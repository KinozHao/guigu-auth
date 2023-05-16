package org.kinoz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.kinoz.system.SysMenu;
import org.kinoz.vo.AssginMenuVo;
import org.kinoz.vo.RouterVo;

import java.util.List;

/**
* @author haogu
* @description 针对表【sys_menu(菜单表)】的数据库操作Service
* @createDate 2023-05-12 10:11:45
*/
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    void removeMenuById(Long id);

    SysMenu findNodeById(Long id);

    void updateMenuStatus(String id, Long status);

    List<SysMenu> findMenuByRoleId(Long roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    List<RouterVo> getUserMenuList(String id);

    List<String> getUserButtonList(String id);
}
