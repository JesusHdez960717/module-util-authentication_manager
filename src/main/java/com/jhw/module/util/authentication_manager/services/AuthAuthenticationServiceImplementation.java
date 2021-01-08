/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.authentication_manager.services;

import com.root101.clean.core.app.services.AuthenticationHandler;
import com.root101.clean.core.app.services.NotificationHandler;
import com.root101.clean.core.app.services.NotificationsGeneralType;
import com.jhw.module.util.rest_config.services.RESTHandler;
import java.util.Map;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import com.root101.clean.core.app.services.AuthenticationService;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class AuthAuthenticationServiceImplementation implements AuthenticationService<String, String, OAuth2AccessToken> {

    public static AuthAuthenticationServiceImplementation init() {
        AuthAuthenticationServiceImplementation auth = new AuthAuthenticationServiceImplementation();
        AuthenticationHandler.registerAuthenticationService(auth);
        return auth;
    }

    private AuthAuthenticationServiceImplementation() {
    }

    @Override
    public boolean login(String user, String pass, Map<String, Object> args) {
        boolean answ = RESTHandler.login(user, pass, args);
        if (answ) {
            NotificationHandler.showNotification(AuthNotificationService.NOTIFICATION_LOGIN, user);
        }
        return answ;
    }

    @Override
    public boolean logout() {
        return RESTHandler.logout();
    }

}
