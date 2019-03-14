package spring_boot_library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import spring_boot_library.Book;
import spring_boot_library.LibService;
import spring_boot_library.command_options.AddCommandOptions;
import spring_boot_library.command_options.DeleteCommandOptions;
import spring_boot_library.command_options.SearchCommandOptions;
import spring_boot_library.command_options.UpdateCommandOptions;

@Controller
@RequestMapping("/options")
public class OptionsController {

    @Autowired
    LibService anotherLibService;

    @RequestMapping(value = "/add")
    public String add(@ModelAttribute("options")AddCommandOptions addCommandOptions) {
        return "WEB-INF/view/add_options.jsp";
    }

    @RequestMapping(value = "/delete")
    public String delete(@ModelAttribute("options")DeleteCommandOptions deleteCommandOptions) {
        return "WEB-INF/view/delete_options.jsp";
    }

    @RequestMapping(value = "/search")
    public String search(@ModelAttribute("options")SearchCommandOptions searchCommandOptions) {
        return "WEB-INF/view/search_options.jsp";
    }

    @RequestMapping(value = "/update")
    public String update(@ModelAttribute("options")UpdateCommandOptions updateCommandOptions) {
        Book book = anotherLibService.findById(updateCommandOptions.getId());
        updateCommandOptions.setAuthor(book.getAuthor());
        updateCommandOptions.setTitle(book.getTitle());
        updateCommandOptions.setYear(book.getYear());
        return "WEB-INF/view/update_options.jsp";
    }
}
