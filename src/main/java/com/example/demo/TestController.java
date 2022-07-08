package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Ok";
    }


    // 500Error
    @GetMapping("/error-test")
    public String errorTest() {
        throw new RuntimeException();
    }

    @GetMapping("/error-my")
    public String myError() {
        throw new MyException();
    }

    @GetMapping(value = "/error-bad")
    public String badRequest() {
        throw new BadRequestException();
    }

    @GetMapping("/converter-view")
    public IpPort converterView(@RequestParam IpPort data) {
        return data;
    }

    @GetMapping("/ipconverter")
    public IpPort ipPortToString(@RequestParam IpPort data) {
        return data;
    }
}
