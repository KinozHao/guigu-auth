package org.kinoz.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kinoz.mapper.SysUserMapper;
import org.kinoz.service.SysUserService;
import org.kinoz.system.SysUser;
import org.kinoz.vo.SysUserQueryVo;
import org.springframework.stereotype.Service;

/**
* @author haogu
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-05-08 13:36:25
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParam,sysUserQueryVo);
    }
}




