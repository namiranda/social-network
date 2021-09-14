package io.redbee.socialnetwork.users;

import java.io.Serializable;

public class UserRequest implements Serializable {
    String mail;
    String password;

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
