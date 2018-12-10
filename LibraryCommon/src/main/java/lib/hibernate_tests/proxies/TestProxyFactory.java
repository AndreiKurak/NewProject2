package lib.hibernate_tests.proxies;

import lib.hibernate_tests.TestBook;
import org.hibernate.Session;

import java.lang.reflect.Method;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class TestProxyFactory {

    private String getSourceCode(Class entity) {
        StringBuffer sourceCodeBuffer = new StringBuffer("import lib.hibernate_tests.*; ");

        //sourceCode = "import lib.hibernate_tests.*; ";
        String entityClassName = entity.getSimpleName();
        sourceCodeBuffer.append("public class " + entityClassName + "Proxy extends " + entityClassName + " {");

        String entityVariable = "entity";
        sourceCodeBuffer.append(" private " + entityClassName + " " + entityVariable + "; ");
        Method[] methods = entity.getMethods();
        String methodName;
        String argumentVariable;
        String logic = "if (" + entityVariable + " == null) " + entityVariable + " = new " + entityClassName + "(); ";
        for (int i = 0; i < methods.length; i++) {
            methodName = methods[i].getName();
            if (methodName.contains("get") && !methodName.equals("getClass")) {
                sourceCodeBuffer.append("public " + methods[i].getReturnType().getSimpleName() + " " + methodName + "() { "
                        + logic + " return " + entityVariable + "." + methodName + "(); } ");
            }
            if (methodName.contains("set")) {
                argumentVariable = methodName.replace("set", "").toLowerCase();
                sourceCodeBuffer.append("public void " + methodName + "(" + methods[i].getParameterTypes()[0].getSimpleName() + " " + argumentVariable
                        + ") { " + logic + " " + entityVariable + "." + methodName + "(" + argumentVariable + "); } ");
            }
        }
        sourceCodeBuffer.append(" }");

        return sourceCodeBuffer.toString();
    }

    public Object createProxy(Class entityClass/*, int id, Session session*/) {
        try {
            File sourceFile = File.createTempFile(entityClass.getSimpleName() + "Proxy", ".java");
            sourceFile.deleteOnExit();

            // generate the source code, using the source filename as the class name
            String classname = sourceFile.getName().split("\\.")[0];
            String sourceCode = getSourceCode(entityClass);

            sourceCode = sourceCode.replace(entityClass.getSimpleName() + "Proxy", classname);

            // write the source code into the source file
            FileWriter writer = new FileWriter(sourceFile);
            writer.write(sourceCode);
            writer.close();

            // compile the source file
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            File parentDirectory = sourceFile.getParentFile();
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
            compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
            fileManager.close();

            // load the compiled class
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{parentDirectory.toURI().toURL()});
            Object proxyObject = classLoader.loadClass(classname).newInstance();
            return  proxyObject;

        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static Object createProxyByAndotherWay(Object ob) {
        return Proxy.newProxyInstance(ob.getClass().getClassLoader(),
                new Class<?>[]{ob.getClass()}, new SomeInvocationHandler(ob));
    }
}
