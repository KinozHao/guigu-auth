package org.kinoz.helper;

/**
 * @author haogu
 * @date 2023/5/12 10:25
 * @DESCRIPTION
 */

import org.kinoz.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据菜单数据构建菜单树的工具类
 * </p>
 *
 */
public class MenuHelper {

    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        sysMenu.setChildren(new ArrayList<SysMenu>());

        treeNodes.stream()
                .filter(menu -> sysMenu.getId().equals(String.valueOf(menu.getParentId())))
                .forEach(menu -> {
                    if (sysMenu.getChildren() == null) {
                        sysMenu.setChildren(new ArrayList<>());
                    }
                    sysMenu.getChildren().add(findChildren(menu, treeNodes));
                });

        return sysMenu;
    }
}