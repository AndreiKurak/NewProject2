package lib.annotation_test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Action {

    public void go() throws Exception {
        Class cl = Class.forName("lib.annotation_test.MyCommand");
         
        if(!cl.isAnnotationPresent(ControlledObject.class)){
            System.err.println("no annotation");
        } else {
            System.out.println("class annotated ; name  -  " + cl.getAnnotation(ControlledObject.class));
        }
        boolean hasStart=false;
        boolean hasStop=false;
        Method[] method = cl.getMethods();
        for(Method md: method){
            if(md.isAnnotationPresent(PrintCommand.class)) {hasStart=true;}
            if (hasStart)
                System.out.println(md.getAnnotation(PrintCommand.class).print());
            if(hasStart && hasStop){break;}
        }
        System.out.println("Start annotaton  - " + hasStart + ";  Stop annotation  - " + hasStop );
    }

    public static void main(String args[]) throws Exception {
        MyCommand command = new MyCommand();
        command.message();
    }
}
