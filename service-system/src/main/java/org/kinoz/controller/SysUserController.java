package org.kinoz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.kinoz.result.Result;
import org.kinoz.service.SysUserService;
import org.kinoz.system.SysRole;
import org.kinoz.system.SysUser;
import org.kinoz.vo.SysRoleQueryVo;
import org.kinoz.vo.SysUserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haogu
 * @date 2023/5/8 13:38
 * @DESCRIPTION
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @ApiOperation(value = "获取所有用户信息")
    @GetMapping("findAll")
    public Result getAllUser(){
        List<SysUser> list = sysUserService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/save")
    public Result addUser(@RequestBody SysUser user){
        boolean result = sysUserService.save(user);
        return result ? Result.ok(user) : Result.fail();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public Result deleteUser(@PathVariable Long id){
        boolean result = sysUserService.removeById(id);
        return result ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "根据id获取用户")
    @GetMapping("/findById/{id}")
    public Result getUserById(@PathVariable Long id){
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/update")
    public Result updateUser(@RequestBody SysUser user){
        sysUserService.updateById(user);
        return Result.ok();
    }

    @ApiOperation(value = "批量删除用户")
    @PostMapping("/batchRemove")
    public Result BatchDeleteUser(@RequestBody List<Long> ids){
        sysUserService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)
            SysUserQueryVo userQueryVo) {
        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, userQueryVo);
        return Result.ok(pageModel);
    }
}
