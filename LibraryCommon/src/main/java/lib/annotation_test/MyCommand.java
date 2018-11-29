package lib.annotation_test;

@ControlledObject(name ="controlled object")
public class MyCommand {

    @PrintCommand(print = "message")
    public void message() {
        try {
            new Action().go();
        }
        catch (Exception ex) {
            
        }
        //System.out.println("message");
    }
}
