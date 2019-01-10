package examples_test.chapter1_4;

import java.util.Properties;

public class ExampleBean {

    private AnotherBean beanOne;

    private int i;

    private Properties collection;

    public ExampleBean(AnotherBean beanOne, int i) {
        /*System.out.println("exampleBean was initialized");*/
        this.beanOne = beanOne;
        this.i = i;
    }

    public void setCollection(Properties collection) {
        this.collection = collection;
    }

    public int getInt() {
        return i;
    }

    public Properties getCollection() {
        return collection;
    }
}
