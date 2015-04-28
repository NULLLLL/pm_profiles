package com.profiles.service.account;

import org.apache.shiro.authc.UsernamePasswordToken;


public class MYUsernamePasswordToken extends UsernamePasswordToken  {
	
    public MYUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host) {  
        super(username, password, rememberMe, host);  
    }  
  
}  

