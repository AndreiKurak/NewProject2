package lib;

import lib.validators.TypeValidator;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeValidatorTest {

    @Test
    public void shouldReturnTrueWithStringArgument(){
        TypeValidator typeValidator = new TypeValidator(new String());
        assertThat(typeValidator.check("string")).isNullOrEmpty();
    }

    @Test
    public void shouldReturnTrueWithIntegerArgument(){
        TypeValidator typeValidator = new TypeValidator(new Integer(0));
        assertThat(typeValidator.check("10")).isNullOrEmpty();
    }

}
