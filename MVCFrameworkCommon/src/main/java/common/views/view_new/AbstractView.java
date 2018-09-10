package common.views.view_new;

import common.views.OutputWindowView;

public class AbstractView {

    private OutputWindowView outputWindowView;

    public AbstractView(OutputWindowView outputWindowView){
        this.outputWindowView = outputWindowView;
    }

    public OutputWindowView getOutputWindowView() {
        return outputWindowView;
    }
    
    /*
    public class MessageView extends AbstractView implements View<String> {

    MessageView(OutputWindowView outputWindowView) {
        super(outputWindowView);
    }

    @Override
    public void showResult(String model) {

    }
}
     */
}
