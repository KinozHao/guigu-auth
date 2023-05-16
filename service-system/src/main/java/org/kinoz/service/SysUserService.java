package org.kinoz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.kinoz.system.SysUser;
import org.kinoz.vo.SysUserQueryVo;

import java.util.Map;

/**
* @author haogu
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-05-08 13:36:25
*/
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    void updateStatus(String id, Long status);

    SysUser getUserByUserName(String username);

    Map<String, Object> getUserInfo(String username);
}
