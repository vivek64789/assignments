package frontend;

import backend.DbConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


public class SignUp{
    DbConnect db = new DbConnect();
    Font font = new Font("Poppins", Font.PLAIN, 16);
    Font font25 = new Font("Poppins", Font.PLAIN, 25);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    JFrame signUpFrame = new JFrame("Sign Up");
    JLabel signUpBackground, email, username, password, confirmPassword, signUpTitle, eyeShow, eyeHide = new JLabel();
    JButton signUpButton, loginButton = new JButton();
    JTextField usernameField, emailField = new JTextField();
    JPasswordField passwordField, confirmPasswordField = new JPasswordField();
    ImageIcon mainFrameImage,iconEyeShow,signUpButtonImage,loginButtonImage, iconEyeHide= new ImageIcon();
    SignUp(){
        settingFrame();
        signUpFrame.getContentPane().setBackground(Color.white);
        signUpFrame.setVisible(true);
        signUpFrame.setSize(1366,820);
    }

    public void settingFrame(){

        // ============================Label===============================

        // ============ Label Title ============================
        signUpTitle = new JLabel("REGISTER HERE");
        signUpTitle.setFont(font25);
        signUpFrame.add(signUpTitle);
        signUpTitle.setBounds(625,70,300,30);

        // ============ Label Email ============================

        email = new JLabel("Email");
        email.setFont(font);
        signUpFrame.add(email);
        email.setBounds(500,140,200,20);


        // ============ Label Username ============================

        username = new JLabel("Username");
        username.setFont(font);
        signUpFrame.add(username);
        username.setBounds(500,245,200,20);


        // ============ Label Password ============================
        password = new JLabel("Password");
        password.setFont(font);
        signUpFrame.add(password);
        password.setBounds(500,360,200,20);

        // ============ Label Confirm Password ============================
        confirmPassword = new JLabel("Confirm Password");
        confirmPassword.setFont(font);
        signUpFrame.add(confirmPassword);
        confirmPassword.setBounds(500,470,200,20);


        // ====================== TextField =========================

        // =============== Email TextField =======================
        emailField = new JTextField();
        signUpFrame.add(emailField);
        emailField.setFont(font12);
        emailField.setBorder(BorderFactory.createLineBorder(Color.white));
        emailField.setBounds(525,155,400,30);


        // =============== Username TextField =======================
        usernameField = new JTextField();
        signUpFrame.add(usernameField);
        usernameField.setFont(font12);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.white));
        usernameField.setBounds(525,265,400,30);


        // =============== Password TextField =======================
        passwordField = new JPasswordField();
        signUpFrame.add(passwordField);
        passwordField.setFont(font12);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.white));
        passwordField.setBounds(525,378,360,30);

        // =============== Confirm Password TextField =======================
        confirmPasswordField = new JPasswordField();
        signUpFrame.add(confirmPasswordField);
        confirmPasswordField.setFont(font12);
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.white));
        confirmPasswordField.setBounds(525,489,360,30);



        // ====================== Button ===========================

        // ============= Submit Button =============================
        signUpButton = new JButton();
        signUpButtonImage = new ImageIcon("src/images/submit.png");
        signUpButton.setIcon(signUpButtonImage);
        signUpFrame.add(signUpButton);
        signUpButton.setBounds(550,550,144,40);
        signUpButton.setOpaque(false);
        signUpButton.setContentAreaFilled(false);
        signUpButton.setBorderPainted(false);
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ============= Login Button =============================
        loginButton = new JButton();
        loginButtonImage = new ImageIcon("src/images/login_button.png");
        loginButton.setIcon(loginButtonImage);
        signUpFrame.add(loginButton);
        loginButton.setBounds(700,550,144,40);
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

        // ==================== Logic Implementation =================

        loginButton.addActionListener(e -> {

            Login loginObj = new Login();
            signUpFrame.dispose();

        });

        signUpButton.addActionListener(e -> {
            String email = emailField.getText();
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
            char[] confirmPassword = confirmPasswordField.getPassword();
            StringBuffer sb = new StringBuffer();
            StringBuffer sbconfirm = new StringBuffer();

            for(int i=0; i<confirmPassword.length; i++){

                sb.append(confirmPassword[i]);
            }
            String finalPassword = sb.toString();

            for(int i=0; i<password.length; i++){

                sbconfirm.append(password[i]);
            }
            String finalConfirmPassword = sbconfirm.toString();


            if( username.contains(" ")){
                JOptionPane.showMessageDialog(signUpFrame,"Username Field should not Contains Space");
            }else if(email.length()==0 || username.length()==0 || finalPassword.length()==0 || finalConfirmPassword.length()==0 ){
                JOptionPane.showMessageDialog(signUpFrame, "Field Can not be empty");
            }
            else if( email.contains(" ")){
                JOptionPane.showMessageDialog(signUpFrame,"Email Field should not Contains Space");
            } else if (!email.contains("@")) {
                JOptionPane.showMessageDialog(signUpFrame,"Email is Invalid");
            }else{
                if(finalPassword.equals(finalConfirmPassword)){

                    String query = "insert into admins(email,username,password)values('"+email+"','"+username+"','"+ finalPassword +"')";
                    System.out.println(query);

                    try {
                        int res  = db.connection().executeUpdate(query);
                        System.out.println(res);
                        JOptionPane.showMessageDialog(signUpFrame,"Account created successfully");
                        new Login();
                        signUpFrame.dispose();
                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
                        JOptionPane.showMessageDialog(signUpFrame,"Username already taken");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(signUpFrame,"Password didn't matched");
                }
            }

        });


        // =================== Frame Background =======================

        mainFrameImage = new ImageIcon("src/images/admin_setup_frame.png");
        signUpBackground = new JLabel();
        signUpBackground.setIcon(mainFrameImage);
        signUpFrame.add(signUpBackground);
        signUpBackground.setBounds(0,0,1366,720);



    }

    public void callEyeHide(){
        // ======================= Eye Hide ========================
        iconEyeHide = new ImageIcon("src/images/eyeHide.png");
        eyeHide = new JLabel();
        eyeHide.setIcon(iconEyeHide);
        signUpFrame.add(eyeHide);
        eyeHide.setBounds(895,372,40,40);
    }

    public void callEyeShow(){
        // ================ Eye Show ================================
        iconEyeShow = new ImageIcon("src/images/eyeShow.png");
        eyeShow = new JLabel();
        eyeShow.setIcon(iconEyeShow);
        signUpFrame.add(eyeShow);
        eyeShow.setBounds(895,372,40,40);
    }

}
