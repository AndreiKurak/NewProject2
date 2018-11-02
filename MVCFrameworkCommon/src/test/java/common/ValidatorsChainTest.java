package common;

import org.junit.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ValidatorsChainTest {
    /*
    private class TypeValidator implements OptionValidator {
        public boolean check(String s) { return true; }
    }

    private class DateBorderValidator implements OptionValidator {
        public boolean check(String s) { return true; }
    }

    @Test
    public void shouldNotExecuteSecondCheckWhenFirstIsNotSuccessful(){
        DateBorderValidator dateValidator = mock(DateBorderValidator.class);
        TypeValidator typeValidator = mock(TypeValidator.class);
        when(typeValidator.check(Matchers.<String>anyObject())).thenReturn(false);

        List<OptionValidator> validators = new ArrayList<>();
        validators.add(typeValidator);
        validators.add(dateValidator);

        ValidatorsChain validatorsChain = new ValidatorsChain(validators);
        validatorsChain.check("value");

        verify(dateValidator, times(0)).check(Matchers.<String>anyObject());
    }

    @Test
    public void shouldExecuteAllChecks(){
        DateBorderValidator dateValidator = mock(DateBorderValidator.class);
        TypeValidator typeValidator = mock(TypeValidator.class);
        when(typeValidator.check(Matchers.<String>anyObject())).thenReturn(true);

        List<OptionValidator> validators = new ArrayList<>();
        validators.add(typeValidator);
        validators.add(dateValidator);

        ValidatorsChain validatorsChain = new ValidatorsChain(validators);
        validatorsChain.check("value");

        verify(typeValidator, times(1)).check(Matchers.<String>anyObject());
        verify(dateValidator, times(1)).check(Matchers.<String>anyObject());

    }
    */
}
