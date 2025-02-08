package com.example.campaignmanager.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionService {
    private final static List<String> KEYWORDS = List.of("keyword1", "keyword2", "keyword3");
    private final static List<String> TOWNS = List.of("town1", "town2", "town3");

    public List<String> getKeywordSuggestions() {
        return new ArrayList<>(KEYWORDS);
    }

    public List<String> getTowns() {
        return new ArrayList<>(TOWNS);
    }
}
