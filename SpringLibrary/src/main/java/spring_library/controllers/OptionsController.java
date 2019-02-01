package spring_library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/options")
public class OptionsController {

    @RequestMapping(value = "/add")
    public String add() {
        return "add_options";
    }

    @RequestMapping(value = "/delete")
    public String delete() {
        return "delete_options";
    }

    @RequestMapping(value = "/search")
    public String search() {
        return "search_options";
    }

    @RequestMapping(value = "/update")
    public String update() {
        return "update_options";
    }
}
