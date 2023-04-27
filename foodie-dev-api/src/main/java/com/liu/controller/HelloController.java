package com.liu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//@Controller
//@ApiIgnore
@RestController
public class HelloController {

    //final private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public Object hello() {
        return "Hello World~";
    }


}
