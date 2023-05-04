package com.liu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


//@Controller
//@ApiIgnore
@RestController
@ApiIgnore
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET})
public class HelloController {

    final private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public Object hello() {
        logger.info("hello日志");
        return "Hello World~";
    }


}
