package com.research.manager.ldm.service;

import com.research.manager.ldm.dto.LoginDto;
import com.research.manager.ldm.vo.LoginVo;

public interface LoginService {

    public LoginVo userlogin(LoginDto loginDto);
}
