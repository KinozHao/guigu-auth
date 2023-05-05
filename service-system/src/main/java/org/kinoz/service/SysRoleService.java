package org.kinoz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.kinoz.system.SysRole;
import org.kinoz.vo.SysRoleQueryVo;

public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo);
}
