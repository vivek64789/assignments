package model;

public class RegisterForm {

    String email, username;
    char[] password;

    public void setEmail(String email){
        this.email = email;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(char[] password){
        this.password = password;
    }

    // get methods

    public String getEmail(){
        return this.email;
    }

    public String getUsername(){
        return this.username;
    }

    public char[] getPassword(){
        return this.password;
    }

}
