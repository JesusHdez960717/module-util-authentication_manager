/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.authentication_manager.ui.usuario;

import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.core.domain.services.Resource;
import com.clean.swing.app.AbstractSwingApplication;
import com.clean.swing.app.RootView;
import com.jhw.module.util.authentication_manager.services.ResourceKeys;
import com.jhw.module.util.personalization.services.PersonalizationHandler;
import com.jhw.swing.material.standards.MaterialColors;
import com.jhw.swing.material.standards.MaterialIcons;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class UserControl extends AbstractAction {

    public static Object from(AbstractSwingApplication app) {
        return new UserControl(app);
    }

    private final AbstractSwingApplication app;

    public UserControl(AbstractSwingApplication app) {
        super("", MaterialIcons.ACCOUNT_CIRCLE.deriveIcon(MaterialColors.WHITE));
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        app.navigateTo(RootView.LOGIN_NAME);
    }

}
