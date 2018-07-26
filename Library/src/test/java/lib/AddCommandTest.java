package lib;

import common.ViewModel;
import common.views.MessageView;
import lib.commands.AddCommand;
import lib.command_options.AddCommandOptions;
import lib.global_options.GlobalOptions;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class AddCommandTest {

    private static class AddCommandOptions extends lib.command_options.AddCommandOptions {

        private String author = "Aleksey";
        private String title = "Mirror";
        private String year;

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getYear() {
            return year;
        }
    }

    private static class GlobalOptions extends lib.global_options.GlobalOptions{

        private String file;
        private String database = "library";

        public String getFile() {
            return file;
        }

        public String getDatabase() {
            return database;
        }
    }

    @Test
    public void shouldReturnViewModel(){
        ViewModel viewModel = new ViewModel();
        viewModel.model = "Add-command was performed";
        viewModel.view = new MessageView();

        AddCommand addCommand = mock(AddCommand.class);
        when(addCommand.execute(new AddCommandOptions(), new GlobalOptions())).thenReturn(mock(ViewModel.class));
        assertThat(addCommand.execute(new AddCommandOptions(), new GlobalOptions())).isEqualTo(viewModel);
        //assertThat(new AddCommand().execute(new AddCommandOptions(), new GlobalOptions()).model).isEqualTo(viewModel.model);


    }
}
