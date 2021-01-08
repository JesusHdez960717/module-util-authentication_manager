/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.util.authentication_manager.services;

import com.root101.clean.core.app.services.AuthenticationHandler;
import com.root101.clean.core.app.services.NotificationHandler;
import com.jhw.module.util.rest_config.services.RESTHandler;
import java.util.Map;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import com.root101.clean.core.app.services.AuthenticationService;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
