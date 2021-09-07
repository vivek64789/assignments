package backend;

import frontend.StartApp;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DbConnect {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
     String hostName;
     String port;
     String username;
     String password;
    public static Connection con;
    Statement stmt = null;

    public Statement setUpConnection() throws SQLException {

        try{
            readCredentials();
            final String DB_URL = "jdbc:mysql://"+hostName+":"+port+"";

            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(DB_URL,username,password);
            stmt = con.createStatement();

            String query = "CREATE DATABASE coursework2";
            stmt.executeUpdate(query);

            con= DriverManager.getConnection(DB_URL+"/coursework2",username,password);
            stmt = con.createStatement();

            System.out.println(DB_URL);



        }
        catch (SQLException | ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
        System.out.println("statement: "+stmt);
        return stmt;

    }


    public Statement connection() {


        try{
            readCredentials();
            final String DB_URL = "jdbc:mysql://"+hostName+":"+port+"";

            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(DB_URL,username,password);
            stmt = con.createStatement();

            con= DriverManager.getConnection(DB_URL+"/coursework2",username,password);
            stmt = con.createStatement();


        }
        catch (SQLException | ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
        return stmt;

    }

    private void readCredentials() throws IOException {
        String fileName = StartApp.myFile.getPath();
        Path path = Path.of(fileName);
        Scanner reader = new Scanner(path);
        this.hostName = reader.nextLine();
        this.port = reader.nextLine();
        this.username = reader.nextLine();

        if (reader.hasNextLine()){
            this.password = reader.nextLine();
        }else{
            this.password = "";
        }

    }

}

