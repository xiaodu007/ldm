package com.research.manager.ldm.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginVo {

    private Integer userId;

    private String token;

    private String userName;

    private String realName;
}
