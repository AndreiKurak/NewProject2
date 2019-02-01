package spring_library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(method = RequestMethod.POST)
    public String home() {
        return "start_page";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home2() {
        return "start_page";
    }
}
