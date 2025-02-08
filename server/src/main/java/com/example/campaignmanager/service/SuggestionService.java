package com.example.campaignmanager.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionService {
    private final static List<String> KEYWORDS = List.of(
            "technology", "science", "education", "healthcare", "finance",
            "marketing", "programming", "artificial intelligence", "machine learning", "blockchain"
    );

    private final static List<String> TOWNS = List.of(
            "Warszawa", "Krakow", "Lodz", "Wroclaw", "Poznan",
            "Gdansk", "Szczecin", "Bydgoszcz", "Lublin", "Katowice"
    );


    public List<String> getKeywordSuggestions() {
        return new ArrayList<>(KEYWORDS);
    }

    public List<String> getTowns() {
        return new ArrayList<>(TOWNS);
    }
}
