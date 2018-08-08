package common;

import common.options_setter.OptionsSetter;
import common.options_setter.OptionsSetterException;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionsSetterTest {

    private final class AddCommandOptions {
        private String author;
        private String title;
        private String year;
    }

    private final OptionsSetter optionsSetter = new OptionsSetter();

    @Test
    public void shouldSetInputValueOfRequiredOptionCorrectly(){
        AddCommandOptions addCommandOptions = new AddCommandOptions();
        optionsSetter.setOptions("author", "Andrey", addCommandOptions);
        assertThat(addCommandOptions.author).isEqualTo("Andrey");
    }

    @Test
    public void shouldSetInputValueOfAnotherRequiredOptionCorrectly(){
        AddCommandOptions addCommandOptions = new AddCommandOptions();
        optionsSetter.setOptions("title", "GatheringForgotten", addCommandOptions);
        assertThat(addCommandOptions.title).isEqualTo("GatheringForgotten");
    }

    @Test (expected = OptionsSetterException.class)
    public void shouldThrowExceptionIfInArgumentsWrongFieldName(){
            AddCommandOptions addCommandOptions = new AddCommandOptions();
            optionsSetter.setOptions("authhor", "Andrey", addCommandOptions);
    }

    @Test
    public void shouldLeaveFieldValueNullIfTheArgumentIsNull(){
        AddCommandOptions addCommandOptions = new AddCommandOptions();
        optionsSetter.setOptions("author", null, addCommandOptions);
        assertThat(addCommandOptions.author).isNull();
    }
}
