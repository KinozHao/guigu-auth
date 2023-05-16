package org.kinoz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.kinoz.system.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author haogu
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2023-05-12 10:11:45
* @Entity generator.SysMenu
*/
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    //根据用户id查询菜单权限数据
    List<SysMenu> findMenuListId(@Param("userId") String userId);
}




