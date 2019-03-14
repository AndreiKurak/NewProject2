package spring_boot_library.command_options;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddCommandOptions {

    @NotNull(message = "Missed option")
    @Size(min = 2, message = "Too short value of option")
    private String author;

    @NotNull(message = "Missed option")
    @Size(min = 2, message = "Too short value of option")
    private String title;

    @Pattern(regexp = "(|\\d+)", message = "Incorrect type of option")
    private String year;
}
