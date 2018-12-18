package lib.hibernate_tests.proxies.method2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            return method.invoke(object, args);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
