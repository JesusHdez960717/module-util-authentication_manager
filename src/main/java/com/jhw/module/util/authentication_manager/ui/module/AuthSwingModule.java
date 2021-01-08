package com.jhw.module.util.authentication_manager.ui.module;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.clean.swing.app.AbstractSwingApplication;
import com.root101.clean.swing.app.DefaultAbstractSwingMainModule;
import com.root101.clean.swing.app.dashboard.DashBoardSimple;
import com.root101.clean.swing.app.dashboard.DashboardConstants;
import com.jhw.module.util.authentication_manager.services.AuthAuthenticationServiceImplementation;
import com.jhw.module.util.authentication_manager.services.AuthNotificationService;
import com.jhw.module.util.authentication_manager.services.AuthResourceServiceImplementation;
import com.jhw.module.util.authentication_manager.ui.usuario.UserControl;

public class AuthSwingModule extends DefaultAbstractSwingMainModule {

    private final AuthModuleNavigator navigator = new AuthModuleNavigator();

    private AuthSwingModule() {
    }

    public static AuthSwingModule init() {
        System.out.println("Iniciando 'Manager de Autenticacion'");
        AuthAuthenticationServiceImplementation.init();
        AuthNotificationService.init();
        try {
            AuthResourceServiceImplementation.init();
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        return new AuthSwingModule();
    }

    @Override
    public void register(AbstractSwingApplication app) {
        DashBoardSimple dash = app.rootView().dashboard();

        dash.addKeyValue(DashboardConstants.UP_ELEMENT, UserControl.from(app), 1000);
    }

    @Override
    public void navigateTo(String string, Object... o) {
        navigator.navigateTo(string, o);
    }

}
