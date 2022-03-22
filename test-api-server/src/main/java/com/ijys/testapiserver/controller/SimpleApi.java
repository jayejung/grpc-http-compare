package com.ijys.testapiserver.controller;

import com.ijys.testapiserver.vo.SimpleData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleApi {
    @GetMapping("/getData")
    public SimpleData getData(@RequestParam(value = "name", defaultValue = "ticketdev") String name) {

        return new SimpleData(1L, String.format("My name is %s", name));
    }
}
