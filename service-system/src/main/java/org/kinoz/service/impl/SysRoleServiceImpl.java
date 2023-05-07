package org.kinoz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kinoz.mapper.SysRoleMapper;
import org.kinoz.service.SysRoleService;
import org.kinoz.system.SysRole;
import org.kinoz.vo.SysRoleQueryVo;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo) {
        return baseMapper.selectPage(pageParam,roleQueryVo);
    }
}
