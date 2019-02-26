package spring_xml_library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring_xml_library.command_options.AddCommandOptions;
import spring_xml_library.command_options.DeleteCommandOptions;
import spring_xml_library.command_options.SearchCommandOptions;
import spring_xml_library.command_options.UpdateCommandOptions;

@Controller
@RequestMapping("/options")
public class OptionsController {

    @RequestMapping(value = "/add")
    public String add(@ModelAttribute("command")AddCommandOptions addCommandOptions) {
        return "pages/add_options.jsp";
    }

    @RequestMapping(value = "/delete")
    public String delete(@ModelAttribute("command") DeleteCommandOptions deleteCommandOptions) {
        return "pages/delete_options.jsp";
    }

    @RequestMapping(value = "/search")
    public String search(@ModelAttribute("command")SearchCommandOptions searchCommandOptions) {
        return "pages/search_options.jsp";
    }

    @RequestMapping(value = "/update")
    public String update(@ModelAttribute("command")UpdateCommandOptions updateCommandOptions) {
        return "pages/update_options.jsp";
    }
}
