package spring_boot_library.command_options;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SearchCommandOptions {

    private String author;

    private String title;

    @Pattern(regexp = "(|\\d+)", message = "Incorrect type of option")
    private String year;
}
