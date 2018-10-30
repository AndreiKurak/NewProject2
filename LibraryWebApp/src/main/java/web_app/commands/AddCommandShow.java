package web_app.commands;

import framework_web.CommandShow;

public class AddCommandShow implements CommandShow {

    public String showOptions() {
        return "/options_input/add_options.jsp";
    }
}
