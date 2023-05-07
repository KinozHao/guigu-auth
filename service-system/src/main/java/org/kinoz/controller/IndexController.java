package org.kinoz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kinoz.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    /**
     * 登录
     * {"code":20000,"data":{"token":"admin-token"}}
     * @return
     */
    @ApiOperation(value = "前端登录")
    @PostMapping("/login")
    public Result login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token","ewrqreqfdafaf23432fdafewqrqwrq");
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
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","KinozHao");
        //map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("avatar","https://i0.wp.com/www.printmag.com/wp-content/uploads/2021/02/4cbe8d_f1ed2800a49649848102c68fc5a66e53mv2.gif?fit=476%2C280&ssl=1");
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
