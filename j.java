package com.example.dictionaryapp.controller;

import com.example.dictionaryapp.model.DictionaryEntry;
import com.example.dictionaryapp.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/search")
    public String searchWord(@RequestParam String word, Model model) {
        try {
            DictionaryEntry[] entries = dictionaryService.getWordDefinition(word);
            if (entries != null && entries.length > 0) {
                model.addAttribute("entry", entries[0]);
                model.addAttribute("word", word);
                model.addAttribute("found", true);
            } else {
                model.addAttribute("found", false);
            }
        } catch (Exception e) {
            model.addAttribute("found", false);
        }
        return "index";
    }
}
