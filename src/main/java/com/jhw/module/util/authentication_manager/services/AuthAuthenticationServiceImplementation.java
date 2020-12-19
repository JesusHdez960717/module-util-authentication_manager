/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.authentication_manager.services;

import com.clean.core.app.services.AuthenticationHandler;
import com.clean.core.app.services.AuthenticationHandlerService;
import com.jhw.module.util.rest_config.services.RESTHandler;
import java.util.Map;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class AuthAuthenticationServiceImplementation implements AuthenticationHandlerService<String, String, OAuth2AccessToken> {

    public static AuthAuthenticationServiceImplementation init() {
        AuthAuthenticationServiceImplementation auth = new AuthAuthenticationServiceImplementation();
        AuthenticationHandler.registerAuthHandlerService(auth);
        return auth;
    }

    private AuthAuthenticationServiceImplementation() {
    }

    @Override
    public boolean login(String user, String pass, Map<String, Object> args) {
        return RESTHandler.login(user, pass, args);
    }

}
