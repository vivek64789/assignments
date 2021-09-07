package frontend;
import backend.DbConnect;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class StartApp {
    public static File myFile = new File("src//backend//credentials.txt");

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf() {
            });
        } catch (Exception ex) {
            System.err.println("Failed to initialize UI");
        }
        UIManager.put("Button.arc", 100);
        UIManager.put("TextComponent.arc", 5);

        try{
            myFile.createNewFile();
            System.out.println("File Created Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

        DbConnect db = new DbConnect();
        String fileName = StartApp.myFile.getPath();
        Path path = Path.of(fileName);
        Scanner reader = new Scanner(path);

        if( !reader.hasNextLine()){
            ConnectDatabase myObj = new ConnectDatabase();
        }
        else if (reader.hasNextLine()) {
            System.out.println("File already exists");
            Login myObj = new Login();
        } else {
            System.out.println("Nothing to work");
        }

    }
}
