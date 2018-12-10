package lib.hibernate_tests.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SomeInvocationHandler implements InvocationHandler {

    private Object obj;

    public SomeInvocationHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try{
            if(m.getName().indexOf("get")>-1){
                System.out.println("...get Method Executing...");
            }else{
                System.out.println("...set Method Executing...");
            }
            result = m.invoke(obj, args);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
