package lib.hibernate_tests.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SomeInvocationHandler implements InvocationHandler {
    private Object obj;

    public SomeInvocationHandler(Object f1){
        obj = f1;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("SomeInvocationHandler invoke : " + method.getName());
        return method.invoke(obj, args);
    }
}
