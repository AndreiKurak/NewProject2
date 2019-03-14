package spring_boot_library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring_boot_library.first_variant.ObsoleteLibService;

@Controller
public class HomeController {

    @Autowired
    ObsoleteLibService libService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeGet(Model model) {
        model.addAttribute("books", libService.list());
        return "WEB-INF/view/home.jsp";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String homePost(Model model) {
        model.addAttribute("books", libService.list());
        return "WEB-INF/view/home.jsp";
    }
}
