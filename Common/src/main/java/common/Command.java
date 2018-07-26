package common;

public interface Command<CommandOptions, GlobalOptions> {

    ViewModel execute(CommandOptions commandOptions, GlobalOptions globalOptions);

    //тесты!!!   (можно не делать для valueClass(?))
    //в командах (AddCommand) дженерики и для глобальных опций (+)
    //exceptions (some fixed, can add causes to some other) (+-)
    //mockito проверка метода (проверка выполнения метода?)
    //try with resourses (+)
    //в databaseconnector сделать локальные переменные для методов (по крайней мере для connection) (+)
    //в селекторе убрать рефлексию (можно через if-ы) (+)
}