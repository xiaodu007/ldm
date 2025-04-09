package com.research.manager.sysmanager.controller;

import com.research.manager.sysmanager.entity.User;
import com.research.manager.sysmanager.exception.BusinessCode;
import com.research.manager.sysmanager.service.UserService;
import com.research.manager.sysmanager.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author weihao
 * @since 2025-03-24
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.POST)
    public ResultVo getUserById(@PathVariable String id){
        User user = userService.getUserById(id);
        return new ResultVo(user);
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ResultVo saveUser(@RequestBody User user){
        userService.save(user);
        return new ResultVo(BusinessCode.SUCCESS);
    }

}
