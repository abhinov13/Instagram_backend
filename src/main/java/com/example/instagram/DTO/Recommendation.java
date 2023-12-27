package com.example.instagram.DTO;

import java.util.HashSet;
import java.util.Set;

import com.example.instagram.Model.User;

public class Recommendation {
    public User user;
    public Set<User> common = new HashSet<>();

    public Set<User> getCommon() {
        return common;
    }

    public void setCommon(Set<User> common) {
        this.common = common;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recommendation() {
    }

    public Recommendation(User user, Set<User> common) {
        this.user = user;
        this.common = common;
    }

    public Recommendation user(User user) {
        setUser(user);
        return this;
    }

    public Recommendation common(Set<User> common) {
        setCommon(common);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Recommendation)) {
            return false;
        }
        Recommendation recommendation = (Recommendation) o;
        return user.getUsername().equals(recommendation.getUser().getUsername());
    }

    @Override
    public int hashCode() {
        return user.getUsername().hashCode();
    }
}
