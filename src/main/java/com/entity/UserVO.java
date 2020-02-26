package com.entity;

import lombok.Data;

@Data
public class UserVO extends User{
    private String token;
    private  String NewPassword;
}
