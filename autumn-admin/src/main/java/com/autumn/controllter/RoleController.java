package com.autumn.controllter;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.vo.RoleChangeVo;
import com.autumn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("list")
    public ResponseResult<Object> roleList(Integer pageNum, Integer pageSize, String roleName, Integer status){
        return roleService.getRoleList(pageNum,pageSize,roleName,status);
    }
    @GetMapping("/listAllRole")
    public ResponseResult<Object> listAllRole(){
        return roleService.getAllRole();
    }
    @PutMapping("changeStatus")
    public ResponseResult<Object> changeStatus(@RequestBody RoleChangeVo roleChangeVo){
        return roleService.changeStatus(roleChangeVo);
    }




    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteRole(@PathVariable("id")Long id){
        return roleService.deleteRole(id);
    }
}
