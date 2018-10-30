package web_app.commands;

import framework_web.CommandShow;

public class UpdateCommandShow implements CommandShow {

    @Override
    public String showOptions() {
        return "/options_input/update_options.jsp";
    }
}
