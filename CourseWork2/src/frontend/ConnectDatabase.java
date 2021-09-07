package frontend;

import backend.DbConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class ConnectDatabase {
    DbConnect db = new DbConnect();
    Font font = new Font("Poppins", Font.PLAIN, 16);
    Font font25 = new Font("Poppins", Font.PLAIN, 25);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    JFrame connectDatabaseFrame = new JFrame("Connect Database");
    JLabel connectDataBaseBackground, host, port, username, password, connectDatabaseTitle, eyeShow, eyeHide = new JLabel();
    JButton submitButton, loginButton = new JButton();
    JTextField portField, hostField, usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    ImageIcon mainFrameImage, iconEyeShow, submitButtonImage, loginButtonImage, iconEyeHide = new ImageIcon();

    ConnectDatabase() {
        settingFrame();
        connectDatabaseFrame.getContentPane().setBackground(Color.white);
        connectDatabaseFrame.setVisible(true);
        connectDatabaseFrame.setSize(1366, 820);
    }

    public void settingFrame() {

        // ============================Label===============================

        // ============ Label Title ============================
        connectDatabaseTitle = new JLabel("CONNECT DATABASE HERE");
        connectDatabaseTitle.setFont(font25);
        connectDatabaseFrame.add(connectDatabaseTitle);
        connectDatabaseTitle.setBounds(545, 70, 400, 30);

        // ============ Label Hostname ============================

        host = new JLabel("Hostname");
        host.setFont(font);
        connectDatabaseFrame.add(host);
        host.setBounds(500, 155, 200, 20);


        // ============ Label Port ============================

        port = new JLabel("Port");
        port.setFont(font);
        connectDatabaseFrame.add(port);
        port.setBounds(500, 250, 200, 20);


        // ============ Label Username ============================
        username = new JLabel("Username");
        username.setFont(font);
        connectDatabaseFrame.add(username);
        username.setBounds(500, 345, 200, 20);

        // ============ Label Password ============================
        password = new JLabel("Password");
        password.setFont(font);
        connectDatabaseFrame.add(password);
        password.setBounds(500, 438, 200, 20);


        // ====================== TextField =========================

        // =============== Host TextField =======================
        hostField = new JTextField();
        connectDatabaseFrame.add(hostField);
        hostField.setFont(font12);
        hostField.setBorder(BorderFactory.createLineBorder(Color.white));
        hostField.setBounds(525, 175, 400, 30);


        // =============== Port TextField =======================
        portField = new JTextField();
        connectDatabaseFrame.add(portField);
        portField.setFont(font12);
        portField.setBorder(BorderFactory.createLineBorder(Color.white));
        portField.setBounds(527, 265, 400, 30);


        // =============== Username TextField =======================
        usernameField = new JTextField();
        connectDatabaseFrame.add(usernameField);
        usernameField.setFont(font12);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.white));
        usernameField.setBounds(525, 362, 360, 30);

        // =============== Password TextField =======================
        passwordField = new JPasswordField();
        connectDatabaseFrame.add(passwordField);
        passwordField.setFont(font12);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.white));
        passwordField.setBounds(525, 454, 360, 30);

        // setting default value
        hostField.setText("localhost");
        portField.setText("3306");
        usernameField.setText("root");
        passwordField.setText("");


        // ====================== Button ===========================

        // ============= Submit Button =============================
        submitButton = new JButton();
        submitButtonImage = new ImageIcon("src/images/submit.png");
        submitButton.setIcon(submitButtonImage);
        connectDatabaseFrame.add(submitButton);
        submitButton.setBounds(550, 550, 144, 40);
        submitButton.setOpaque(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ============= Login Button =============================
        loginButton = new JButton();
        loginButtonImage = new ImageIcon("src/images/login_button.png");
        loginButton.setIcon(loginButtonImage);
        connectDatabaseFrame.add(loginButton);
        loginButton.setBounds(700, 550, 144, 40);
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

        // ==================== Logic Implementation =================

        loginButton.addActionListener(e -> {
            String fileName = StartApp.myFile.getPath();
            boolean fileExists = StartApp.myFile.exists();
            Path path = Path.of(fileName);
            try {
                if (fileExists) {

                    Scanner reader = new Scanner(path);
                    if (reader.hasNextLine()) {
                        Login loginObj = new Login();
                        connectDatabaseFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(connectDatabaseFrame, "Please Connect Database First");
                    }
                } else {
                    JOptionPane.showMessageDialog(connectDatabaseFrame, "Please Connect Database");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        submitButton.addActionListener(e -> {
            String host = hostField.getText();
            String port = portField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            String fileName = StartApp.myFile.getPath();
            System.out.println(fileName);
            try {

                FileWriter writeFile = new FileWriter(fileName);
                writeFile.write(host + "\r\n" + port + "\r\n" + username + "\r\n" + password);
                writeFile.close();

                DbConnect obj = new DbConnect();
                Statement con = obj.connection();
                if (con != null) {


                    String adminQuery = "CREATE TABLE `coursework2`.`admins` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `email` VARCHAR(200) NOT NULL , `username` VARCHAR(200) NOT NULL , `password` VARCHAR(1000) NOT NULL , PRIMARY KEY (`id`),  CONSTRAINT email_unique UNIQUE (email),CONSTRAINT username_unique UNIQUE (username))";
                    int adminConfirm = db.setUpConnection().executeUpdate(adminQuery);

                    String bookQuery = "CREATE TABLE `coursework2`.`books` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `bookName` VARCHAR(200) NOT NULL , `bookPrice` VARCHAR(200) NOT NULL , `bookEdition` VARCHAR(200) NOT NULL, `publishDate` VARCHAR(200) NOT NULL, `bookPublisher` VARCHAR(200) NOT NULL, `bookStatus` VARCHAR(200) NOT NULL , PRIMARY KEY (`id`))";
                    int booksConfirm = db.setUpConnection().executeUpdate(bookQuery);

                    String publisherQuery = "CREATE TABLE `coursework2`.`publishers` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `publisherName` VARCHAR(200) NOT NULL , `publisherEmail` VARCHAR(200) NOT NULL , `publisherContact` VARCHAR(200) NOT NULL, `publisherAddress` VARCHAR(200) NOT NULL, PRIMARY KEY (`id`))";
                    int publisherConfirm = db.setUpConnection().executeUpdate(publisherQuery);

                    if (adminConfirm == 0 && booksConfirm == 0 && publisherConfirm==0) {
                        JOptionPane.showMessageDialog(connectDatabaseFrame, "Connection Successful \nDatabase and Table created successfully");
                    } else {
                        System.out.println("Database connection successful but not able to create tables");
                    }
                    int ask = JOptionPane.showConfirmDialog(connectDatabaseFrame, "Please set up admin Login now");

                    if (ask == 0) {
                        new SignUp();
                        connectDatabaseFrame.dispose();
                    }

                } else {
                    JOptionPane.showMessageDialog(connectDatabaseFrame, "Connection Error \nPlease provide correct details");
                }


            } catch (IOException | SQLException ioException) {
                System.out.println("Error Writing Values");
                JOptionPane.showMessageDialog(connectDatabaseFrame, "Table already Created");
                int ask = JOptionPane.showConfirmDialog(connectDatabaseFrame, "Please set up admin Login now");

                if (ask == 0) {
                    new SignUp();
                    connectDatabaseFrame.dispose();
                }
            }


        });


        // =================== Frame Background =======================

        mainFrameImage = new ImageIcon("src/images/connect_database_frame.png");
        connectDataBaseBackground = new JLabel();
        connectDataBaseBackground.setIcon(mainFrameImage);
        connectDatabaseFrame.add(connectDataBaseBackground);
        connectDataBaseBackground.setBounds(0, 0, 1366, 720);


    }

    public void callEyeHide() {
        // ======================= Eye Hide ========================
        iconEyeHide = new ImageIcon("src/images/eyeHide.png");
        eyeHide = new JLabel();
        eyeHide.setIcon(iconEyeHide);
        connectDatabaseFrame.add(eyeHide);
        eyeHide.setBounds(895, 448, 40, 40);
    }

    public void callEyeShow() {
        // ================ Eye Show ================================
        iconEyeShow = new ImageIcon("src/images/eyeShow.png");
        eyeShow = new JLabel();
        eyeShow.setIcon(iconEyeShow);
        connectDatabaseFrame.add(eyeShow);
        eyeShow.setBounds(895, 448, 40, 40);
    }

}
