package com.stefancouture.accountsmgr.objects;

public class User {
    private String username;
    private String password;
    private String reentered_password;
    private String firstName;
    private String lastName;
    private String email_address;

    public User(){}

    public User(String userNm, String pass, String re_pass, String fName, String lName, String email){
        username = userNm;
        password = pass;
        reentered_password = re_pass;
        firstName = fName;
        lastName = lName;
        email_address = email;
    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getReentered_password(){ return reentered_password; }
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public String getEmail_address(){ return email_address; }

    public void setUsername(String usrName){ username = usrName; }
    public void setPassword(String pswr){ password = pswr; }
    public void setReentered_password(String reentered_pswr){ reentered_password = reentered_pswr; }
    public void setFirstName(String fName){ firstName = fName ; }
    public void setLastName(String lName){ lastName = lName; }
    public void setEmail_address(String emailAdd){ email_address = emailAdd; }
}
