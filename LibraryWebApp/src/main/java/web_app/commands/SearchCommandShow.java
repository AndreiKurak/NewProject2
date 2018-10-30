package web_app.commands;

import framework_web.CommandShow;

public class SearchCommandShow implements CommandShow {

    @Override
    public String showOptions() {
        return "/options_input/search_options.jsp";
    }
}
