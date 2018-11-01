package web_app.commands;

import common.Command;
import common.ViewModel;

public class SearchCommandShow implements Command {

    @Override
    public ViewModel execute(Object o, Object o2) {
        ViewModel viewModel = new ViewModel();
        viewModel.setModel("/options_input/search_options.jsp");
        viewModel.setViewName("OptionsView");
        return viewModel;
    }
}
