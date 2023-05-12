package org.kinoz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kinoz.result.Result;
import org.kinoz.service.SysMenuService;
import org.kinoz.system.SysMenu;
import org.kinoz.vo.AssginMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haogu
 * @date 2023/5/12 10:17
 * @DESCRIPTION
 */
@Api(tags = "菜单")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "菜单列表")
    @GetMapping("findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    @ApiOperation(value = "根据id查询菜单")
    @GetMapping("findNodeById/{id}")
    public Result findNodeById(@PathVariable Long id){
        SysMenu menu = sysMenuService.findNodeById(id);
        return Result.ok(menu);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu permission) {
        sysMenuService.save(permission);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody SysMenu permission) {
        sysMenuService.updateById(permission);
        return Result.ok();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysMenuService.removeMenuById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id更新菜单状态")
    @PutMapping("updateStatus/{id}/{status}")
    public Result updateMenuStatus(@PathVariable String id,@PathVariable Long status) {
        sysMenuService.updateMenuStatus(id,status);
        return Result.ok();
    }

    @ApiOperation(value = "根据角色分配菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssing(@PathVariable Long roleId){
        List<SysMenu> menus = sysMenuService.findMenuByRoleId(roleId);
        return Result.ok(menus);
    }

    @ApiOperation(value = "给角色分配权限")
    @PutMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }
}
