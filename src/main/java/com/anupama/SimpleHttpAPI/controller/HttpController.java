package com.anupama.SimpleHttpAPI.controller;

import com.anupama.SimpleHttpAPI.service.HttpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
public class HttpController {

    private final HttpService httpService;

    public HttpController(HttpService httpService) {
        this.httpService = httpService;
    }

    @GetMapping
    public ResponseEntity<?> sayHello(@RequestParam(required = false) String name) {
        return httpService.processHello(name);
    }
}