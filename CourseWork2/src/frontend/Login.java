package frontend;

import backend.DbConnect;
import backend.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Login {
    Font font = new Font("Poppins", Font.PLAIN, 16);
    Font font25 = new Font("Poppins", Font.PLAIN, 25);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    JFrame loginFrame = new JFrame("Login");
    JLabel LoginBackground, username, password, freshStart, forgotPassword, loginTitle, eyeShow, eyeHide = new JLabel();
    JButton loginButton, signUpButton = new JButton();
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    ImageIcon mainFrameImage, iconEyeShow, iconEyeHide, loginButtonImage, signUpButtonImage = new ImageIcon();

    Login() {
        settingFrame();

        loginFrame.getContentPane().setBackground(Color.white);
        loginFrame.setVisible(true);
        loginFrame.setSize(1366, 820);
    }

    public void settingFrame() {

        // ============================Label===============================

        // ============ Label Username ============================
        loginTitle = new JLabel("LOGIN HERE");
        loginTitle.setFont(font25);
        loginFrame.add(loginTitle);
        loginTitle.setBounds(625, 120, 300, 30);

        // ============ Label Username ============================

        username = new JLabel("Username");
        username.setFont(font);
        loginFrame.add(username);
        username.setBounds(500, 245, 200, 20);


        // ============ Label Password ============================
        password = new JLabel("Password");
        password.setFont(font);
        loginFrame.add(password);
        password.setBounds(500, 360, 200, 20);

        // ============ Forgot Password ============================
        forgotPassword = new JLabel("Forgot Password?");
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPassword.setForeground(Color.red);
        forgotPassword.setFont(font);
        loginFrame.add(forgotPassword);
        forgotPassword.setBounds(770, 430, 200, 20);


        // ============ Fresh Start ============================
        freshStart = new JLabel("Wipe Data");
        freshStart.setForeground(Color.red);
        freshStart.setFont(font);
        loginFrame.add(freshStart);
        freshStart.setBounds(650, 650, 200, 20);
        freshStart.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ====================== TextField =========================

        // =============== Username TextField =======================
        usernameField = new JTextField();
        loginFrame.add(usernameField);
        usernameField.setFont(font12);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.white));
        usernameField.setBounds(525, 268, 400, 30);


        // =============== Password TextField =======================
        passwordField = new JPasswordField();
        loginFrame.add(passwordField);
        passwordField.setFont(font12);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.white));
        passwordField.setBounds(525, 383, 360, 30);


        // ====================== Button ===========================

        // ============= Login Button =============================
        loginButton = new JButton();
        loginButtonImage = new ImageIcon("src/images/login_button.png");
        loginButton.setIcon(loginButtonImage);
        loginFrame.add(loginButton);
        loginButton.setBounds(550, 500, 144, 40);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ============= Login Button =============================
        signUpButton = new JButton();
        signUpButtonImage = new ImageIcon("src/images/register_button.png");
        signUpButton.setIcon(signUpButtonImage);
        loginFrame.add(signUpButton);
        signUpButton.setBounds(700, 500, 144, 40);
        signUpButton.setOpaque(false);
        signUpButton.setContentAreaFilled(false);
        signUpButton.setBorderPainted(false);
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // ======================= Hide Show ========================

        callEyeShow();
        callEyeHide();
        eyeHide.setVisible(false);

        eyeShow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeShow.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//                System.out.println("Clicked");
                passwordField.setEchoChar((char) 0);
                eyeShow.setVisible(false);
                eyeHide.setVisible(true);
            }
        });

        eyeHide.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeHide.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//                System.out.println("Clicked");
                passwordField.setEchoChar('\u2022');
                eyeHide.setVisible(false);
                eyeShow.setVisible(true);
            }
        });

        // =========== Logic ==================================

        // =============== Login Button Event Handling=========
        loginButton.addActionListener(e -> {

            String finalGetUsername = this.usernameField.getText();
            char[] getPassword = this.passwordField.getPassword();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < getPassword.length; i++) {

                sb.append(getPassword[i]);
            }
            String finalGetPassword = sb.toString();

            System.out.println(finalGetPassword);
            if (finalGetUsername.isEmpty() || finalGetPassword.isEmpty()) {
                JOptionPane.showMessageDialog(loginFrame, "Fields can not be empty");
            } else {
                String query = "SELECT * FROM admins where (email='" + finalGetUsername + "' OR username ='" + finalGetUsername + "') AND password = '"+finalGetPassword+"'";

                DbConnect db = new DbConnect();
                try {
                    ResultSet data = db.connection().executeQuery(query);
                    if(data.next()){
                        new AdminDashboard();
                        loginFrame.dispose();
                    }else {
                        JOptionPane.showMessageDialog(loginFrame, "Username or password invalid");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        });

        // ============ Register Button Event handling==========

        signUpButton.addActionListener(e -> {

            SignUp obj = new SignUp();
            loginFrame.dispose();


        });

        forgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ForgotPassword();
                loginFrame.dispose();
            }
        });

        // ========= Wipe data event Handling ======================
        freshStart.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int ask = JOptionPane.showConfirmDialog(loginFrame, "Are you sure you want to Erase All data \n and Fresh Start the Application");
                if (ask == 0) {
                    DbConnect db = new DbConnect();
                    Statement con = db.connection();
                    if(con == null){
                        new ConnectDatabase();
                        loginFrame.dispose();
                    }
                    try {
                        db.connection().executeUpdate("use coursework2");
                        db.connection().executeUpdate("drop table admins");
                        db.connection().executeUpdate("drop database coursework2");
                        JOptionPane.showMessageDialog(loginFrame, "Data wiped Successfully");
                        new ConnectDatabase();
                        loginFrame.dispose();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    StartApp.myFile.delete();
                    StartApp obj = new StartApp();

                }
            }
        });


        // =================== Frame Background =======================

        mainFrameImage = new ImageIcon("src/images/LoginFrame.png");
        LoginBackground = new JLabel();
        LoginBackground.setIcon(mainFrameImage);
        loginFrame.add(LoginBackground);
        LoginBackground.setBounds(0, 0, 1366, 720);


    }

    public void callEyeHide() {
        // ======================= Eye Hide ========================
        iconEyeHide = new ImageIcon("src/images/eyeHide.png");
        eyeHide = new JLabel();
        eyeHide.setIcon(iconEyeHide);
        loginFrame.add(eyeHide);
        eyeHide.setBounds(895, 377, 40, 40);
    }

    public void callEyeShow() {
        // ================ Eye Show ================================
        iconEyeShow = new ImageIcon("src/images/eyeShow.png");
        eyeShow = new JLabel();
        eyeShow.setIcon(iconEyeShow);
        loginFrame.add(eyeShow);
        eyeShow.setBounds(895, 377, 40, 40);
    }

}
