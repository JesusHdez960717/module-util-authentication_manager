/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.authentication_manager.ui.usuario;

import com.root101.clean.core.app.services.AuthenticationHandler;
import com.root101.clean.core.app.services.NotificationHandler;
import com.root101.clean.core.app.services.NotificationsGeneralType;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.clean.swing.app.AbstractSwingApplication;
import com.root101.clean.swing.app.RootView;
import com.jhw.module.util.authentication_manager.services.AuthNotificationService;
import com.jhw.module.util.authentication_manager.services.ResourceKeys;
import com.root101.swing.material.components.button._MaterialButtonIconTransparent;
import com.root101.swing.material.standards.MaterialColors;
import com.root101.swing.material.standards.MaterialIcons;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class UserControl extends _MaterialButtonIconTransparent {

    public static UserControl from(AbstractSwingApplication app) {
        return new UserControl(app);
    }

    private final AbstractSwingApplication app;

    public UserControl(AbstractSwingApplication app) {
        this.app = app;
        this.setText("");
        this.setIcon(MaterialIcons.ACCOUNT_CIRCLE.deriveIcon(MaterialColors.WHITE));
        this.setComponentPopupMenu(createMenu());
        addListeners();
    }

    private JPopupMenu createMenu() {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new AbstractAction("Cerrar sesión", MaterialIcons.POWER_SETTINGS_NEW) {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAction();
            }
        });
        return menu;
    }

    private void closeAction() {
        if (NotificationHandler.showConfirmDialog(AuthNotificationService.CONFIRM_LOGOUT, "")) {
            AuthenticationHandler.logout();
            app.navigateTo(RootView.LOGIN_NAME);
            NotificationHandler.showNotification(AuthNotificationService.NOTIFICATION_LOGOUT, ResourceHandler.getString(ResourceKeys.MSG_LOGOUT));
        }
    }

    private void addListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPopup(e);
            }
        });
    }

    private void showPopup(MouseEvent e) {
        if (getComponentPopupMenu() != null) {
            //debajo del componente
            int x = 0;
            int y = (int) getSize().getHeight();
            getComponentPopupMenu().show(this, x, y);
        }
    }
}
