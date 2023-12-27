package com.example.instagram.DTO;

import com.example.instagram.Model.User;

public class UserBuilder {
    String name, username, password, mobile, email;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User build() {
        User user = new User();
        user.setEmail(email);
        user.setMobile(mobile);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

}
