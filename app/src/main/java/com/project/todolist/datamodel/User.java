package com.project.todolist.datamodel;

import java.io.Serializable;

/**
 * Created by mah_y on 8/29/2017.
 */

public class User implements Serializable{
    public String uid;
    public String uemail;
    public User() {
    }

    public User(String uid, String uemail) {
        this.uid = uid;
        this.uemail = uemail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }
}
