package examples_test.chapter1_6;

public class ExampleBean {  /*implements InitializingBean; implements DisposableBean*/

    private String message;

    public String getMessage() {
        return message;
    }

    public void init() {
        message = "init";
        // do some initialization work
    }

    public void cleanup() {
        message = "cleanup";
        // do some destruction work (like releasing pooled connections)
    }
}
