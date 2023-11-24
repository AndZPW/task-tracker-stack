package dev.andzwp.taskservice.exception;

public class NoSuchTaskException extends Exception {

    public NoSuchTaskException(){
        super("No such task");
    }

    public NoSuchTaskException(String message) {
        super(message);
    }
}
