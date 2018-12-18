package lib.hibernate_tests.proxies.method2;

import com.sun.java.browser.net.ProxyService;
import lib.hibernate_tests.TestBook;
import sun.misc.ProxyGenerator;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyCreator {

    public Object createProxy(Class entityClass) {
        try {
            InvocationHandler handler = new MyInvocationHandler(entityClass.newInstance());
            /*Class proxyClass = Proxy.getProxyClass(entityClass.getClassLoader(), entityClass.getInterfaces());
            Object proxy = proxyClass.getConstructor(new Class[] {InvocationHandler.class }).newInstance(new Object[] { handler });*/

            TestBookInterface entityInterface = (TestBookInterface) Proxy.newProxyInstance(entityClass.getClassLoader(),
                    entityClass.getInterfaces(),
                    handler);
            //ProxyGenerator.generateProxyClass()
            return entityInterface;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {     
        ProxyCreator proxyCreator = new ProxyCreator();
        TestBookInterface myEntityProxy = (TestBookInterface) proxyCreator.createProxy(TestBook.class);
        myEntityProxy.setAuthor("Takumi");
        System.out.println(myEntityProxy.getAuthor());
    }
}
