package frontend;
import backend.DbConnect;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class UpdatePublisher {
    String publisherID;
    DbConnect db = new DbConnect();
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    Font font14 = new Font("Poppins", Font.BOLD, 13);
    JFrame updatePublishersFrame = new JFrame("Update Publishers");
    JPanel updatePublisherPanel = new JPanel();
    JLabel publisherName, publisherEmail, publisherContact, publisherAddress,updatePublisherPanelLabel= new JLabel();
    JButton submitPublisherButton = new JButton();
    JTextField
            publisherNameTextField, publisherEmailTextField, publisherContactTextField, publisherAddressTextField
            = new JTextField();
    ImageIcon updatePublisherPanelIcon = new ImageIcon();

    UpdatePublisher(String publisherID, String publisherName, String publisherEmail, String publisherContact,String publisherAddress) {
        this.publisherID = publisherID;
        updatePublishersFrame.getContentPane().setBackground(Color.white);
        updatePublishersFrame.setVisible(true);
        updatePublishersFrame.setSize(1050, 650);
        updatePublishersFrame.setResizable(false);
        updatePublishersFrame.setLocation(300,60);
        updatePublishersFrame.setLayout(null);
        settingFrame();
        this.publisherNameTextField.setText(publisherName);
        this.publisherEmailTextField.setText(publisherEmail);
        this.publisherContactTextField.setText(publisherContact);
        this.publisherAddressTextField.setText(publisherAddress);



    }

    public void settingFrame() {


        publisherName = new JLabel("Publisher Name:");
        updatePublisherPanel.add(publisherName);
        publisherName.setFont(font14);
        publisherName.setForeground(Color.decode("#404040"));
        publisherName.setBounds(280, 100, 150, 30);

        publisherNameTextField = new JTextField();
        updatePublisherPanel.add(publisherNameTextField);
        publisherNameTextField.setFont(font12);
        publisherNameTextField.setBounds(280, 125, 450, 40);


        publisherEmail = new JLabel("Publisher Email:");
        updatePublisherPanel.add(publisherEmail);
        publisherEmail.setFont(font14);
        publisherEmail.setForeground(Color.decode("#404040"));
        publisherEmail.setBounds(280, 175, 150, 30);

        publisherEmailTextField = new JTextField();
        updatePublisherPanel.add(publisherEmailTextField);
        publisherEmailTextField.setFont(font12);
        publisherEmailTextField.setBounds(280, 200, 450, 40);


        publisherContact = new JLabel("Publisher Contact Number:");
        updatePublisherPanel.add(publisherContact);
        publisherContact.setFont(font14);
        publisherContact.setForeground(Color.decode("#404040"));
        publisherContact.setBounds(280, 250, 200, 30);

        publisherContactTextField = new JTextField();
        updatePublisherPanel.add(publisherContactTextField);
        publisherContactTextField.setFont(font12);
        publisherContactTextField.setBounds(280, 275, 450, 40);


        publisherAddress = new JLabel("Publisher Address:");
        updatePublisherPanel.add(publisherAddress);
        publisherAddress.setFont(font14);
        publisherAddress.setForeground(Color.decode("#404040"));
        publisherAddress.setBounds(280, 325, 200, 30);

        publisherAddressTextField = new JTextField();
        updatePublisherPanel.add(publisherAddressTextField);
        publisherAddressTextField.setFont(font12);
        publisherAddressTextField.setBounds(280, 350, 450, 40);


        //        =============== Save Publisher Button =================
        submitPublisherButton = new JButton("Save");
        submitPublisherButton.setFont(font14);
        submitPublisherButton.setForeground(Color.decode("#016316"));
        updatePublisherPanel.add(submitPublisherButton);
        submitPublisherButton.setBounds(430, 410, 150, 40);
        submitPublisherButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        updatePublishersFrame.add(updatePublisherPanel);
        updatePublisherPanel.setBounds(0, 0, 1020, 600);
        updatePublisherPanel.setOpaque(false);
        updatePublisherPanel.setLayout(null);
        updatePublisherPanel.setVisible(true);

        updatePublisherPanelLabel = new JLabel();
        updatePublisherPanelIcon = new ImageIcon("src/images/addPublisherFrame.png");
        updatePublisherPanelLabel.setIcon(updatePublisherPanelIcon);
        updatePublisherPanel.add(updatePublisherPanelLabel);
        updatePublisherPanelLabel.setBounds(0, 0, 1020, 600);

        submitPublisherButton.addActionListener(e -> {
            try {
                updatePublisherAction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            updatePublishersFrame.dispose();
        });
    }

    public void updatePublisherAction() throws SQLException {

        DbConnect db = new DbConnect();

        if (db.connection() == null) {
            JOptionPane.showMessageDialog(updatePublisherPanel, "Connection lost\nPlease Check if Host is Off", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }

        // fetching all data from add Publisher form
        String getPublisherName = publisherNameTextField.getText();
        String getPublisherEmail = publisherEmailTextField.getText();
        String getPublisherContact = publisherContactTextField.getText();
        String getPublisherAddress = publisherAddressTextField.getText();
        String publisherID = this.publisherID;
        System.out.println("publusher id "+publisherID+"");

        if (!getPublisherName.isEmpty() && !getPublisherEmail.isEmpty() && !getPublisherContact.isEmpty() && !getPublisherAddress.isEmpty()) {

            String updatePublisherQuery = "UPDATE publishers SET publisherName = '"+getPublisherName+"', publisherEmail ='"+getPublisherEmail+"',publisherContact = '"+getPublisherContact+"', publisherAddress = '"+getPublisherAddress+"' WHERE publishers.id = "+publisherID+"";
            System.out.println(updatePublisherQuery);
            int addPublisherResult = db.connection().executeUpdate(updatePublisherQuery);
            System.out.println(addPublisherResult);
            if (addPublisherResult == 1) {
                JOptionPane.showMessageDialog(updatePublisherPanel, "Publisher Updated Successfully", "Updated Successfully", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(updatePublisherPanel, "Field can not be empty", "Empty", JOptionPane.ERROR_MESSAGE);
        }
    }
}
