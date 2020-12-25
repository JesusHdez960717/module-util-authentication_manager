/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.authentication_manager.services;

import com.clean.core.app.services.NotificationServiceFunctional;
import com.jhw.swing.material.standards.MaterialIcons;
import com.clean.core.app.services.Notification;
import com.clean.core.domain.services.Resource;
import com.jhw.module.util.authentication_manager.ui.module.AuthModuleNavigator;
import com.jhw.module.util.personalization.core.domain.Personalization;
import com.jhw.module.util.personalization.services.PersonalizationHandler;
import com.jhw.swing.notification.NotificationBuilder;
import com.jhw.swing.notification.NotificationFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class AuthNotificationService extends NotificationServiceFunctional {

    public static final String NOTIFICATION_LOGIN = "auth.notification.login.done";
    public static final String NOTIFICATION_LOGOUT = "auth.notification.logout.done";

    public static final String CONFIRM_LOGOUT = "auth.notification.logout.confirm";

    public static AuthNotificationService init() {
        AuthNotificationService notif = new AuthNotificationService();
        Notification.registerNotificationService(notif);
        return notif;
    }

    private AuthNotificationService() {
        addAll();
    }

    @Override
    protected void addNotifications() {
        super.addNotification(AuthNotificationService.NOTIFICATION_LOGIN, (Object t) -> {
            NotificationFactory.buildNotificationTOAST(NotificationBuilder.builder().
                    delaySeconds(PersonalizationHandler.getInt(Personalization.KEY_INT_NOTIFICATION_DURATION))
                    .text(Resource.getString(Resource.getString(ResourceKeys.MSG_LOGIN)) + "\n" + t.toString())
                    .icon(MaterialIcons.PERSON)
                    .color(PersonalizationHandler.getColor(Personalization.KEY_COLOR_INFO)));
        });
        super.addNotification(AuthNotificationService.NOTIFICATION_LOGOUT, (Object t) -> {
            NotificationFactory.buildNotificationTOAST(NotificationBuilder.builder().
                    delaySeconds(PersonalizationHandler.getInt(Personalization.KEY_INT_NOTIFICATION_DURATION))
                    .text(Resource.getString(ResourceKeys.MSG_LOGOUT))
                    .icon(AuthModuleNavigator.ICON_LOGOUT)
                    .color(PersonalizationHandler.getColor(Personalization.KEY_COLOR_WARNING)));
        });
    }

    @Override
    protected void addConfirmDialog() {
        super.addConfirmDialog(AuthNotificationService.CONFIRM_LOGOUT, (Object t)
                -> JOptionPane.showConfirmDialog(null, Resource.getString(ResourceKeys.MSG_LOGOUT_CONFIRM),
                        "Cerrar sesión",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == 0);
    }

}