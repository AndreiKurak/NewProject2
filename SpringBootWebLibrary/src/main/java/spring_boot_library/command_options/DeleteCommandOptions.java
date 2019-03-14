package spring_boot_library.command_options;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DeleteCommandOptions {

    @Getter
    @Setter
    @NotNull(message = "Missed option")
    //@Pattern(regexp = "\\d+", message = "Incorrect type of option")
    private Integer id;
}
