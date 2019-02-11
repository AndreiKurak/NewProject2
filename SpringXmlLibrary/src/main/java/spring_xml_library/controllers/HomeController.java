package spring_xml_library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring_xml_library.LibService;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    LibService libService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("pages/start_page.jsp");
        model.addObject("books", libService.list());
        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home2() {
        ModelAndView model = new ModelAndView("pages/start_page.jsp");
        model.addObject("books", libService.list());
        return model;
    }
}
