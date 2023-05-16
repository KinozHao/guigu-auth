package org.kinoz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kinoz.enums.StatusEnum;
import org.kinoz.exception.LabException;
import org.kinoz.result.Result;
import org.kinoz.service.SysUserService;
import org.kinoz.system.SysUser;
import org.kinoz.utils.JwtHelper;
import org.kinoz.utils.MD5;
import org.kinoz.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haogu
 * @date 2023/5/7 15:45
 * @DESCRIPTION
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    SysUserService sysUserService;
    /**
     * 登录
     * {"code":20000,"data":{"token":"admin-token"}}
     * @return
     */
    @ApiOperation(value = "前端登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        // 根据用户名获取用户信息
        SysUser sysUser = sysUserService.getUserByUserName(loginVo.getUsername());

        // 判断是否为空
        if (sysUser == null){
            throw new LabException(20001,"用户不存在");
        }

        //判断密码是否一致
        /*String password = MD5.encrypt(loginVo.getPassword());
        if (!sysUser.getPassword().equals(password)){
            throw new LabException(20002,"密码不正确");
        }*/

        //判断用户是否可用
        if (sysUser.getStatus().intValue() == 0){
            throw new LabException(20003,"此用户已被禁用");
        }

        //根据userid和username生产token字符串,通过map返回
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());

        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }
    /**
     * 获取用户信息
     * {
     * "code":20000,
     * "data":{
     * "roles":["admin"],
     * "introduction":"I am a super administrator",
     * "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
     * "name":"Super Admin"}
     * }
     * @return
     */
    @ApiOperation(value = "前端信息获取")
    @GetMapping("/info")
    public Result info(HttpServletRequest req) {
        //获取请求头token字符串
        String token = req.getHeader("token");
        //从token字符串中获取用户名称(id)
        String username = JwtHelper.getUsername(token);
        //根据用户名称获取用户信息(基本信息 菜单权限 按钮权限)
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }
    /**
     * 登出
     * {"code":20000,"data":"success"}
     * @return
     */
    @ApiOperation(value = "前端登出")
    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}
