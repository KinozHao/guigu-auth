package org.kinoz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.kinoz.system.SysRole;
import org.kinoz.vo.AssginRoleVo;
import org.kinoz.vo.SysRoleQueryVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo);

    Map<String,Object> getRolesByUserId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
