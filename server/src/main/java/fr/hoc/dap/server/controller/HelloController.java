/**
 * 
 */
package fr.hoc.dap.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author house
 *
 */
@Controller
public class HelloController {

    /**
     * @param args
     */
    @RequestMapping("/hello2")
    public String hello(ModelMap model) {
        model.addAttribute("maVar", "toto");

        List<String> bestioles = new ArrayList<>();
        bestioles.add("Chien");
        bestioles.add("Zèbre");
        bestioles.add("Suricate");

        model.addAttribute("bebetes", bestioles);

        return "hello";
    }

}
