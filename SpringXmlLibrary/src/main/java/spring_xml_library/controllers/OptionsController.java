package spring_xml_library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/options")
public class OptionsController {

    @RequestMapping(value = "/add")
    public String add() {
        return "pages/add_options.jsp";
    }

    @RequestMapping(value = "/delete")
    public String delete() {
        return "pages/delete_options.jsp";
    }

    @RequestMapping(value = "/search")
    public String search() {
        return "pages/search_options.jsp";
    }

    @RequestMapping(value = "/update")
    public String update() {
        return "pages/update_options.jsp";
    }
}
