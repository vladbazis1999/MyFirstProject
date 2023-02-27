package com.vlad.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MainPage")
public class MyController
{
    @GetMapping
    public String showMainPage()
    {
        return "MainPage/mainPage";
    }
}
