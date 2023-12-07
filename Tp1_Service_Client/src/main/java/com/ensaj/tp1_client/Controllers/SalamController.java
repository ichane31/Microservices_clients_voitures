package com.ensaj.tp1_client.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalamController {
    @GetMapping("/salam")
    public String getSalamController() {
        return "Salam";
    }
}
