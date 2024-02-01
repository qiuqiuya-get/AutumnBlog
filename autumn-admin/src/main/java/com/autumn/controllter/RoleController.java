package com.autumn.controllter;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.TagListDto;
import com.autumn.domain.vo.RoleChangeVo;
import com.autumn.mapper.RoleMapper;
import com.autumn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("list")
    public ResponseResult roleList(Integer pageNum, Integer pageSize, String roleName, Integer status){
        return roleService.getRoleList(pageNum,pageSize,roleName,status);
    }

    @PutMapping("changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleChangeVo roleChangeVo){
        return roleService.changeStatus(roleChangeVo);
    }




    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable("id")Long id){
        return roleService.deleteRole(id);
    }
}
