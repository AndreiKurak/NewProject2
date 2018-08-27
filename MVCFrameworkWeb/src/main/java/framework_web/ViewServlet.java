package framework_web;

import common.ApplicationDescriptor;
import common.CommandWithOptions;
import common.ViewModel;
import common.parser.Parser;
import common.views.ErrorView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view_servlet")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] args = (String[]) req.getSession().getAttribute("query");
        ApplicationDescriptor descriptor = (ApplicationDescriptor) req.getSession().getAttribute("libDescriptor");
        
        Parser parser = new Parser();

        CommandWithOptions command = parser.parse(args, descriptor.getGlobalOptionsDescriptionList(), descriptor.getCommandsDescriptionList(), descriptor.getGlobalOptions());

        ViewModel viewModel = command.getCommand().execute(command.getCommandOptions(), command.getGlobalOptions());

        if (viewModel.getView() != null){
            viewModel.getView().showResult(viewModel.getModel(), new WebPagePrinter(req, resp));
        }
        else {
            viewModel.setView(new ErrorView());
            viewModel.getView().showResult("view не задано", new WebPagePrinter(req, resp));
        }
    }
}
