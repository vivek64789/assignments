
package frontend;

import backend.DbConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ForgotPassword {
    Font font = new Font("Poppins", Font.PLAIN, 16);
    Font font25 = new Font("Poppins", Font.PLAIN, 25);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    JFrame changePasswordFrame = new JFrame("Change Password");
    JLabel changePasswordBackground, changePasswordTitle, username, newPassword, eyeShow, eyeHide = new JLabel();
    JButton submitButton, loginButton = new JButton();
    JTextField usernameField = new JTextField();
    JPasswordField newPasswordField = new JPasswordField();
    ImageIcon mainFrameImage, iconEyeShow, iconEyeHide, changePasswordButtonImage, loginButtonImages = new ImageIcon();

    ForgotPassword() {
        settingFrame();
        changePasswordFrame.getContentPane().setBackground(Color.white);
        changePasswordFrame.setVisible(true);
        changePasswordFrame.setSize(1366, 820);
    }

    public void settingFrame() {

        // ============ Label Username ============================

        username = new JLabel("Username");
        username.setFont(font);
        changePasswordFrame.add(username);
        username.setBounds(500, 245, 200, 20);

        changePasswordTitle = new JLabel("CHANGE PASSWORD");
        changePasswordTitle.setFont(font25);
        changePasswordFrame.add(changePasswordTitle);
        changePasswordTitle.setBounds(575, 120, 300, 30);


        // ============ Label Password ============================
        newPassword = new JLabel("New Password");
        newPassword.setFont(font);
        changePasswordFrame.add(newPassword);
        newPassword.setBounds(500, 360, 200, 20);


        // ====================== TextField =========================

        // =============== Username TextField =======================
        usernameField = new JTextField();
        changePasswordFrame.add(usernameField);
        usernameField.setFont(font12);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.white));
        usernameField.setBounds(525, 268, 400, 30);


        // =============== Password TextField =======================
        newPasswordField = new JPasswordField();
        changePasswordFrame.add(newPasswordField);
        newPasswordField.setFont(font12);
        newPasswordField.setBorder(BorderFactory.createLineBorder(Color.white));
        newPasswordField.setBounds(525, 383, 360, 30);


        // ====================== Button ===========================

        // ============= Login Button =============================
        submitButton = new JButton();
        changePasswordButtonImage = new ImageIcon("src/images/submit.png");
        submitButton.setIcon(changePasswordButtonImage);
        changePasswordFrame.add(submitButton);
        submitButton.setBounds(550, 500, 144, 40);
        submitButton.setOpaque(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ============= Login Button =============================
        loginButton = new JButton();
        loginButtonImages = new ImageIcon("src/images/login_button.png");
        loginButton.setIcon(loginButtonImages);
        changePasswordFrame.add(loginButton);
        loginButton.setBounds(700, 500, 144, 40);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // ======================= Hide Show ========================

        callEyeShow();
        callEyeHide();
        eyeHide.setVisible(false);

        eyeShow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeShow.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//                System.out.println("Clicked");
                newPasswordField.setEchoChar((char) 0);
                eyeShow.setVisible(false);
                eyeHide.setVisible(true);
            }
        });

        eyeHide.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeHide.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//                System.out.println("Clicked");
                newPasswordField.setEchoChar('\u2022');
                eyeHide.setVisible(false);
                eyeShow.setVisible(true);
            }
        });

        // =========== Logic ==================================

        // =============== Login Button Event Handling=========
        submitButton.addActionListener(e -> {

            String finalGetUsername = this.usernameField.getText();
            char[] getPassword = this.newPasswordField.getPassword();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < getPassword.length; i++) {

                sb.append(getPassword[i]);
            }
            String finalGetPassword = sb.toString();

            System.out.println(finalGetPassword);
            if (finalGetUsername.isEmpty() || finalGetPassword.isEmpty()) {
                JOptionPane.showMessageDialog(changePasswordFrame, "Fields can not be empty");
            } else {
                String query = "UPDATE admins SET password = '" + finalGetPassword + "' WHERE admins.username ='"+ finalGetUsername +"'";
                System.out.println(query);
                DbConnect db = new DbConnect();
                try {
                    int data = db.connection().executeUpdate(query);
                    if (data == 1) {
                        JOptionPane.showMessageDialog(changePasswordFrame, "Password changed successfully");
                        new Login();
                        changePasswordFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(changePasswordFrame, "There is an error");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        });

        // ============ Register Button Event handling==========

        loginButton.addActionListener(e -> {

            Login obj = new Login();
            changePasswordFrame.dispose();


        });


        // =================== Frame Background =======================

        mainFrameImage = new ImageIcon("src/images/LoginFrame.png");
        changePasswordBackground = new JLabel();
        changePasswordBackground.setIcon(mainFrameImage);
        changePasswordFrame.add(changePasswordBackground);
        changePasswordBackground.setBounds(0, 0, 1366, 720);


    }

    public void callEyeHide() {
        // ======================= Eye Hide ========================
        iconEyeHide = new ImageIcon("src/images/eyeHide.png");
        eyeHide = new JLabel();
        eyeHide.setIcon(iconEyeHide);
        changePasswordFrame.add(eyeHide);
        eyeHide.setBounds(895, 377, 40, 40);
    }

    public void callEyeShow() {
        // ================ Eye Show ================================
        iconEyeShow = new ImageIcon("src/images/eyeShow.png");
        eyeShow = new JLabel();
        eyeShow.setIcon(iconEyeShow);
        changePasswordFrame.add(eyeShow);
        eyeShow.setBounds(895, 377, 40, 40);
    }

}
