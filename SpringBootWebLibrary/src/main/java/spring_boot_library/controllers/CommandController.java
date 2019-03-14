package spring_boot_library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring_boot_library.LibService;
import spring_boot_library.Book;
import spring_boot_library.command_options.AddCommandOptions;
import spring_boot_library.command_options.DeleteCommandOptions;
import spring_boot_library.command_options.SearchCommandOptions;
import spring_boot_library.command_options.UpdateCommandOptions;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/command")
public class CommandController {

    @Autowired
    LibService libService;

    @RequestMapping(value = "/add")
    public ModelAndView add(@Valid @ModelAttribute("options")AddCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("home");
        if (result.hasErrors()) {
            model.setViewName("WEB-INF/view/add_options.jsp");
        }
        else {
            libService.add(new Book(modelRequest.getAuthor(), modelRequest.getTitle(), modelRequest.getYear()));
            model.addObject("message", "AddCommand was successfully performed");
        }
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@Valid @ModelAttribute("options")DeleteCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("home");
        if (result.hasErrors()) {
            model.setViewName("WEB-INF/view/delete_options.jsp");
        }
        else {
            libService.delete(modelRequest.getId());
            model.addObject("message", "DeleteCommand was successfully performed");
        }
        return model;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(@Valid @ModelAttribute("options")SearchCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("WEB-INF/view/result.jsp");
        if (result.hasErrors()) {
            model.setViewName("WEB-INF/view/search_options.jsp");
        }
        else {
            List<Book> bookList = libService.search(modelRequest.getAuthor(), modelRequest.getTitle(), modelRequest.getYear());
            model.addObject("message", bookList);
        }
        return model;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(@Valid @ModelAttribute("options")UpdateCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("home");
        if (result.hasErrors()) {
            model.setViewName("WEB-INF/view/update_options.jsp");
        }
        else {
            libService.update(new Book(modelRequest.getId(), modelRequest.getAuthor(),
                    modelRequest.getTitle(), modelRequest.getYear()));
            model.addObject("message", "UpdateCommand was successfully performed");
        }
        return model;
    }
}
