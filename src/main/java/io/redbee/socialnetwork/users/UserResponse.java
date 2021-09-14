package io.redbee.socialnetwork.users;

import java.io.Serializable;

public class UserResponse implements Serializable {
    Integer id;
    String mail;
    String status;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getStatus() {
        return status;
    }
}
