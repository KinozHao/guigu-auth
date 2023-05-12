package org.kinoz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kinoz.mapper.SysRoleMapper;
import org.kinoz.mapper.SysUserRoleMapper;
import org.kinoz.service.SysRoleService;
import org.kinoz.system.SysRole;
import org.kinoz.system.SysUserRole;
import org.kinoz.vo.AssginRoleVo;
import org.kinoz.vo.SysRoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo) {
        return baseMapper.selectPage(pageParam,roleQueryVo);
    }

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Map<String,Object> getRolesByUserId(Long userId) {
        // 获取所有角色
        List<SysRole> roles = baseMapper.selectList(null);
        // 根据用户id查询,已经分配的角色
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,userId);
        List<SysUserRole> userRoleList = sysUserRoleMapper.selectList(wrapper);


        // 从sysUserRoles获取所有角色id
        /*List<String> userRoleIds = new ArrayList<>();
        for (SysUserRole sysUserRole : userRoleList) {
            userRoleIds.add(sysUserRole.getRoleId());
        }*/
        List<String> userRoleIds = userRoleList.stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());
        //封装到map集合
        Map<String, Object> finalResult = new HashMap<>();
        finalResult.put("allRoles",roles);
        finalResult.put("userRoleIds",userRoleIds);
        return finalResult;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        // 根据用户id删除之前的角色信息
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);

        // 获取所有角色id 添加角色用户关系表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        //角色id列表
        roleIdList.stream().forEach(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleMapper.insert(sysUserRole);
        });


    }
}
