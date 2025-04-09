package com.research.manager.sysmanager.service;


import com.research.manager.sysmanager.dto.LoginDto;
import com.research.manager.sysmanager.vo.LoginVo;

public interface LoginService {

    public LoginVo userlogin(LoginDto loginDto);
}
