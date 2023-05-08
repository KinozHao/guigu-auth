package org.kinoz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.kinoz.system.SysRole;
import org.kinoz.system.SysUser;
import org.kinoz.vo.SysRoleQueryVo;
import org.kinoz.vo.SysUserQueryVo;

/**
* @author haogu
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2023-05-08 13:36:25
* @Entity generator.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<SysUser> selectPage(Page<SysUser> pageParam, @Param("vo") SysUserQueryVo sysUserQueryVo);
}




