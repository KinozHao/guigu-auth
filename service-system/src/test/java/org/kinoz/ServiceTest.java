package org.kinoz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.kinoz.service.SysRoleService;
import org.kinoz.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
/**
 * @author haogu
 * @date 2023/5/4 14:37
 * @DESCRIPTION use service level to implement the database crud
 */
@SpringBootTest
public class ServiceTest {

    @Autowired
    SysRoleService sysRoleService;

    @Test
    public void findAll(){
        List<SysRole> list = sysRoleService.list();
        list.forEach(System.out::println);
    }
    @Test
    public void conditionSelect(){
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        //the roleCode columns must include "y"
        wrapper.like(SysRole::getRoleCode,"y");
        sysRoleService.list(wrapper);
    }

    @Test
    public void add(){
        SysRole sysRole = new SysRole("实验室管理员","sysgly","实验室管理员");
        boolean save = sysRoleService.save(sysRole);
        System.out.println(save);
    }
    @Test
    public void update(){
        SysRole sysRole = sysRoleService.getById(1654010502747021313L);
        sysRole.setRoleCode("library admin");
        boolean result = sysRoleService.updateById(sysRole);
        System.out.println(result);
    }
    @Test
    public void remove(){
        //we used @TableLogic at model so this is a logic remove! the data also have in the table not del!
        boolean result = sysRoleService.removeById(9);
        System.out.println(result);
    }

    @Test
    public void bubbleSort(){
        int arr[] = {23,434,232,43,53,231,4353};
        for (int i = 0; i < arr.length; i++) {
            for (int j =0; j< arr.length - 1; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = 0;
                    temp = arr[i];
                    arr[i] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
