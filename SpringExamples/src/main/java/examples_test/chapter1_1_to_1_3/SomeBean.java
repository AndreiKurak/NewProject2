package examples_test.chapter1_1_to_1_3;

public class SomeBean {

    private static SomeBean someBean = new SomeBean();

    public static SomeBean createInstance() {
        return someBean;
    }

    @Override
    public String toString() {
        return "SomeBean";
    }
}
