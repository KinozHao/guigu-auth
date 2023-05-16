package org.kinoz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.kinoz.enums.StatusEnum;
import org.kinoz.exception.LabException;
import org.kinoz.helper.MenuHelper;
import org.kinoz.mapper.SysMenuMapper;
import org.kinoz.mapper.SysRoleMenuMapper;
import org.kinoz.service.SysMenuService;
import org.kinoz.system.SysMenu;
import org.kinoz.system.SysRoleMenu;
import org.kinoz.system.SysUser;
import org.kinoz.utils.RouterHelper;
import org.kinoz.vo.AssginMenuVo;
import org.kinoz.vo.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
* @author haogu
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2023-05-12 10:11:45
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        // 查询全部权限列表
        List<SysMenu> list = this.list();
        if (CollectionUtils.isEmpty(list)){
            return null;
        }

        //构建树形数据
        return  MenuHelper.buildTree(list);
    }

    @Override
    public SysMenu findNodeById(Long id) {
        return this.getById(id);
    }

    @Override
    public void updateMenuStatus(String id, Long status) {
        SysMenu menu = baseMapper.selectById(id);

        menu.setStatus(Math.toIntExact(status));

        baseMapper.updateById(menu);
    }

    @Override
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        //获取所有status为1的权限列表
        List<SysMenu> menuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        //根据角色id获取角色权限
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id",roleId));
        //获取该角色已分配的所有权限id
        List<String> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(String.valueOf(roleMenu.getMenuId()));
        }
        //遍历所有权限列表
        for (SysMenu sysMenu : menuList) {
            if(roleMenuIds.contains(sysMenu.getId())){
                //设置该权限已被分配
                sysMenu.setSelect(true);
            }else {
                sysMenu.setSelect(false);
            }
        }
        //将权限列表转换为权限树
        List<SysMenu> sysMenus = MenuHelper.buildTree(menuList);
        return sysMenus;
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //删除已分配的权限
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId,assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);

        //遍历所有已选择的权限id
        assginMenuVo.getMenuIdList().stream()
                .filter(Objects::nonNull)
                .map(menuId -> {
                    //创建SysRoleMenu对象
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setMenuId(menuId);
                    sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
                    return sysRoleMenu;
                })
                //添加新权限
                .forEach(sysRoleMenuMapper::insert);

    }

    @Override
    public List<RouterVo> getUserMenuList(String userId) {
        //admin是超级管理员 操作所有内容
        List<SysMenu> sysMenus = null;
        //判断当前用户id是不是admin,获取所有权限数据
        if ("1".equals(userId)){
            sysMenus = baseMapper.selectList(
                    new LambdaQueryWrapper<SysMenu>()
                            .eq(SysMenu::getStatus, 1)
                            .orderByAsc(SysMenu::getSortValue));
        }else {
            //不是admin的情况下其他查询
            sysMenus = baseMapper.findMenuListId(userId);
        }

        // 构建树形结构
        List<SysMenu> sysMenusTree = MenuHelper.buildTree(sysMenus);
        // 转换为前端路由要求的格式数据
        List<RouterVo> routerVos = RouterHelper.buildRouters(sysMenusTree);
        return routerVos;
    }

    @Override
    public List<String> getUserButtonList(String id) {
        return null;
    }

    @Override
    public void removeMenuById(Long id) {
        // 查询要删除的菜单下面是否有子菜单
        // 根据 id = parentId
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId,id);
        Integer integer = baseMapper.selectCount(wrapper);
        if (integer > 0){
            throw new LabException(201,"请先删除子菜单");
        }
        baseMapper.deleteById(id);
    }


}




