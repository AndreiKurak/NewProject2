package lib;

import lib.validators.DateBorderValidator;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataBorderValidatorTest {

    @Test
    public void shouldReturnTrueWhenDataConsistsOfYearThatPassed(){  //names
        DateBorderValidator validator = new DateBorderValidator();
        assertThat(validator.check("2018")).isNullOrEmpty();
    }

    @Test
    public void shouldReturnFalseWhenDataDoesNotConsistOfYearThatPassed(){ //names
        DateBorderValidator validator = new DateBorderValidator();
        assertThat(validator.check("2020")).isNotNull();
    }

    @Test (expected = NumberFormatException.class)
    public void shouldThrowExceptionIfArgumentValueCannotBeConvertedToInteger(){
        DateBorderValidator validator = new DateBorderValidator();
        validator.check("Thirty Four");
    }

}
