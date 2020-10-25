package com.example.elbrus.Models;

public class User {
    private String name, secname, email, pass;

    private int score;

    public User(String name, String secname, String email, String pass, int score) {
        this.name = name;
        this.secname = secname;
        this.email = email;
        this.pass = pass;
        this.score = score;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
