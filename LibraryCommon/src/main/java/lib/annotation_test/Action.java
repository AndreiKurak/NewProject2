package lib.annotation_test;

import java.lang.reflect.Method;

public class Action {

    public void doAction() throws Exception {
        Class cl = Class.forName("lib.annotation_test.MyCommand");
         
        if(!cl.isAnnotationPresent(ControlledObject.class))
            System.err.println("no annotation");
        else
            System.out.println("class annotated ; name  -  " + cl.getAnnotation(ControlledObject.class));

        boolean hasStart=false;
        Method[] method = cl.getMethods();
        for(Method md: method){
            if(md.isAnnotationPresent(PrintCommand.class)) {hasStart=true;}
            if (hasStart)
                System.out.print(md.getAnnotation(PrintCommand.class).print());
        }
        System.out.println("Start annotation  - " + hasStart);
    }

    public static void main(String args[]) throws Exception {
        MyCommand command = new MyCommand();
        command.message();
    }
}
