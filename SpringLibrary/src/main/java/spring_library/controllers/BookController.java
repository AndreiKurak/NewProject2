package spring_library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring_library.Book;
import spring_library.LibService;
import spring_library.command_options.AddCommandOptions;
import spring_library.command_options.DeleteCommandOptions;
import spring_library.command_options.SearchCommandOptions;
import spring_library.command_options.UpdateCommandOptions;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/command")
public class BookController {

    @Autowired
    LibService libService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //@ResponseBody
    public ModelAndView add(@Valid @ModelAttribute AddCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("view");

        if (result.hasErrors()) {
            model.setViewName("start_page");
            model.addObject("message", "Error");
            System.out.println("errors?");
        }
        else {
            model.addObject("message", "AddCommand was successfully performed");
            libService.add(new Book(modelRequest.getAuthor(), modelRequest.getTitle(), modelRequest.getYear()));
        }
        System.out.println("Yo!" + modelRequest.getAuthor());
        return model;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@Valid @ModelAttribute DeleteCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("view");
        if (result.hasErrors()) {
            model.setViewName("start_page");
            model.addObject("message", "Error");
            System.out.println("errors?");
        }
        else {
            model.addObject("message", "DeleteCommand was successfully performed");
            libService.delete(Integer.valueOf(modelRequest.getId()));
        }
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid @ModelAttribute UpdateCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("view");
        if (result.hasErrors()) {
            model.setViewName("start_page");
            model.addObject("message", "Error");
            System.out.println("errors?");
        }
        else {
            model.addObject("message", "UpdateCommand was successfully performed");
            libService.update(Integer.valueOf(modelRequest.getId()), new Book(modelRequest.getAuthor(),
                    modelRequest.getTitle(), modelRequest.getYear()));
        }
        return model;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@Valid @ModelAttribute SearchCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("view");
        if (result.hasErrors()) {
            model.setViewName("start_page");
            model.addObject("message", "Error");
            System.out.println("errors?");
        }
        else {
            model.addObject("message", "SearchCommand was successfully performed");
            libService.search(new Book(modelRequest.getAuthor(), modelRequest.getTitle(), modelRequest.getYear()));
        }
        return model;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("view");
        List<Book> bookList = libService.list();
        model.addObject("message", bookList);
        System.out.println(bookList);
        return model;

    }
}
