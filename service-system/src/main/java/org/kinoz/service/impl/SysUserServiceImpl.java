package org.kinoz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kinoz.mapper.SysUserMapper;
import org.kinoz.service.SysMenuService;
import org.kinoz.service.SysUserService;
import org.kinoz.system.SysUser;
import org.kinoz.vo.RouterVo;
import org.kinoz.vo.SysUserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author haogu
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-05-08 13:36:25
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {
    @Autowired
    SysMenuService sysMenuService;
    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParam,sysUserQueryVo);
    }

    @Override
    public void updateStatus(String id, Long status) {
        //1.accord id get userinfo
        SysUser sysUser = baseMapper.selectById(id);
        //2.set user's status
        sysUser.setStatus(Math.toIntExact(status));
        //3.accord id update userinfo
        baseMapper.updateById(sysUser);
    }

    @Override
    public SysUser getUserByUserName(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,username);

        SysUser sysUser = baseMapper.selectOne(wrapper);
        return sysUser;
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        // 根据username查询用户基本信息
        SysUser user = this.getUserByUserName(username);

        // 根据userid查询菜单权限值
        List<RouterVo> routerVosList =  sysMenuService.getUserMenuList(user.getId());

        // 根据userid查询按钮权限值
        List<String> buttonList =  sysMenuService.getUserButtonList(user.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name",username);
        map.put("avatar","https://i0.wp.com/www.printmag.com/wp-content/uploads/2021/02/4cbe8d_f1ed2800a49649848102c68fc5a66e53mv2.gif?fit=476%2C280&ssl=1");
        //菜单权限数据
        map.put("routers",routerVosList);
        //按钮权限数据
        map.put("buttons",buttonList);
        return null;
    }
}




