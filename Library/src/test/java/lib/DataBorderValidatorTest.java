package lib;

import lib.validators.DateBorderValidator;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataBorderValidatorTest {

    @Test
    public void shouldReturnTrueWhenDataConsistsOfYearThatPassed(){
        DateBorderValidator validator = new DateBorderValidator();
        assertThat(validator.check("2018")).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenDataDoesNotConsistOfYearThatPassed(){
        DateBorderValidator validator = new DateBorderValidator();
        assertThat(validator.check("2020")).isFalse();
    }

    @Test (expected = NumberFormatException.class)
    public void shouldThrowExceptionIfArgumentValueCannotBeConvertedToInteger(){
        DateBorderValidator validator = new DateBorderValidator();
        validator.check("Thirty Four");
    }
}
