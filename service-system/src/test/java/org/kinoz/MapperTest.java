package org.kinoz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.kinoz.mapper.SysRoleMapper;
import org.kinoz.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
/**
 * @author haogu
 * @date 2023/5/4 14:37
 * @DESCRIPTION use mapper level to implement the database crud
 */
@SpringBootTest
public class MapperTest {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Test
    public void allTest(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        sysRoles.forEach(System.out::println);
    }

    @Test
    public void logicDel(){
        //测试逻辑删除,逻辑删除只是把语句的is_delete值修改
        //实际数据库中并没有删除依然存在
        int row  = sysRoleMapper.deleteById(9);
        System.out.println("影响行数:"+row);
    }

    @Test
    public void wrapperTest(){
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //条件查询
        //wrapper.eq("role_name","系统管理员");
        //模糊查询
        wrapper.like("role_name","管理员");
        sysRoleMapper.selectList(wrapper);
    }
}
