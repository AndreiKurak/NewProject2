package examples_test.chapter1_1_to_1_3;

public class ServiceBeanFactory {

    private ServiceBean serviceBean = new ServiceBean();

    public ServiceBean createServiceBeanInstance() {
        return serviceBean;
    }
}
