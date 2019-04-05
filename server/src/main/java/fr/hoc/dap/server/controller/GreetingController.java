package fr.hoc.dap.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** . */
@RestController
public class GreetingController {
    /**
     * @param theName .
     * @return .
     */
    @RequestMapping("/")
    public String index1(@RequestParam("name") final String theName) {
        return "Greetings from Spring Boot " + theName;
    }
}
