package br.com.jessikafujimura.todolist.exception;

public class DateValidationException extends RuntimeException{
    
    public DateValidationException(String message){
        super(message);
    }
}
