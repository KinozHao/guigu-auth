package org.kinoz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.kinoz.result.Result;
import org.kinoz.service.SysRoleService;
import org.kinoz.system.SysRole;
import org.kinoz.vo.SysRoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haogu
 * @date 2023/5/5 16:30
 * @DESCRIPTION
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("findAll")
    public Result findAll() {
        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);
    }

    @ApiOperation(value="添加角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole sysRole){
        return sysRoleService.save(sysRole)? Result.ok() : Result.fail();
    }

    @ApiOperation(value="根据id获取角色")
    @GetMapping("findById/{id}")
    public Result update(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    @ApiOperation(value="更新角色")
    @PutMapping("update")
    public Result update(@RequestBody SysRole sysRole){
        return sysRoleService.updateById(sysRole)? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "根据id删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
       return  sysRoleService.removeById(id) ? Result.ok() : Result.fail();

    }

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
            SysRoleQueryVo roleQueryVo) {
        Page<SysRole> pageParam = new Page<>(page, limit);
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, roleQueryVo);
        return Result.ok(pageModel);
    }
}