package spring_boot_library.first_variant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring_boot_library.Book;
import spring_boot_library.first_variant.ObsoleteLibService;
import spring_boot_library.command_options.AddCommandOptions;
import spring_boot_library.command_options.DeleteCommandOptions;
import spring_boot_library.command_options.SearchCommandOptions;
import spring_boot_library.command_options.UpdateCommandOptions;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/obsolete_command")
public class ObsoleteCommandController {

    @Autowired
    ObsoleteLibService obsoleteLibService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@Valid @ModelAttribute("options") AddCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("home");
        if (result.hasErrors()) {
            model.setViewName("WEB-INF/view/add_options.jsp");
        }
        else {
            obsoleteLibService.add(new Book(modelRequest.getAuthor(), modelRequest.getTitle(), modelRequest.getYear()));
            model.addObject("message", "AddCommand was successfully performed");
        }
        return model;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView delete(@Valid @ModelAttribute("options") DeleteCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("home");
        if (result.hasErrors()) {
            model.setViewName("WEB-INF/view/delete_options.jsp");
        }
        else {
            obsoleteLibService.delete(Integer.valueOf(modelRequest.getId()));
            model.addObject("message", "DeleteCommand was successfully performed");
        }
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid @ModelAttribute("options") UpdateCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("home");
        if (result.hasErrors()) {
            model.setViewName("WEB-INF/view/update_options.jsp");
        }
        else {
            obsoleteLibService.update(Integer.valueOf(modelRequest.getId()), new Book(modelRequest.getAuthor(),
                    modelRequest.getTitle(), modelRequest.getYear()));
            model.addObject("message", "UpdateCommand was successfully performed");
        }
        return model;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@Valid @ModelAttribute("options") SearchCommandOptions modelRequest, BindingResult result) {
        ModelAndView model = new ModelAndView("WEB-INF/view/result.jsp");
        if (result.hasErrors()) {
            model.setViewName("WEB-INF/view/search_options.jsp");
        }
        else {
            List<Book> bookList = obsoleteLibService.search(new Book(modelRequest.getAuthor(), modelRequest.getTitle(), modelRequest.getYear()));
            model.addObject("message", bookList);
        }
        return model;
    }
}
