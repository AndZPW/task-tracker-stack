package dev.andzwp.taskservice.exception;

public class TaskIsAlreadyCreatedException extends Exception{
    public TaskIsAlreadyCreatedException(String message){
        super(message);
    }
}
