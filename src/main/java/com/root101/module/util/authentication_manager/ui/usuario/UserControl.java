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
package com.root101.module.util.authentication_manager.ui.usuario;

import com.root101.clean.core.app.services.AuthenticationHandler;
import com.root101.clean.core.app.services.NotificationHandler;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.clean.swing.app.AbstractSwingApplication;
import com.root101.clean.swing.app.RootView;
import com.root101.module.util.authentication_manager.services.AuthNotificationService;
import com.root101.module.util.authentication_manager.services.ResourceKeys;
import com.root101.swing.material.components.button._MaterialButtonIconTransparent;
import com.root101.swing.material.standards.MaterialColors;
import com.root101.swing.material.standards.MaterialIcons;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
