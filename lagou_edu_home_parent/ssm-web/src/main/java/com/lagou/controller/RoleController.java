package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    /*
        查询所有角色(条件)
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> allRole = roleService.findAllRole(role);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", allRole);

        return responseResult;

    }

    /*
        查询所有的父子菜单信息(分配菜单的第一个接口)
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){

        //-1表示查询所有的父子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        //响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有的父子菜单成功", map);

        return responseResult;


    }

    /*
        根据角色id查询关联的菜单信息id
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色关联的菜单信息成功", menuByRoleId);

        return responseResult;


    }

    /*
        为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){


        roleService.roleContextMenu(roleMenuVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);

        return responseResult;


    }


    /*
        删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "角色删除成功", null);

        return responseResult;

    }


}
