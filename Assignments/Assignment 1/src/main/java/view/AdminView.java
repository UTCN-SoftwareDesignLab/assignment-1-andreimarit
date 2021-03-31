package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by Alex on 18/03/2017.
 */
public class AdminView extends JFrame {


    private JLabel userLabel;
    private JButton logoutButton;

    public AdminView() throws HeadlessException {
        setSize(400, 400);
        setTitle("Admin");
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(userLabel);
        add(logoutButton);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        userLabel = new JLabel("This is Admin's page");
        logoutButton = new JButton("Logout");
    }

//    public String getUsername() {
//        return tfUsername.getText();
//    }
//
//    public String getPassword() {
//        return tfPassword.getText();
//    }
//
    public void setLogoutButtonListener(ActionListener logoutButtonListener) {
        logoutButton.addActionListener(logoutButtonListener);
    }
//
//    public void setRegisterButtonListener(ActionListener registerButtonListener) {
//        btnRegister.addActionListener(registerButtonListener);
//    }

    public void setVisible() {
        this.setVisible(true);
    }

}
