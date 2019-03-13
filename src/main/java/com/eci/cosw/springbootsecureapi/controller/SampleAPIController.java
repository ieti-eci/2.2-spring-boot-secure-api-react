package com.eci.cosw.springbootsecureapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleAPIController
{

    @GetMapping( "/api/test" )
    public String getTestMessage()
    {
        return "Test Message!!";
    }
}
