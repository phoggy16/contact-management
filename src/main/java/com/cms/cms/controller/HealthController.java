package com.cms.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("api/external/health-check")
    public String health(){
        return "Ok";
    }
}
