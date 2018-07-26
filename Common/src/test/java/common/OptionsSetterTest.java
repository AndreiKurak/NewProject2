package common;

import common.options_setter.OptionsSetter;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionsSetterTest {

    private static final class AddCommandOptions {
        private String author;
        private String title;
        private String year;
    }
    private static final OptionsSetter optionsSetter = new OptionsSetter();

    @Test
    public void shouldSetInputValueOfRequiredOptionCorrectly(){
        AddCommandOptions addCommandOptions = new AddCommandOptions();
        optionsSetter.setOptions("author", "Andrey", addCommandOptions);
        assertThat(addCommandOptions.author).isEqualTo("Andrey");

        optionsSetter.setOptions("title", "GatheringForgotten", addCommandOptions);
        assertThat(addCommandOptions.title).isEqualTo("GatheringForgotten");
    }
}
