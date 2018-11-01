package framework_console;

import common.parser.ParseException;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleParametersParserTest {

    @Test
    public void shouldThrowExceptionInformingAboutNullInputLine() {
        try {
            String[] input = {""};
            ConsoleParametersParser consoleParse = new ConsoleParametersParser(input);
            throw new ParseException("Not Empty Line");
        }
        catch (ParseException parseException) {
            assertThat(parseException.getMessage()).isEqualTo("Empty input line");
        }
    }
}
