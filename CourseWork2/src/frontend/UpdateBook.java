package frontend;

import backend.DbConnect;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;


public class UpdateBook {
    String bookID;
    DbConnect db = new DbConnect();
    Font font12 = new Font("Poppins", Font.PLAIN, 12);
    Font font14 = new Font("Poppins", Font.BOLD, 13);
    JFrame updateBookFrame = new JFrame("Update Books");
    JPanel updateBookPanel = new JPanel();
    JLabel bookName, bookPrice, bookEdition, bookPublishDate, bookPublisher, bookStatus, updateBookPanelLabel
            = new JLabel();
    JTextField bookNameField, bookPriceField, bookEditionField, bookPublishDateField
            = new JTextField();
    JButton saveBookButton = new JButton();
    JComboBox<String> bookPublisherField, bookStatusField = new JComboBox<String>();
    ImageIcon updateBookPanelIcon = new ImageIcon();

    UpdateBook(String bookID, String bookName, String bookPrice, String bookEdition, String bookPublishDate, String bookPublisher, String bookStatus) {
        settingFrame();
        this.bookID = bookID;
        updateBookFrame.getContentPane().setBackground(Color.white);
        updateBookFrame.setVisible(true);
        updateBookFrame.setSize(1050, 650);
//        updateBookFrame.setResizable(false);
        updateBookFrame.setLocation(300, 60);

        this.bookNameField.setText(bookName);
        this.bookPriceField.setText(bookPrice);
        this.bookEditionField.setText(bookEdition);
        this.bookPublishDateField.setText(bookPublishDate);
        this.bookPublisherField.setSelectedItem(bookPublisher);
        this.bookStatusField.setSelectedItem(bookStatus);

    }

    public void settingFrame() {

        // ================= add book panel label and entry fields ==============

        //        =============== Update Book Button =================
        saveBookButton = new JButton("Save");
        saveBookButton.setFont(font14);
        saveBookButton.setForeground(Color.decode("#404040"));
        updateBookPanel.add(saveBookButton);
        saveBookButton.setBounds(430, 510, 150, 40);
        saveBookButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bookName = new JLabel("Book Name:");
        bookName.setFont(font14);
        bookName.setForeground(Color.decode("#404040"));
        updateBookPanel.add(bookName);
        bookName.setBounds(280, 70, 100, 40);

        bookNameField = new JTextField();
        bookNameField.setFont(font12);
        updateBookPanel.add(bookNameField);
        bookNameField.setBounds(280, 103, 450, 25);
        bookNameField.setBorder(BorderFactory.createLineBorder(Color.white));

        bookPrice = new JLabel("Book Price:");
        bookPrice.setFont(font14);
        bookPrice.setForeground(Color.decode("#404040"));
        updateBookPanel.add(bookPrice);
        bookPrice.setBounds(280, 147, 100, 40);

        bookPriceField = new JTextField();
        bookPriceField.setFont(font12);
        updateBookPanel.add(bookPriceField);
        bookPriceField.setBounds(280, 178, 450, 25);
        bookPriceField.setBorder(BorderFactory.createLineBorder(Color.white));

        bookEdition = new JLabel("Book Edition:");
        bookEdition.setFont(font14);
        bookEdition.setForeground(Color.decode("#404040"));
        updateBookPanel.add(bookEdition);
        bookEdition.setBounds(280, 222, 100, 40);

        bookEditionField = new JTextField();
        bookEditionField.setFont(font12);
        updateBookPanel.add(bookEditionField);
        bookEditionField.setBounds(280, 255, 450, 25);
        bookEditionField.setBorder(BorderFactory.createLineBorder(Color.white));

        bookPublishDate = new JLabel("Publish Date:");
        bookPublishDate.setFont(font14);
        bookPublishDate.setForeground(Color.decode("#404040"));
        updateBookPanel.add(bookPublishDate);
        bookPublishDate.setBounds(280, 297, 150, 40);

        bookPublishDateField = new JTextField();
        bookPublishDateField.setFont(font12);
        updateBookPanel.add(bookPublishDateField);
        bookPublishDateField.setBounds(280, 330, 450, 25);
        bookPublishDateField.setBorder(BorderFactory.createLineBorder(Color.white));

        bookPublisher = new JLabel("Book Publisher:");
        bookPublisher.setFont(font14);
        bookPublisher.setForeground(Color.decode("#404040"));
        updateBookPanel.add(bookPublisher);
        bookPublisher.setBounds(280, 370, 150, 40);

        String[] bookPublisherFieldValue = {"First", "Second"};
        bookPublisherField = new JComboBox<>(bookPublisherFieldValue);
        bookPublisherField.setFont(font12);
        updateBookPanel.add(bookPublisherField);
        bookPublisherField.setBounds(280, 403, 450, 30);

        bookStatus = new JLabel("Book Status:");
        bookStatus.setFont(font14);
        bookStatus.setForeground(Color.decode("#404040"));
        updateBookPanel.add(bookStatus);
        bookStatus.setBounds(280, 435, 150, 40);

        String[] bookStatusFieldValue = {"Available", "Out Of Stock"};
        bookStatusField = new JComboBox<>(bookStatusFieldValue);
        bookStatusField.setFont(font12);
        updateBookPanel.add(bookStatusField);
        bookStatusField.setBounds(280, 468, 450, 30);

        //      ================= update Book Panel ================================
        updateBookFrame.add(updateBookPanel);
//        bookPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        updateBookPanel.setBounds(0, 0, 1020, 600);
        updateBookPanel.setOpaque(false);
        updateBookPanel.setLayout(null);

        updateBookPanelLabel = new JLabel();
        updateBookPanelIcon = new ImageIcon("src/images/add_book_frame.png");
        updateBookPanelLabel.setIcon(updateBookPanelIcon);
        updateBookPanel.add(updateBookPanelLabel);
        updateBookPanelLabel.setBounds(0, 0, 1020, 600);

        saveBookButton.addActionListener(e -> {
            try {
                updateBookAction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            updateBookFrame.dispose();
        });

    }


    public void updateBookAction() throws SQLException {
        DbConnect db = new DbConnect();
        if (db.connection() == null) {
            JOptionPane.showMessageDialog(updateBookPanel, "Connection lost\nPlease Check if Host is Off", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }

        // fetching all data from add book form
        String getBookName = bookNameField.getText();
        String getBookPrice = bookPriceField.getText();
        String getBookEdition = bookEditionField.getText();
        String getBookPublishDate = bookPublishDateField.getText();
        String getBookPublisher = (String) bookPublisherField.getSelectedItem();
        String getBookStatus = (String) bookStatusField.getSelectedItem();
        String bookID = this.bookID;

        if (!getBookName.isEmpty() && !getBookPrice.isEmpty() && !getBookEdition.isEmpty() && !getBookPublishDate.isEmpty() && !Objects.requireNonNull(getBookPublisher).isEmpty() && !Objects.requireNonNull(getBookStatus).isEmpty()) {

            try {
                int a = Integer.parseInt(getBookPrice);
                System.out.println(a);
                String addBookQuery = "UPDATE books SET bookName = '"+getBookName+"', bookPrice ='"+getBookPrice+"',bookEdition = '"+getBookEdition+"', publishDate = '"+getBookPublishDate+"', bookPublisher = '"+getBookPublisher+"',bookStatus = '"+getBookStatus+"' WHERE books.id = "+bookID+"";

                System.out.println(addBookQuery);
                int addBookResult = db.connection().executeUpdate(addBookQuery);
                System.out.println(addBookResult);
                if (addBookResult == 1) {
                    JOptionPane.showMessageDialog(updateBookPanel, "Book Added Successfully", "Added Successfully", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(updateBookPanel, "Price must be Number");
            }
        } else {
            JOptionPane.showMessageDialog(updateBookPanel, "Field can not be empty", "Empty", JOptionPane.ERROR_MESSAGE);
        }
    }

}
