package lib.annotation_test;

@ControlledObject(name ="controlled object")
public class MyCommand {

    @PrintCommand(print = "message")
    public void message() {
        //System.out.println("message: ");
        try {
            new Action().doAction();
        }
        catch (Exception ex) {
            
        }
    }
}
