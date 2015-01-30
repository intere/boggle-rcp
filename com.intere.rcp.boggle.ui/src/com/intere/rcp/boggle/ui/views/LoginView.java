package com.intere.rcp.boggle.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.UIPlayer;
import com.intere.rcp.boggle.ui.controllers.GuiBoggleClient;
import com.intere.rcp.boggle.ui.controllers.PlayerManager;

/**
 * This view is the "Login" view. It logs you in/out of the Boggle Server.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class LoginView extends ViewPart {

    public static final String ID = "com.intere.rcp.boggle.ui.views.LoginView";

    private Label userIdLabel;

    private Text usernameText;

    private Button loginButton;

    private Button logoutButton;

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(2, false));

        Label lbl = new Label(parent, SWT.NONE);
        lbl.setText("UID: ");
        lbl.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));

        userIdLabel = new Label(parent, SWT.BORDER);
        userIdLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        lbl = new Label(parent, SWT.NONE);
        lbl.setText("Username: ");
        lbl.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));

        usernameText = new Text(parent, SWT.BORDER);
        usernameText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        loginButton = new Button(parent, SWT.PUSH);
        loginButton.setText("Login");
        loginButton.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));

        logoutButton = new Button(parent, SWT.PUSH);

        logoutButton.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));

        loginButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                login();
            }
        });

        usernameText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(e.keyCode==SWT.CR) {
                    login();
                }
            }
        });

        logoutButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                logout();
            }
        });

        if (PlayerManager.getInstance().getPlayer() != null) {
            userIdLabel.setText(PlayerManager.getInstance().getPlayer().getPlayerId());
            usernameText.setText(PlayerManager.getInstance().getPlayer().getUsername());
        }

        updateButtons();
    }

    /**
     * Handles the login.
     */
    protected void login() {
        if (!PlayerManager.getInstance().isLoggedIn()) {
            PlayerManager.getInstance().setPlayer(new UIPlayer(usernameText.getText()));
            GuiBoggleClient.getInstance().login();
        } else {
            PlayerManager.getInstance().getPlayer().setUsername(usernameText.getText());
        }

        loginButton.setEnabled(false);
        updateButtons();
    }

    /**
     * Handles the logout event.
     */
    protected void logout() {
        if (PlayerManager.getInstance().isLoggedIn()) {
            GuiBoggleClient.getInstance().logout();
        }
        updateButtons();
    }

    @Override
    public void setFocus() {
        updateButtons();
        usernameText.forceFocus();
    }

    protected void updateButtons() {
        if (PlayerManager.getInstance().isLoggedIn()) {
            loginButton.setEnabled(false);
            logoutButton.setText("Logout");
            userIdLabel.setText(PlayerManager.getInstance().getPlayer().getPlayerId());
            usernameText.setEnabled(false);
        } else {
            loginButton.setEnabled(true);
            logoutButton.setText("Cancel");
            userIdLabel.setText("");
            usernameText.setEnabled(true);
        }
    }

}
