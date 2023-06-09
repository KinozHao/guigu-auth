package org.kinoz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.kinoz.exception.LabException;
import org.kinoz.result.Result;
import org.kinoz.service.SysRoleService;
import org.kinoz.system.SysRole;
import org.kinoz.vo.AssginRoleVo;
import org.kinoz.vo.SysRoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        try {
            int a = 10/0;
        } catch (Exception e) {
            throw new LabException(500,"by zero,solve it!!!");
        }
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

    @ApiOperation(value = "根据id批量删除角色")
    @DeleteMapping("batchRemove")
    //json数据对应java中的list集合
    public Result batchRemove(@RequestBody List<Long> ids){
        return  Result.ok(sysRoleService.removeByIds(ids));
    }

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
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

    @ApiOperation(value = "获取用户的角色数据")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable Long userId){

       Map<String, Object> roleMap =  sysRoleService.getRolesByUserId(userId);

       return Result.ok(roleMap);
    }

    @ApiOperation(value = "用户分配角色")
    @PostMapping( "/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
}