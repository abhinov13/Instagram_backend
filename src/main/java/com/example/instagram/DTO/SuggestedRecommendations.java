package com.example.instagram.DTO;

import java.util.HashMap;
import java.util.Map;

import com.example.instagram.Model.User;

public class SuggestedRecommendations {
    Map<User, Recommendation> recommendations = new HashMap<>();

    public Map<User, Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Map<User, Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

}
