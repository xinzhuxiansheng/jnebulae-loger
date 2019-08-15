package com.xinzhuxiansheng.springboot.microservicea.web.controller;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/microservicea")
public class ApiController {
    private static Logger logger = LoggerFactory.getLogger(ApiController.class);
    private static org.apache.logging.log4j.Logger  udp = LogManager.getLogger("udpAppendeagent");

    @RequestMapping(value = "/api01", method = { RequestMethod.POST,RequestMethod.GET}, produces = {
        "application/json;charset=UTF-8" })
    public String api01(){

        udp.info("microserviceA");
        return "ok";
    }
}
