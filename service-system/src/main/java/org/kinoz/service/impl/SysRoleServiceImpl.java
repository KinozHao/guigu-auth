package org.kinoz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kinoz.mapper.SysRoleMapper;
import org.kinoz.service.SysRoleService;
import org.kinoz.system.SysRole;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
