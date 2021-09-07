package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AdminSetup{
    Font font = new Font("Poppins", Font.PLAIN, 16);
    Font font25 = new Font("Poppins", Font.PLAIN, 25);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    JFrame loginFrame = new JFrame("Login");
    JLabel LoginBackground, username, password, forgotPassword, loginTitle, eyeShow, eyeHide = new JLabel();
    JButton loginButton, signUpButton = new JButton();
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    ImageIcon mainFrameImage,iconEyeShow, iconEyeHide, loginButtonImage, signUpButtonImage  = new ImageIcon();
    AdminSetup(){
        settingFrame();

        loginFrame.getContentPane().setBackground(Color.white);
        loginFrame.setVisible(true);
        loginFrame.setSize(1366,820);
    }

    public void settingFrame(){

        // ============================Label===============================

        // ============ Label Username ============================
        loginTitle = new JLabel("LOGIN HERE");
        loginTitle.setFont(font25);
        loginFrame.add(loginTitle);
        loginTitle.setBounds(625,120,300,30);

        // ============ Label Username ============================

        username = new JLabel("Username");
        username.setFont(font);
        loginFrame.add(username);
        username.setBounds(500,245,200,20);


        // ============ Label Password ============================
        password = new JLabel("Password");
        password.setFont(font);
        loginFrame.add(password);
        password.setBounds(500,360,200,20);

        // ============ Forgot Password ============================
        password = new JLabel("Forgot Password?");
        password.setFont(font);
        loginFrame.add(password);
        password.setBounds(500,450,200,20);

        // ====================== TextField =========================

        // =============== Username TextField =======================
        usernameField = new JTextField();
        loginFrame.add(usernameField);
        usernameField.setFont(font12);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.white));
        usernameField.setBounds(525,268,400,30);


        // =============== Password TextField =======================
        passwordField = new JPasswordField();
        loginFrame.add(passwordField);
        passwordField.setFont(font12);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.white));
        passwordField.setBounds(525,383,360,30);



        // ====================== Button ===========================

        // ============= Login Button =============================
        loginButton = new JButton();
        loginButtonImage = new ImageIcon("src/images/login_button.png");
        loginButton.setIcon(loginButtonImage);
        loginFrame.add(loginButton);
        loginButton.setBounds(550,500,144,40);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ============= Login Button =============================
        signUpButton = new JButton();
        signUpButtonImage = new ImageIcon("src/images/register_button.png");
        signUpButton.setIcon(signUpButtonImage);
        loginFrame.add(signUpButton);
        signUpButton.setBounds(700,500,144,40);
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
                passwordField.setEchoChar((char)0);
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

            System.out.println("dfgh");


        });

        // ============ Register Button Event handling==========

        signUpButton.addActionListener(e -> {

            SignUp obj = new SignUp();
            loginFrame.dispose();


        });


        // =================== Frame Background =======================

        mainFrameImage = new ImageIcon("src/images/connect_database_frame.png");
        LoginBackground = new JLabel();
        LoginBackground.setIcon(mainFrameImage);
        loginFrame.add(LoginBackground);
        LoginBackground.setBounds(0,0,1366,720);



    }

    public void callEyeHide(){
        // ======================= Eye Hide ========================
        iconEyeHide = new ImageIcon("src/images/eyeHide.png");
        eyeHide = new JLabel();
        eyeHide.setIcon(iconEyeHide);
        loginFrame.add(eyeHide);
        eyeHide.setBounds(895,377,40,40);
    }

    public void callEyeShow(){
        // ================ Eye Show ================================
        iconEyeShow = new ImageIcon("src/images/eyeShow.png");
        eyeShow = new JLabel();
        eyeShow.setIcon(iconEyeShow);
        loginFrame.add(eyeShow);
        eyeShow.setBounds(895,377,40,40);
    }

}
