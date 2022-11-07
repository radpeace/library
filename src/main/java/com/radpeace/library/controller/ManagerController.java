package com.radpeace.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

    @GetMapping("/manager-books")
    public String managerBooks(Model model) {
        model.addAttribute("title", "Книги");
        return "manager-books";
    }
    @GetMapping("/issued-books")
    public String issuedBooks(Model model) {
        model.addAttribute("title", "Выданные книги");
        return "issued-books";
    }
    @GetMapping("/debtors")
    public String debtors(Model model) {
        model.addAttribute("title", "Должники");
        return "debtors";
    }
}