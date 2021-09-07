package frontend;

import backend.Algorithm;
import backend.DbConnect;
import backend.MergeSort;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

import com.formdev.flatlaf.FlatIntelliJLaf;


public class AdminDashboard {
    Font font25 = new Font("Poppins", Font.PLAIN, 25);
    Font font45 = new Font("Poppins", Font.BOLD, 45);
    Font font12 = new Font("Poppins", Font.PLAIN, 12);
    Font font14 = new Font("Poppins", Font.BOLD, 13);
    JFrame adminDashboardFrame = new JFrame("adminDashboard");
    JPanel sidebarPanel = new JPanel();
    JPanel generalPanel = new JPanel();
    JPanel bookPanel = new JPanel();
    JPanel addBookPanel = new JPanel();
    JPanel updateBookPanel = new JPanel();
    JPanel viewBookPanel = new JPanel();
    JPanel addPublisherPanel = new JPanel();
    JPanel updatePublisherPanel = new JPanel();
    JPanel viewPublisherPanel = new JPanel();
    JPanel publisherPanel = new JPanel();
    JPanel searchByPanel = new JPanel();
    JPanel sortByPanel = new JPanel();
    JTable viewBookTable, viewPublisherTable = new JTable();
    JLabel adminDashboardBackground, book, bookIconLabel, publisher,
            publisherIconLabel, general, generalIconLabel, generalPanelLabel, forgotPassword, logout, logoutIconLabel,
            addBook, addBookIconLabel, bookPanelLabel, publisherPanelLabel, addPublisher, addPublisherIconLabel,
            viewBookIconLabel, viewBook, viewPublisherIconLabel, viewPublisher,
            adminDashboardTitle, generalDataLabel,
            totalAdmins, totalBooks, totalPublishers, totalAdminsData, totalBooksData, totalPublishersData,
            addBookPanelLabel, bookName, bookPrice, bookEdition, bookPublishDate, bookPublisher, bookStatus,
            publisherName, publisherEmail, publisherContact, publisherAddress,
            viewBookPanelLabel, addPublisherPanelLabel, viewPublisherPanelLabel,
            updateBookPanelLabel = new JLabel();
    JButton submitBookButton, submitPublisherButton, updateBookButton, deleteBookButton, updatePublisherButton,
            searchBookButton, viewBookAllButton, sortBookButton,viewBookAllButtonSort,
            deletePublisherButton = new JButton();
    JTextField bookNameField, bookPriceField, bookEditionField, bookPublishDateField,
            publisherNameTextField, publisherEmailTextField, publisherContactTextField, publisherAddressTextField,
            searchBookField
                    = new JTextField();
    JComboBox<String> bookPublisherField, bookStatusField, byBookField, searchBookByField, sortBookByField, sortByOrderField = new JComboBox<String>();
    JPasswordField passwordField = new JPasswordField();
    ImageIcon mainFrameImage, dashboardIcon, bookIcon, publisherIcon, generalIcon, iconEyeShow,
            generalDataIcon,
            iconEyeHide, logoutIcon,
            viewBookIcon, bookPanelIcon, publisherPanelIcon, addPublisherIcon, viewPublisherIcon,
            addBookPanelIcon, generalPanelIcon, viewBookPanelIcon, addPublisherPanelIcon, viewPublisherPanelIcon,
            updateBookPanelIcon
                    = new ImageIcon();
    backend.Algorithm algorithmObj = new Algorithm();
    backend.MergeSort mergeSortObj = new MergeSort();

    public AdminDashboard() {
        settingFrame();
        adminDashboardFrame.getContentPane().setBackground(Color.white);
        adminDashboardFrame.setVisible(true);
        adminDashboardFrame.setResizable(false);
        adminDashboardFrame.setSize(1380, 750);
    }

    public void settingFrame() {
        //      ================= View Book Panel ================================
        String[] booksColumn = {"ID", "Name", "Price", "Edition", "Publish Date", "Publisher", "Status"};
        DefaultTableModel model = new DefaultTableModel(booksColumn, 0);
        viewBookTable = new JTable(model);

        JScrollPane sp = new JScrollPane(viewBookTable);
        viewBookPanel.add(sp);
        sp.setBounds(3, 68, 1006, 420);

//        fetchAllBooks();

        Cursor hand = new Cursor(Cursor.HAND_CURSOR);

        // ===================== General ==============================

        generalIconLabel = new JLabel();
        generalIconLabel.setCursor(hand);
        generalIcon = new ImageIcon("src/images/dashboard.png");
        generalIconLabel.setIcon(generalIcon);
        sidebarPanel.add(generalIconLabel);
        generalIconLabel.setBounds(40, 40, 40, 40);

        general = new JLabel("General");
        general.setCursor(hand);
        sidebarPanel.add(general);
        general.setFont(font12);
        general.setBounds(80, 45, 100, 30);

        // ===================== Books ==============================

        bookIconLabel = new JLabel();
        bookIconLabel.setCursor(hand);
        bookIcon = new ImageIcon("src/images/book.png");
        bookIconLabel.setIcon(bookIcon);
        sidebarPanel.add(bookIconLabel);
        bookIconLabel.setBounds(40, 110, 40, 40);


        book = new JLabel("Books");
        book.setCursor(hand);
        sidebarPanel.add(book);
        book.setFont(font12);
        book.setBounds(80, 115, 100, 30);


        // ===================== View Book ==============================

        viewBookIconLabel = new JLabel();
        viewBookIconLabel.setCursor(hand);
        viewBookIcon = new ImageIcon("src/images/view.png");
        viewBookIconLabel.setIcon(viewBookIcon);
        sidebarPanel.add(viewBookIconLabel);
        viewBookIconLabel.setBounds(80, 200, 40, 40);
        viewBookIconLabel.setVisible(false);

        viewBook = new JLabel("View Book");
        viewBook.setCursor(hand);
        sidebarPanel.add(viewBook);
        viewBook.setFont(font12);
        viewBook.setBounds(120, 205, 100, 30);
        viewBook.setVisible(false);


        // ===================== Add Book ==============================

        addBookIconLabel = new JLabel();
        addBookIconLabel.setCursor(hand);
        viewBookIcon = new ImageIcon("src/images/add.png");
        addBookIconLabel.setIcon(viewBookIcon);
        sidebarPanel.add(addBookIconLabel);
        addBookIconLabel.setBounds(80, 155, 40, 40);
        addBookIconLabel.setVisible(false);

        addBook = new JLabel("Add Book");
        addBook.setCursor(hand);
        sidebarPanel.add(addBook);
        addBook.setFont(font12);
        addBook.setBounds(120, 160, 100, 30);
        addBook.setVisible(false);

        // ===================== Publisher ==============================

        publisherIconLabel = new JLabel();
        publisherIconLabel.setCursor(hand);
        publisherIcon = new ImageIcon("src/images/publisher.png");
        publisherIconLabel.setIcon(publisherIcon);
        sidebarPanel.add(publisherIconLabel);
        publisherIconLabel.setBounds(40, 180, 40, 40);

        publisher = new JLabel("Publisher");
        publisher.setCursor(hand);
        sidebarPanel.add(publisher);
        publisher.setFont(font12);
        publisher.setBounds(80, 185, 100, 30);

        // ===================== Add Publisher ==============================

        addPublisherIconLabel = new JLabel();
        addPublisherIconLabel.setCursor(hand);
        addPublisherIcon = new ImageIcon("src/images/add.png");
        addPublisherIconLabel.setIcon(addPublisherIcon);
        sidebarPanel.add(addPublisherIconLabel);
        addPublisherIconLabel.setBounds(80, 220, 40, 40);
        addPublisherIconLabel.setVisible(false);

        addPublisher = new JLabel("Add Publisher");
        addPublisher.setCursor(hand);
        sidebarPanel.add(addPublisher);
        addPublisher.setFont(font12);
        addPublisher.setBounds(120, 225, 100, 30);
        addPublisher.setVisible(false);


        // ===================== View Publisher ==============================

        viewPublisherIconLabel = new JLabel();
        viewPublisherIconLabel.setCursor(hand);
        viewPublisherIcon = new ImageIcon("src/images/view.png");
        viewPublisherIconLabel.setIcon(viewPublisherIcon);
        sidebarPanel.add(viewPublisherIconLabel);
        viewPublisherIconLabel.setBounds(80, 265, 40, 40);
        viewPublisherIconLabel.setVisible(false);

        viewPublisher = new JLabel("View Publisher");
        viewPublisher.setCursor(hand);
        sidebarPanel.add(viewPublisher);
        viewPublisher.setFont(font12);
        viewPublisher.setBounds(120, 270, 100, 30);
        viewPublisher.setVisible(false);

        // ================ Sidebar ===========================

        adminDashboardFrame.add(sidebarPanel);
        sidebarPanel.setBounds(23, 40, 257, 630);
        sidebarPanel.setOpaque(false);
        sidebarPanel.setLayout(null);

        // ================= general Panel ======================

        adminDashboardFrame.add(generalPanel);
//        generalPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        generalPanel.setBounds(300, 70, 1020, 600);
        generalPanel.setOpaque(false);
        generalPanel.setLayout(null);
//        generalPanel.setVisible(false);

        totalAdmins = new JLabel("Admins");
        generalPanel.add(totalAdmins);
        totalAdmins.setFont(font25);
        totalAdmins.setForeground(Color.black);
        totalAdmins.setBounds(195, 350, 100, 30);

        totalAdminsData = new JLabel();
        generalPanel.add(totalAdminsData);
        totalAdminsData.setFont(font45);
        totalAdminsData.setForeground(Color.black);
        totalAdminsData.setBounds(220, 260, 100, 60);

        totalBooks = new JLabel("Books");
        generalPanel.add(totalBooks);
        totalBooks.setFont(font25);
        totalBooks.setForeground(Color.black);
        totalBooks.setBounds(470, 350, 150, 30);

        totalBooksData = new JLabel();
        generalPanel.add(totalBooksData);
        totalBooksData.setFont(font45);
        totalBooksData.setForeground(Color.black);
        totalBooksData.setBounds(480, 260, 150, 60);

        totalPublishers = new JLabel("Publishers");
        generalPanel.add(totalPublishers);
        totalPublishers.setFont(font25);
        totalPublishers.setForeground(Color.black);
        totalPublishers.setBounds(710, 350, 150, 30);

        totalPublishersData = new JLabel();
        generalPanel.add(totalPublishersData);
        totalPublishersData.setFont(font45);
        totalPublishersData.setForeground(Color.black);
        totalPublishersData.setBounds(745, 260, 150, 60);

        refreshDashboardData();

        generalDataLabel = new JLabel();
        generalDataIcon = new ImageIcon("src/images/general_data_inner_frame.png");
        generalDataLabel.setIcon(generalDataIcon);
        generalPanel.add(generalDataLabel);
        generalDataLabel.setBounds(0, 0, 1020, 600);

        generalPanelLabel = new JLabel();
        generalPanelIcon = new ImageIcon("src/images/inner_frame.png");
        generalPanelLabel.setIcon(generalPanelIcon);
        generalPanel.add(generalPanelLabel);
        generalPanelLabel.setBounds(0, 0, 1020, 600);

        // ================= add book panel label and entry fields ==============

        bookName = new JLabel("Book Name:");
        bookName.setFont(font14);
        bookName.setForeground(Color.decode("#404040"));
        addBookPanel.add(bookName);
        bookName.setBounds(280, 70, 100, 40);

        bookNameField = new JTextField();
        bookNameField.setFont(font12);
        addBookPanel.add(bookNameField);
        bookNameField.setBounds(280, 103, 450, 25);
        bookNameField.setBorder(BorderFactory.createLineBorder(Color.white));

        bookPrice = new JLabel("Book Price:");
        bookPrice.setFont(font14);
        bookPrice.setForeground(Color.decode("#404040"));
        addBookPanel.add(bookPrice);
        bookPrice.setBounds(280, 147, 100, 40);

        bookPriceField = new JTextField();
        bookPriceField.setFont(font12);
        addBookPanel.add(bookPriceField);
        bookPriceField.setBounds(280, 178, 450, 25);
        bookPriceField.setBorder(BorderFactory.createLineBorder(Color.white));

        bookEdition = new JLabel("Book Edition:");
        bookEdition.setFont(font14);
        bookEdition.setForeground(Color.decode("#404040"));
        addBookPanel.add(bookEdition);
        bookEdition.setBounds(280, 222, 100, 40);

        bookEditionField = new JTextField();
        bookEditionField.setFont(font12);
        addBookPanel.add(bookEditionField);
        bookEditionField.setBounds(280, 255, 450, 25);
        bookEditionField.setBorder(BorderFactory.createLineBorder(Color.white));

        bookPublishDate = new JLabel("Publish Date:");
        bookPublishDate.setFont(font14);
        bookPublishDate.setForeground(Color.decode("#404040"));
        addBookPanel.add(bookPublishDate);
        bookPublishDate.setBounds(280, 297, 150, 40);

        bookPublishDateField = new JTextField();
        bookPublishDateField.setFont(font12);
        addBookPanel.add(bookPublishDateField);
        bookPublishDateField.setBounds(280, 330, 450, 25);
        bookPublishDateField.setBorder(BorderFactory.createLineBorder(Color.white));

        bookPublisher = new JLabel("Book Publisher:");
        bookPublisher.setFont(font14);
        bookPublisher.setForeground(Color.decode("#404040"));
        addBookPanel.add(bookPublisher);
        bookPublisher.setBounds(280, 370, 150, 40);


        bookPublisherField = new JComboBox<>();
        bookPublisherField.setFont(font12);
        addBookPanel.add(bookPublisherField);
        bookPublisherField.setBounds(280, 403, 450, 30);

        bookStatus = new JLabel("Book Status:");
        bookStatus.setFont(font14);
        bookStatus.setForeground(Color.decode("#404040"));
        addBookPanel.add(bookStatus);
        bookStatus.setBounds(280, 435, 150, 40);

        String[] bookStatusFieldValue = {"Available", "Out Of Stock"};
        bookStatusField = new JComboBox<>(bookStatusFieldValue);
        bookStatusField.setFont(font12);
        addBookPanel.add(bookStatusField);
        bookStatusField.setBounds(280, 468, 450, 30);

        //        =============== Add Book Button =================
        submitBookButton = new JButton("Submit");
//        submitButtonIcon = new ImageIcon("src/images/submit.png");
//        submitButton.setIcon(submitButtonIcon);
        submitBookButton.setFont(font14);
        submitBookButton.setForeground(Color.decode("#404040"));
        addBookPanel.add(submitBookButton);
        submitBookButton.setBounds(430, 510, 150, 40);
//        submitButton.setOpaque(false);
//        submitButton.setContentAreaFilled(false);
//        submitButton.setBorderPainted(false);
        submitBookButton.setCursor(hand);


//      ================= Add Book Panel ================================

        bookPanel.add(addBookPanel);
//        bookPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        addBookPanel.setBounds(0, 0, 1020, 600);
        addBookPanel.setOpaque(false);
        addBookPanel.setLayout(null);
        addBookPanel.setVisible(false);

        addBookPanelLabel = new JLabel();
        addBookPanelIcon = new ImageIcon("src/images/add_book_frame.png");
        addBookPanelLabel.setIcon(addBookPanelIcon);
        addBookPanel.add(addBookPanelLabel);
        addBookPanelLabel.setBounds(0, 0, 1020, 600);


//      ================= update Book Panel ================================
        bookPanel.add(updateBookPanel);
//        bookPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        updateBookPanel.setBounds(0, 0, 1020, 600);
        updateBookPanel.setOpaque(false);
        updateBookPanel.setLayout(null);
        updateBookPanel.setVisible(false);

        updateBookPanelLabel = new JLabel();
        updateBookPanelIcon = new ImageIcon("src/images/add_book_frame.png");
        updateBookPanelLabel.setIcon(updateBookPanelIcon);
        updateBookPanel.add(updateBookPanelLabel);
        updateBookPanelLabel.setBounds(0, 0, 1020, 600);

        //        =============== Update Book Button =================
        updateBookButton = new JButton("Update");
        updateBookButton.setFont(font14);
        updateBookButton.setForeground(Color.decode("#404040"));
        viewBookPanel.add(updateBookButton);
        updateBookButton.setBounds(300, 510, 150, 40);
        updateBookButton.setCursor(hand);

        //        =============== Update Book Button =================
        deleteBookButton = new JButton("Delete");
        deleteBookButton.setFont(font14);
        deleteBookButton.setForeground(Color.decode("#404040"));
        viewBookPanel.add(deleteBookButton);
        deleteBookButton.setBounds(500, 510, 150, 40);
        deleteBookButton.setCursor(hand);

//        Search
        String[] byBookFieldData = {"Search by", "Sort by"};
        byBookField = new JComboBox<>(byBookFieldData);
        viewBookPanel.add(byBookField);
        byBookField.setBounds(50, 25, 150, 30);

        searchByAction();
        sortByPanel.setVisible(true);

        sortByAction();
        sortByPanel.setVisible(true);

        byBookField.addActionListener(e -> {

            int byBookData = byBookField.getSelectedIndex();
            System.out.println(byBookData);
            if (byBookData == 0) {
                sortByPanel.setVisible(false);
                searchByPanel.setVisible(true);


            } else if (byBookData == 1) {
                searchByPanel.setVisible(false);
                sortByPanel.setVisible(true);

            }

        });


        bookPanel.add(viewBookPanel);
//        bookPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        viewBookPanel.setBounds(0, 0, 1020, 600);
        viewBookPanel.setOpaque(false);
        viewBookPanel.setLayout(null);
        viewBookPanel.setVisible(false);


        viewBookPanelLabel = new JLabel();
        viewBookPanelIcon = new ImageIcon("src/images/viewFrame.png");
        viewBookPanelLabel.setIcon(viewBookPanelIcon);
        viewBookPanel.add(viewBookPanelLabel);
        viewBookPanelLabel.setBounds(0, 0, 1020, 600);

//      ================= Book Panel ================================

        adminDashboardFrame.add(bookPanel);
//        bookPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        bookPanel.setBounds(300, 70, 1020, 600);
        bookPanel.setOpaque(false);
        bookPanel.setLayout(null);
        bookPanel.setVisible(false);

        bookPanelLabel = new JLabel();
        bookPanelIcon = new ImageIcon("src/images/inner_frame.png");
        bookPanelLabel.setIcon(bookPanelIcon);
        bookPanel.add(bookPanelLabel);
        bookPanelLabel.setBounds(0, 0, 1020, 600);

//        add publisher labels and entry

        publisherName = new JLabel("Publisher Name:");
        addPublisherPanel.add(publisherName);
        publisherName.setFont(font14);
        publisherName.setForeground(Color.decode("#404040"));
        publisherName.setBounds(280, 100, 150, 30);

        publisherNameTextField = new JTextField();
        addPublisherPanel.add(publisherNameTextField);
        publisherNameTextField.setFont(font12);
        publisherNameTextField.setBounds(280, 125, 450, 40);


        publisherEmail = new JLabel("Publisher Email:");
        addPublisherPanel.add(publisherEmail);
        publisherEmail.setFont(font14);
        publisherEmail.setForeground(Color.decode("#404040"));
        publisherEmail.setBounds(280, 175, 150, 30);

        publisherEmailTextField = new JTextField();
        addPublisherPanel.add(publisherEmailTextField);
        publisherEmailTextField.setFont(font12);
        publisherEmailTextField.setBounds(280, 200, 450, 40);


        publisherContact = new JLabel("Publisher Contact Number:");
        addPublisherPanel.add(publisherContact);
        publisherContact.setFont(font14);
        publisherContact.setForeground(Color.decode("#404040"));
        publisherContact.setBounds(280, 250, 200, 30);

        publisherContactTextField = new JTextField();
        addPublisherPanel.add(publisherContactTextField);
        publisherContactTextField.setFont(font12);
        publisherContactTextField.setBounds(280, 275, 450, 40);


        publisherAddress = new JLabel("Publisher Address:");
        addPublisherPanel.add(publisherAddress);
        publisherAddress.setFont(font14);
        publisherAddress.setForeground(Color.decode("#404040"));
        publisherAddress.setBounds(280, 325, 200, 30);

        publisherAddressTextField = new JTextField();
        addPublisherPanel.add(publisherAddressTextField);
        publisherAddressTextField.setFont(font12);
        publisherAddressTextField.setBounds(280, 350, 450, 40);


        //        =============== Add Publisher Button =================
        submitPublisherButton = new JButton("Submit");
        submitPublisherButton.setFont(font14);
        submitPublisherButton.setForeground(Color.decode("#016316"));
        addPublisherPanel.add(submitPublisherButton);
        submitPublisherButton.setBounds(430, 410, 150, 40);
        submitPublisherButton.setCursor(hand);


        // ================ add Publisher Panel =========================

        publisherPanel.add(addPublisherPanel);
        addPublisherPanel.setBounds(0, 0, 1020, 600);
        addPublisherPanel.setOpaque(false);
        addPublisherPanel.setLayout(null);
        addPublisherPanel.setVisible(false);

        addPublisherPanelLabel = new JLabel();
        addPublisherPanelIcon = new ImageIcon("src/images/addPublisherFrame.png");
        addPublisherPanelLabel.setIcon(addPublisherPanelIcon);
        addPublisherPanel.add(addPublisherPanelLabel);
        addPublisherPanelLabel.setBounds(0, 0, 1020, 600);

        // ================ update Publisher Panel =========================

        publisherPanel.add(updatePublisherPanel);
        updatePublisherPanel.setBounds(0, 0, 1020, 600);
        updatePublisherPanel.setOpaque(false);
        updatePublisherPanel.setLayout(null);
        updatePublisherPanel.setVisible(false);

        addPublisherPanelLabel = new JLabel();
        addPublisherPanelIcon = new ImageIcon("src/images/addPublisherFrame.png");
        addPublisherPanelLabel.setIcon(addPublisherPanelIcon);
        addPublisherPanel.add(addPublisherPanelLabel);
        addPublisherPanelLabel.setBounds(0, 0, 1020, 600);


        viewPublisherTableAction();

        // ================ Publisher =========================

        adminDashboardFrame.add(publisherPanel);
//        publisherPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        publisherPanel.setBounds(300, 70, 1020, 600);
        publisherPanel.setOpaque(false);
        publisherPanel.setLayout(null);
        publisherPanel.setVisible(false);

        publisherPanelLabel = new JLabel();
        publisherPanelIcon = new ImageIcon("src/images/inner_frame.png");
        publisherPanelLabel.setIcon(publisherPanelIcon);
        publisherPanel.add(publisherPanelLabel);
        publisherPanelLabel.setBounds(0, 0, 1020, 600);


        // ========================= Logout ===========================

        logoutIconLabel = new JLabel();
        logoutIconLabel.setCursor(hand);
        logoutIcon = new ImageIcon("src/images/logout.png");
        logoutIconLabel.setIcon(logoutIcon);
        adminDashboardFrame.add(logoutIconLabel);
        logoutIconLabel.setBounds(1300, 25, 40, 40);

        logout = new JLabel("Logout");
        logout.setCursor(hand);
        adminDashboardFrame.add(logout);
        logout.setFont(font12);
        logout.setBounds(1250, 25, 100, 40);
        logout.setForeground(Color.RED);


        // ===================== Admin Dashboard ======================

        adminDashboardTitle = new JLabel("ADMIN DASHBOARD");
        adminDashboardTitle.setFont(font25);
        adminDashboardFrame.add(adminDashboardTitle);
        adminDashboardTitle.setBounds(640, 20, 400, 50);


        // =================== Frame Background =======================

        mainFrameImage = new ImageIcon("src/images/admin_dashboard_sidebar_frame2psd.png");
        adminDashboardBackground = new JLabel();
        adminDashboardBackground.setIcon(mainFrameImage);
        adminDashboardFrame.add(adminDashboardBackground);
        adminDashboardBackground.setBounds(0, 0, 1366, 720);


        // ================ Logic and Action Listener=====================================


        updateBookButton.addActionListener(e -> {
            updateBookButtonAction();
        });

//        searchBookButton.addActionListener(e -> {
//            searchBookIdAction();
//        });

        deleteBookButton.addActionListener(e -> {
            try {
                deleteBookButtonAction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        submitBookButton.addActionListener(e -> {

            try {
                submitBookButtonAction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        updatePublisherButton.addActionListener(e -> {
            updatePublisherButtonAction();
        });

        submitPublisherButton.addActionListener(e -> {

            try {
                submitPublisherButtonAction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        deletePublisherButton.addActionListener(e -> {
            try {
                deletePublisherButtonAction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login();
                adminDashboardFrame.dispose();
            }
        });

        generalIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                generalAction();

            }
        });
        general.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                generalAction();

            }
        });

        bookIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                bookAction();

            }
        });

        book.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                bookAction();

            }
        });

        addBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                addBookAction();

            }
        });
        addBookIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                addBookAction();

            }
        });

        viewBookIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                viewBookAction();

            }
        });

        viewBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                viewBookAction();
                refreshBooks();

            }
        });

        publisherIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                publisherAction();

            }
        });

        publisher.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                publisherAction();

            }
        });

        addPublisher.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                addPublisherAction();

            }
        });

        viewPublisher.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                viewPublisherAction();
                refreshPublishers();

            }
        });

        addPublisherIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                addPublisherAction();

            }
        });

        viewPublisherIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                viewPublisherAction();

            }
        });
    }

    public void searchByAction() {

        String[] searchBookByFieldData = {"Book ID", "Book Name", "Publisher Name", "Publish Date"};
        searchBookByField = new JComboBox<>(searchBookByFieldData);
        searchByPanel.add(searchBookByField);
        searchBookByField.setSelectedIndex(0);
        searchBookByField.setBounds(0, 5, 150, 30);

        searchBookField = new JTextField();
        searchByPanel.add(searchBookField);
        searchBookField.setBounds(160, 5, 350, 30);

        searchBookButton = new JButton("Search");
        searchByPanel.add(searchBookButton);
        searchBookButton.setBounds(520, 5, 100, 30);

        viewBookAllButton = new JButton("View All");
        searchByPanel.add(viewBookAllButton);
        viewBookAllButton.setBounds(630, 5, 100, 30);


        searchByActionCondition();


        viewBookAllButton.addActionListener(e -> {

            refreshBooks();
        });

        viewBookPanel.add(searchByPanel);
//        searchByPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        searchByPanel.setBounds(220, 20, 750, 40);
        searchByPanel.setOpaque(false);
        searchByPanel.setLayout(null);
        searchByPanel.setVisible(true);
    }


    public void sortByAction() {

        String[] sortBookByFieldData = {"Book ID","Book Name", "Publisher Name", "Book Publish Date"};
        sortBookByField = new JComboBox<>(sortBookByFieldData);
        sortByPanel.add(sortBookByField);
        sortBookByField.setBounds(0, 5, 150, 30);

        String[] sortBookByOrderData = {"Ascending", "Descending"};
        sortByOrderField = new JComboBox<>(sortBookByOrderData);
        sortByPanel.add(sortByOrderField);
        sortByOrderField.setBounds(160, 5, 350, 30);


        sortBookButton = new JButton("Sort");
        sortByPanel.add(sortBookButton);
        sortBookButton.setBounds(520, 5, 100, 30);


        viewBookAllButtonSort = new JButton("View All");
        sortByPanel.add(viewBookAllButtonSort);
        viewBookAllButtonSort.setBounds(630, 5, 100, 30);

        sortByActionValidation();
        viewBookAllButtonSort.addActionListener(e -> {
            refreshBooks();
        });

        viewBookPanel.add(sortByPanel);
//        sortByPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        sortByPanel.setBounds(220, 20, 750, 40);
        sortByPanel.setOpaque(false);
        sortByPanel.setLayout(null);
        sortByPanel.setVisible(true);
    }


    public void updatePublisherButtonAction() {
        int selectedBookRow = viewPublisherTable.getSelectedRow();
        if (selectedBookRow >= 0) {
            TableModel model = viewPublisherTable.getModel();
            String publisherID = (String) model.getValueAt(selectedBookRow, 0);
            String publisherName = (String) model.getValueAt(selectedBookRow, 1);
            String publisherEmail = (String) model.getValueAt(selectedBookRow, 2);
            String publisherContact = (String) model.getValueAt(selectedBookRow, 3);
            String publisherAddress = (String) model.getValueAt(selectedBookRow, 4);

            new UpdatePublisher(publisherID, publisherName, publisherEmail, publisherContact, publisherAddress);
        } else {
            JOptionPane.showMessageDialog(viewBookPanel, "Please Select Row you want to Update", "Row Not Selected", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateBookButtonAction() {
        int selectedBookRow = viewBookTable.getSelectedRow();
        if (selectedBookRow >= 0) {
            TableModel model = viewBookTable.getModel();
            String bookID = (String) model.getValueAt(selectedBookRow, 0);
            String bookName = (String) model.getValueAt(selectedBookRow, 1);
            String bookPrice = (String) model.getValueAt(selectedBookRow, 2);
            String bookEdition = (String) model.getValueAt(selectedBookRow, 3);
            String bookPublishDate = (String) model.getValueAt(selectedBookRow, 4);
            String bookPublisher = (String) model.getValueAt(selectedBookRow, 5);
            String bookStatus = (String) model.getValueAt(selectedBookRow, 6);
            new UpdateBook(bookID, bookName, bookPrice, bookEdition, bookPublishDate, bookPublisher, bookStatus);
        } else {
            JOptionPane.showMessageDialog(viewBookPanel, "Please Select Row you want to Update", "Row Not Selected", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteBookButtonAction() throws SQLException {
        System.out.println("I am at Delete book action button");
        int selectedBookRow = viewBookTable.getSelectedRow();
        if (selectedBookRow >= 0) {
            TableModel model = viewBookTable.getModel();
            String bookID = (String) model.getValueAt(selectedBookRow, 0);
            DbConnect db = new DbConnect();
            String query = "DELETE FROM BOOKS WHERE id='" + bookID + "'";
            int result = db.connection().executeUpdate(query);
            System.out.println(result + " :result");
            if (result == 1) {
                refreshBooks();
                JOptionPane.showMessageDialog(viewBookPanel, "Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(viewBookPanel, "Error Deletion", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else {
            JOptionPane.showMessageDialog(viewBookPanel, "Please Select Row you want to Delete", "Row Not Selected", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletePublisherButtonAction() throws SQLException {
        System.out.println("I am at Delete Publisher action button");
        int selectedBookRow = viewPublisherTable.getSelectedRow();
        if (selectedBookRow >= 0) {
            TableModel model = viewPublisherTable.getModel();
            String publisherID = (String) model.getValueAt(selectedBookRow, 0);
            DbConnect db = new DbConnect();
            String query = "DELETE FROM PUBLISHERS WHERE id='" + publisherID + "'";
            int result = db.connection().executeUpdate(query);
            System.out.println(result + " :result");
            if (result == 1) {
                JOptionPane.showMessageDialog(viewPublisherPanel, "Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(viewPublisherPanel, "Error Deletion", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else {
            JOptionPane.showMessageDialog(viewBookPanel, "Please Select Row you want to Delete", "Row Not Selected", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void submitBookButtonAction() throws SQLException {

        DbConnect db = new DbConnect();
        if (db.connection() == null) {
            JOptionPane.showMessageDialog(addBookPanel, "Connection lost\nPlease Check if Host is Off", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
        // fetching all data from add book form
        String getBookName = bookNameField.getText();
        String getBookPrice = bookPriceField.getText();
        String getBookEdition = bookEditionField.getText();
        String getBookPublishDate = bookPublishDateField.getText();
        String getBookPublisher = (String) bookPublisherField.getSelectedItem();
        String getBookStatus = (String) bookStatusField.getSelectedItem();
        System.out.println(getBookStatus);

        if (!getBookName.isEmpty() && !getBookPrice.isEmpty() && !getBookEdition.isEmpty() && !getBookPublishDate.isEmpty() && !Objects.requireNonNull(getBookPublisher).isEmpty() && !Objects.requireNonNull(getBookStatus).isEmpty()) {

            try {
                int a = Integer.parseInt(getBookPrice);
                System.out.println(a);
                String addBookQuery = "insert into books(bookName,bookPrice,bookEdition, publishDate, bookPublisher,bookStatus)values('" + getBookName + "','" + getBookPrice + "','" + getBookEdition + "','" + getBookPublishDate + "','" + getBookPublisher + "','" + getBookStatus + "')";
                int addBookResult = db.connection().executeUpdate(addBookQuery);
                System.out.println(addBookResult);
                if (addBookResult == 1) {
                    setAddBookFieldEmpty();
                    JOptionPane.showMessageDialog(addBookPanel, "Book Added Successfully", "Added Successfully", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(addBookPanel, "Price must be Number");
            }

        } else {
            JOptionPane.showMessageDialog(addBookPanel, "Field can not be empty", "Empty", JOptionPane.ERROR_MESSAGE);
        }

    }


    public void submitPublisherButtonAction() throws SQLException {

        System.out.println("Okay i am in submit Publisher button form");

        DbConnect db = new DbConnect();

        if (db.connection() == null) {
            JOptionPane.showMessageDialog(addBookPanel, "Connection lost\nPlease Check if Host is Off", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }

        // fetching all data from add Publisher form
        String getPublisherName = publisherNameTextField.getText();
        String getPublisherEmail = publisherEmailTextField.getText();
        String getPublisherContact = publisherContactTextField.getText();
        String getPublisherAddress = publisherAddressTextField.getText();

        if (!getPublisherName.isEmpty() && !getPublisherEmail.isEmpty() && !getPublisherContact.isEmpty() && !getPublisherAddress.isEmpty()) {

            String addBookQuery = "insert into publishers(publisherName,publisherEmail,publisherContact, publisherAddress)values('" + getPublisherName + "','" + getPublisherEmail + "','" + getPublisherContact + "','" + getPublisherAddress + "')";
            int addPublisherResult = db.connection().executeUpdate(addBookQuery);
            System.out.println(addPublisherResult);
            if (addPublisherResult == 1) {
                setAddPublisherFieldEmpty();
                JOptionPane.showMessageDialog(addPublisherPanel, "Publisher Added Successfully", "Added Successfully", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(addPublisherPanel, "Field can not be empty", "Empty", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void setAddPublisherFieldEmpty() {
        publisherNameTextField.setText("");
        publisherEmailTextField.setText("");
        publisherContactTextField.setText("");
        publisherAddressTextField.setText("");
    }

    public void setAddBookFieldEmpty() {
        bookNameField.setText("");
        bookPriceField.setText("");
        bookEditionField.setText("");
        bookPublishDateField.setText("");
    }

    public void addPublisherAction() {
        adminDashboardTitle.setText("Add Publishers");
        addPublisherPanel.setVisible(true);
        viewPublisherPanel.setVisible(false);
    }

    public void viewPublisherAction() {
        adminDashboardTitle.setText("View Publishers");
        addPublisherPanel.setVisible(false);
        viewPublisherPanel.setVisible(true);
//        viewPublisherTableAction();

    }

    public void generalAction() {
        refreshDashboardData();
        // ================ General Panel ===========================
        adminDashboardTitle.setText("ADMIN DASHBOARD");
        generalPanel.setVisible(true);
        bookPanel.setVisible(false);
        publisherPanel.setVisible(false);

        publisher.setBounds(80, 185, 100, 30);
        publisherIconLabel.setBounds(40, 180, 40, 40);

        // add book
        addBookIconLabel.setVisible(false);
        addBook.setVisible(false);
        // view book
        viewBookIconLabel.setVisible(false);
        viewBook.setVisible(false);

        // add publisher
        addPublisherIconLabel.setVisible(false);
        addPublisher.setVisible(false);
        // view publisher
        viewPublisherIconLabel.setVisible(false);
        viewPublisher.setVisible(false);

    }

    public void bookAction() {
        // ================ book Panel ===========================
        System.out.println("Okay");
        bookPanel.setVisible(true);
        generalPanel.setVisible(false);
        publisherPanel.setVisible(false);
        addBookPanel.setVisible(false);

        bookIconLabel.setBounds(40, 110, 40, 40);
        book.setBounds(80, 115, 100, 30);

        publisher.setBounds(80, 265, 100, 30);
        publisherIconLabel.setBounds(40, 260, 40, 40);

        // add book
        addBookIconLabel.setVisible(true);
        addBook.setVisible(true);
        // view book
        viewBookIconLabel.setVisible(true);
        viewBook.setVisible(true);

        // add publisher
        addPublisherIconLabel.setVisible(false);
        addPublisher.setVisible(false);
        // view publisher
        viewPublisherIconLabel.setVisible(false);
        viewPublisher.setVisible(false);

        // add and view book
        viewBookPanel.setVisible(true);
        refreshBooks();
        addBookPanel.setVisible(false);


    }

    public void addBookAction() {
        refreshPublisherCombo();
        addBookPanel.setVisible(true);
        viewBookPanel.setVisible(false);
        adminDashboardTitle.setText("Add Books");

    }

    public void viewBookAction() {
        viewBookPanel.setVisible(true);
        addBookPanel.setVisible(false);
        adminDashboardTitle.setText("View Books");
//        viewBookTableAction();

    }


    // ================ Publisher action ===========================
    public void publisherAction() {
        System.out.println("Okay");
        publisherPanel.setVisible(true);
        bookPanel.setVisible(false);
        generalPanel.setVisible(false);

        publisher.setBounds(80, 185, 100, 30);
        publisherIconLabel.setBounds(40, 180, 40, 40);

        // add publisher
        addPublisherIconLabel.setVisible(true);
        addPublisher.setVisible(true);
        // view publisher
        viewPublisherIconLabel.setVisible(true);
        viewPublisher.setVisible(true);

        // add book
        addBookIconLabel.setVisible(false);
        addBook.setVisible(false);
        // view book
        viewBookIconLabel.setVisible(false);
        viewBook.setVisible(false);

        addPublisherPanel.setVisible(false);
        viewPublisherPanel.setVisible(true);
        refreshPublishers();
    }

    public void viewPublisherTableAction() {

        //        =============== Update Publisher Button =================
        updatePublisherButton = new JButton("Update");
        updatePublisherButton.setFont(font14);
        updatePublisherButton.setForeground(Color.decode("#016316"));
        viewPublisherPanel.add(updatePublisherButton);
        updatePublisherButton.setBounds(300, 510, 150, 40);
        updatePublisherButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //        =============== Delete Publisher Button =================
        deletePublisherButton = new JButton("Delete");
        deletePublisherButton.setFont(font14);
        deletePublisherButton.setForeground(Color.decode("#016316"));
        viewPublisherPanel.add(deletePublisherButton);
        deletePublisherButton.setBounds(500, 510, 150, 40);
        deletePublisherButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        //      ================= View Book Panel ================================
        String[] PublishersColumn = {"Publisher ID", "Name", "Email", "Contact", "Address"};
        DefaultTableModel model = new DefaultTableModel(PublishersColumn, 0);
        viewPublisherTable = new JTable(model);

        JScrollPane publisherSp = new JScrollPane(viewPublisherTable);
        viewPublisherPanel.add(publisherSp);
        publisherSp.setBounds(3, 3, 1006, 485);

        // ================ view Publisher Panel =========================

        publisherPanel.add(viewPublisherPanel);
//        publisherPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        viewPublisherPanel.setBounds(0, 0, 1020, 600);
        viewPublisherPanel.setOpaque(false);
        viewPublisherPanel.setLayout(null);
        viewPublisherPanel.setVisible(false);

        viewPublisherPanelLabel = new JLabel();
        viewPublisherPanelIcon = new ImageIcon("src/images/viewFrame.png");
        viewPublisherPanelLabel.setIcon(viewPublisherPanelIcon);
        viewPublisherPanel.add(viewPublisherPanelLabel);
        viewPublisherPanelLabel.setBounds(0, 0, 1020, 600);

    }


    public void fetchAllPublishers() {
        ArrayList<Publishers> publishersArrayList = fetchPublisherData();
        DefaultTableModel model = (DefaultTableModel) viewPublisherTable.getModel();

        Object[] publisherData1 = new Object[5];
        for (int i = 0; i < publishersArrayList.size(); i++) {
            publisherData1[0] = publishersArrayList.get(i).publisherID;
            publisherData1[1] = publishersArrayList.get(i).publisherName;
            publisherData1[2] = publishersArrayList.get(i).publisherEmail;
            publisherData1[3] = publishersArrayList.get(i).publisherContact;
            publisherData1[4] = publishersArrayList.get(i).publisherAddress;
            model.addRow(publisherData1);
        }
    }

    public void fetchAllBooks() {
        ArrayList<Books> booksArrayList = fetchBookData();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();

        Object[] data = new Object[7];
        for (int i = 0; i < booksArrayList.size(); i++) {
            data[0] = booksArrayList.get(i).bookID;
            data[1] = booksArrayList.get(i).bookName;
            data[2] = booksArrayList.get(i).bookPrice;
            data[3] = booksArrayList.get(i).bookEdition;
            data[4] = booksArrayList.get(i).bookPublishDate;
            data[5] = booksArrayList.get(i).bookPublisher;
            data[6] = booksArrayList.get(i).bookStatus;
            model.addRow(data);
        }
    }


    public ArrayList<Books> fetchBookData() {

        ArrayList<Books> booksArrayList = new ArrayList<>();
        DbConnect db = new DbConnect();
        String fetchBooksQuery = "SELECT * FROM BOOKS";

        try {
            ResultSet result = db.connection().executeQuery(fetchBooksQuery);
            while (result.next()) {
                String bookID = result.getString("id");
                String bookName = result.getString("bookName");
                String bookPrice = result.getString("bookPrice");
                String bookEdition = result.getString("bookEdition");
                String bookPublishDate = result.getString("publishDate");
                String bookPublisher = result.getString("bookPublisher");
                String bookStatus = result.getString("bookStatus");

                Books bookData = new Books(bookID, bookName, bookPrice, bookEdition, bookPublishDate, bookPublisher, bookStatus);
                booksArrayList.add(bookData);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return booksArrayList;
    }

public ArrayList<String> fetchAllAdmins() {

        ArrayList<String> adminsArrayList = new ArrayList<>();
        DbConnect db = new DbConnect();
        String fetchAdminsQuery = "SELECT * FROM admins";

        try {
            ResultSet result = db.connection().executeQuery(fetchAdminsQuery);
            while (result.next()) {
                adminsArrayList.add(result.getString("username"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return adminsArrayList;
    }


    public ArrayList<Publishers> fetchPublisherData() {

        ArrayList<Publishers> publishersArrayList = new ArrayList<>();

        String fetchPublishersQuery = "SELECT * FROM `publishers`";

        try {
            DbConnect db2 = new DbConnect();
            ResultSet publisherResult = db2.connection().executeQuery(fetchPublishersQuery);
            System.out.println("ResultPub: " + publisherResult);
            while (publisherResult.next()) {
                String publisherID = publisherResult.getString("id");
                String publisherName = publisherResult.getString("publisherName");
                String publisherEmail = publisherResult.getString("publisherEmail");
                String publisherContact = publisherResult.getString("publisherContact");
                String publisherAddress = publisherResult.getString("publisherAddress");

                System.out.println(publisherName + publisherEmail + publisherContact + publisherAddress);
                Publishers publisherData = new Publishers(publisherID, publisherName, publisherEmail, publisherContact, publisherAddress);
                publishersArrayList.add(publisherData);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publishersArrayList;
    }


    public void refreshBooks() {

        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        model.setRowCount(0);
        fetchAllBooks();
    }


    public void refreshPublishers() {
        DefaultTableModel model = (DefaultTableModel) viewPublisherTable.getModel();
        model.setRowCount(0);
        fetchAllPublishers();
    }

    public void searchBookIdAction() {
        try {
            refreshBooks();
            int searchFieldValue = Integer.parseInt(searchBookField.getText());
            DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
            ArrayList<Integer> idArrayListData = new ArrayList<Integer>();
            for (int count = 0; count < model.getRowCount(); count++) {

                idArrayListData.add(Integer.parseInt(model.getValueAt(count, 0).toString()));
            }
            int result = algorithmObj.binarySearch(idArrayListData, searchFieldValue);
            if (result >= 0) {
                Vector foundRow = model.getDataVector().elementAt(result);
                model.setRowCount(0);
                model.addRow(foundRow);
                System.out.println(foundRow);
            } else {
                JOptionPane.showMessageDialog(bookPanel, "No data found");
            }
        } catch (Exception exception) {
            System.out.println("Not found");
        }
    }

    public void searchBookFieldByIDKeyReleased() {
//        searchBookIdAction();
        try {
            refreshBooks();
            int searchFieldValue = Integer.parseInt(searchBookField.getText());
            DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
            ArrayList<Integer> idArrayListData = new ArrayList<Integer>();
            for (int count = 0; count < model.getRowCount(); count++) {

                idArrayListData.add(Integer.parseInt(model.getValueAt(count, 0).toString()));
            }
            int result = algorithmObj.binarySearch(idArrayListData, searchFieldValue);
            if (result >= 0) {
                Vector foundRow = model.getDataVector().elementAt(result);
                model.setRowCount(0);
                model.addRow(foundRow);
                System.out.println(foundRow);
            }

        } catch (Exception exception) {
            System.out.println("Not found");
        }
    }


    public void searchBookFieldByBookNameAction() {

        refreshBooks();
        String searchFieldValue = searchBookField.getText();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 1).toString() + "~" + model.getValueAt(count, 0));
        }
        System.out.println("Search Text: " + searchFieldValue);

        System.out.println("Table: " + nameArrayListData);
        ArrayList<String> sortedArrayList = mergeSortObj.divide(nameArrayListData, 0, nameArrayListData.size() - 1);
        System.out.println("Sorted: " + sortedArrayList);
        int result = algorithmObj.binarySearchString(sortedArrayList, searchFieldValue);
        System.out.println("Search result: " + result);
        System.out.println("\n");
        if (result >= 0) {
            Vector foundRow = model.getDataVector().elementAt(result);
            model.setRowCount(0);
            model.addRow(foundRow);
            System.out.println(foundRow);

        } else {
            JOptionPane.showMessageDialog(bookPanel, "Book " + searchFieldValue + " not Found");
        }
    }

    public void searchBookFieldByBookPublisherAction() {

        refreshBooks();
        String searchFieldValue = searchBookField.getText();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 5).toString() + "~" + model.getValueAt(count, 0));
        }

        ArrayList<String> sortedArrayList = mergeSortObj.divide(nameArrayListData, 0, nameArrayListData.size() - 1);

        int result = algorithmObj.binarySearchString(sortedArrayList, searchFieldValue);

        System.out.println("\n");
        if (result >= 0) {
            Vector foundRow = model.getDataVector().elementAt(result);
            model.setRowCount(0);
            model.addRow(foundRow);
            System.out.println(foundRow);

        } else {
            JOptionPane.showMessageDialog(bookPanel, "Publisher " + searchFieldValue + " not Found");
        }
    }

    public void searchBookFieldByBookPublishDateAction() {

        refreshBooks();
        String searchFieldValue = searchBookField.getText();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 4).toString() + "~" + model.getValueAt(count, 0));
        }

        ArrayList<String> sortedArrayList = mergeSortObj.divide(nameArrayListData, 0, nameArrayListData.size() - 1);

        int result = algorithmObj.binarySearchString(sortedArrayList, searchFieldValue);

        System.out.println("\n");
        if (result >= 0) {
            Vector foundRow = model.getDataVector().elementAt(result);
            model.setRowCount(0);
            model.addRow(foundRow);
            System.out.println(foundRow);

        } else {
            JOptionPane.showMessageDialog(bookPanel, "Nothing found on " + searchFieldValue + "Date");
        }
    }

    public void searchByActionCondition() {

        searchBookButton.addActionListener(e -> {
            if (Objects.equals(searchBookByField.getSelectedIndex(), 0)) {
                searchBookFieldByIDKeyReleased();
            } else if (Objects.equals(searchBookByField.getSelectedIndex(), 1)) {
                searchBookFieldByBookNameAction();
            } else if (Objects.equals(searchBookByField.getSelectedIndex(), 2)) {
                searchBookFieldByBookPublisherAction();
            } else if (Objects.equals(searchBookByField.getSelectedIndex(), 3)) {
                searchBookFieldByBookPublishDateAction();
            }
        });
    }

//    Sort Actions

    public void sortByBookNameAscendingAction() {
        refreshBooks();
        String searchFieldValue = searchBookField.getText();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 1).toString() + "~" + model.getValueAt(count, 0));
        }
        System.out.println("Search Text: " + searchFieldValue);

        System.out.println("Table: " + nameArrayListData);
        ArrayList<String> sortedArrayList = mergeSortObj.divide(nameArrayListData, 0, nameArrayListData.size() - 1);
        System.out.println("Sorted: " + sortedArrayList);

        for (int i = 0; i < sortedArrayList.size(); i++) {
            String[] temp_id = nameArrayListData.get(i).split("~");
            System.out.println("Temp id: "+ Arrays.toString(temp_id));
            Vector foundRow = model.getDataVector().elementAt(Integer.parseInt(temp_id[1])-1+i);
            model.insertRow(i, foundRow);
        }
        model.setRowCount(sortedArrayList.size());
    }
    public void sortByBookNameDescendingAction() {
        refreshBooks();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 1).toString() + "~" + model.getValueAt(count, 0));
        }

        System.out.println("Table: " + nameArrayListData);
        ArrayList<String> sortedArrayList = mergeSortObj.divideDescending(nameArrayListData, 0, nameArrayListData.size() - 1);
        System.out.println("Sorted: " + sortedArrayList);

        for (int i = 0; i < sortedArrayList.size(); i++) {
            String[] temp_id = nameArrayListData.get(i).split("~");
            System.out.println("Temp id: "+ Arrays.toString(temp_id));
            Vector foundRow = model.getDataVector().elementAt(Integer.parseInt(temp_id[1])-1+i);
            model.insertRow(i, foundRow);
        }
        model.setRowCount(sortedArrayList.size());
    }

    public void sortByBookIdAscendingAction() {
        refreshBooks();
        String searchFieldValue = searchBookField.getText();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 0).toString() + "~" + model.getValueAt(count, 0));
        }
        System.out.println("Search Text: " + searchFieldValue);

        System.out.println("Table: " + nameArrayListData);
        ArrayList<String> sortedArrayList = mergeSortObj.divide(nameArrayListData, 0, nameArrayListData.size() - 1);
        System.out.println("Sorted: " + sortedArrayList);

        for (int i = 0; i < sortedArrayList.size(); i++) {
            String[] temp_id = nameArrayListData.get(i).split("~");
            System.out.println("Temp id: "+ Arrays.toString(temp_id));
            Vector foundRow = model.getDataVector().elementAt(Integer.parseInt(temp_id[1])-1+i);
            model.insertRow(i, foundRow);
        }
        model.setRowCount(sortedArrayList.size());
    }

    public void sortByBookIdDescendingAction() {
        refreshBooks();
        String searchFieldValue = searchBookField.getText();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 0).toString() + "~" + model.getValueAt(count, 0));
        }
        System.out.println("Search Text: " + searchFieldValue);

        System.out.println("Table: " + nameArrayListData);
        ArrayList<String> sortedArrayList = mergeSortObj.divideDescending(nameArrayListData, 0, nameArrayListData.size() - 1);
        System.out.println("Sorted: " + sortedArrayList);

        for (int i = 0; i < sortedArrayList.size(); i++) {
            String[] temp_id = nameArrayListData.get(i).split("~");
            System.out.println("Temp id: "+ Arrays.toString(temp_id));
            Vector foundRow = model.getDataVector().elementAt(Integer.parseInt(temp_id[1])-1+i);
            model.insertRow(i, foundRow);
        }
        model.setRowCount(sortedArrayList.size());
    }

    public void sortByBookPublisherAscendingAction() {
        refreshBooks();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 5).toString() + "~" + model.getValueAt(count, 0));
        }

        ArrayList<String> sortedArrayList = mergeSortObj.divide(nameArrayListData, 0, nameArrayListData.size() - 1);

        for (int i = 0; i < sortedArrayList.size(); i++) {
            String[] temp_id = nameArrayListData.get(i).split("~");
            Vector foundRow = model.getDataVector().elementAt(Integer.parseInt(temp_id[1])-1+i);
            model.insertRow(i, foundRow);
        }
        model.setRowCount(sortedArrayList.size());
    }

    public void sortByBookPublisherDescendingAction() {
        refreshBooks();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 5).toString() + "~" + model.getValueAt(count, 0));
        }

        ArrayList<String> sortedArrayList = mergeSortObj.divideDescending(nameArrayListData, 0, nameArrayListData.size() - 1);

        for (int i = 0; i < sortedArrayList.size(); i++) {
            String[] temp_id = nameArrayListData.get(i).split("~");
            Vector foundRow = model.getDataVector().elementAt(Integer.parseInt(temp_id[1])-1+i);
            model.insertRow(i, foundRow);
        }
        model.setRowCount(sortedArrayList.size());
    }

    public void sortByBookPublishDateAscendingAction() {
        refreshBooks();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 4).toString() + "~" + model.getValueAt(count, 0));
        }

        ArrayList<String> sortedArrayList = mergeSortObj.divide(nameArrayListData, 0, nameArrayListData.size() - 1);

        for (int i = 0; i < sortedArrayList.size(); i++) {
            String[] temp_id = nameArrayListData.get(i).split("~");
            Vector foundRow = model.getDataVector().elementAt(Integer.parseInt(temp_id[1])-1+i);
            model.insertRow(i, foundRow);
        }
        model.setRowCount(sortedArrayList.size());
    }
    public void sortByBookPublishDateDescendingAction() {
        refreshBooks();
        DefaultTableModel model = (DefaultTableModel) viewBookTable.getModel();
        ArrayList<String> nameArrayListData = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {

            nameArrayListData.add(model.getValueAt(count, 4).toString() + "~" + model.getValueAt(count, 0));
        }

        ArrayList<String> sortedArrayList = mergeSortObj.divideDescending(nameArrayListData, 0, nameArrayListData.size() - 1);

        for (int i = 0; i < sortedArrayList.size(); i++) {
            String[] temp_id = nameArrayListData.get(i).split("~");
            Vector foundRow = model.getDataVector().elementAt(Integer.parseInt(temp_id[1])-1+i);
            model.insertRow(i, foundRow);
        }
        model.setRowCount(sortedArrayList.size());
    }

    public void sortByActionValidation() {
        sortBookButton.addActionListener(e -> {

            if (Objects.equals(sortBookByField.getSelectedIndex(), 0) && Objects.equals(sortByOrderField.getSelectedIndex(),0)) {
                sortByBookIdAscendingAction();
            }
            else if (Objects.equals(sortBookByField.getSelectedIndex(), 1) && Objects.equals(sortByOrderField.getSelectedIndex(),0)) {
                System.out.println("I am in");
                sortByBookNameAscendingAction();
            }
            else if (Objects.equals(sortBookByField.getSelectedIndex(), 2) && Objects.equals(sortByOrderField.getSelectedIndex(),0)) {
                sortByBookPublisherAscendingAction();
            }
            else if (Objects.equals(sortBookByField.getSelectedIndex(), 3) && Objects.equals(sortByOrderField.getSelectedIndex(),0)) {
                sortByBookPublishDateAscendingAction();
            }
            else if (Objects.equals(sortBookByField.getSelectedIndex(), 0) && Objects.equals(sortByOrderField.getSelectedIndex(),1)) {
                sortByBookIdDescendingAction();
            }
            else if (Objects.equals(sortBookByField.getSelectedIndex(), 1) && Objects.equals(sortByOrderField.getSelectedIndex(),1)) {
                System.out.println("I am in");
                sortByBookNameDescendingAction();
            }
            else if (Objects.equals(sortBookByField.getSelectedIndex(), 2) && Objects.equals(sortByOrderField.getSelectedIndex(),1)) {
                sortByBookPublisherDescendingAction();
            }
            else if (Objects.equals(sortBookByField.getSelectedIndex(), 3) && Objects.equals(sortByOrderField.getSelectedIndex(),1)) {
                sortByBookPublishDateDescendingAction();
            }
        });
    }

    public void refreshPublisherCombo(){
        bookPublisherField.removeAllItems();
        ArrayList<Publishers> publishersArrayList = fetchPublisherData();
        String[] publisherNameList = new String[publishersArrayList.size()];
        for (int i = 0; i < publishersArrayList.size(); i++) {
            publisherNameList[i] = String.valueOf(publishersArrayList.get(i).publisherName);
        }

        for (int i=0; i<publishersArrayList.size();i++){
            bookPublisherField.addItem(publisherNameList[i]);
        }
        System.out.println(Arrays.toString(publisherNameList));

    }

    public void refreshDashboardData(){
        ArrayList<String> totalAdminsDataList = fetchAllAdmins();
        ArrayList<Books> totalBooksDataList = fetchBookData();
        ArrayList<Publishers> totalPublisherList = fetchPublisherData();
        totalAdminsData.setText(String.valueOf(totalAdminsDataList.size()));
        totalBooksData.setText(String.valueOf(totalBooksDataList.size()));
        totalPublishersData.setText(String.valueOf(totalPublisherList.size()));
    }

}