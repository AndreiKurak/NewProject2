package spring_library.command_options;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class DeleteCommandOptions {

    @NotNull(message = "Missed option \"id\"")
    @Digits(integer = 3, fraction = 0, message = "Incorrect type of option \"id\"")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
