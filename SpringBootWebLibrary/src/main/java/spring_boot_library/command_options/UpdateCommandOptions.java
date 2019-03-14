package spring_boot_library.command_options;

import lombok.Getter;
import lombok.Setter;
import spring_boot_library.Book;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UpdateCommandOptions {

    @NotNull(message = "Missed option")
    //@Pattern(regexp = "\\d+", message = "Incorrect type of option")
    private Integer id;

    private String author;

    private String title;

    @Pattern(regexp = "(|\\d+)", message = "Incorrect type of option")
    private String year;
}
