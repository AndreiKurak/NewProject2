package web_app.commands;

import common.Command;
import common.ViewModel;

public class AddCommandShow implements Command {

    @Override
    public ViewModel execute(Object o, Object o2) {
        ViewModel viewModel = new ViewModel();
        viewModel.setViewName("AddShowView");
        return viewModel;
    }
}
