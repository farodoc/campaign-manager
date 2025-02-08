package com.example.campaignmanager.controller;

import com.example.campaignmanager.service.SuggestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/suggestions")
public class SuggestionController {
    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<String>> getKeywordSuggestions() {
        List<String> suggestions = suggestionService.getKeywordSuggestions();
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/towns")
    public ResponseEntity<List<String>> getTowns() {
        List<String> towns = suggestionService.getTowns();
        return ResponseEntity.ok(towns);
    }
}