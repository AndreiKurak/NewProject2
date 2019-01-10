package examples_test.chapter1_4;

public abstract class CommandManager {

    public Object process(String someString) {
        AnotherBean command = createCommand();
        command.setSomething(someString);
        return command;
    }

    /*@Lookup("myCommand")*/
    protected abstract AnotherBean createCommand();
}
