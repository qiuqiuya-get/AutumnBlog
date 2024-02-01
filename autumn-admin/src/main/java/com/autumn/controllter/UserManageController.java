package com.autumn.controllter;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.vo.RoleChangeVo;
import com.autumn.service.RoleService;
import com.autumn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
public class UserManageController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber, Integer status){
        return userService.getUserList(pageNum,pageSize,userName,phonenumber,status);
    }



    @PutMapping("changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleChangeVo roleChangeVo){
        return userService.changeStatus(roleChangeVo);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable("id")Long id){
        return userService.deleteUser(id);
    }
}
