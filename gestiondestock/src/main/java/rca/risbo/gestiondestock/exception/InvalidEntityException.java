package rca.risbo.gestiondestock.exception;

import lombok.Getter;

import java.util.List;

/**
 * @author ybgabamano
 *  Leve une exception pour une entité qui n'est pas valide lors
 *  de l'enregistrement ou de la MAJ dans la BDD
 */
public class InvalidEntityException extends RuntimeException{

    @Getter
    private ErrorCodes errorCodes;
    @Getter
    private List<String> errors;

    public InvalidEntityException(String message){
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidEntityException(String message, Throwable cause, ErrorCodes errorCodes){
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    public InvalidEntityException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }

    public InvalidEntityException(String message, ErrorCodes errorCodes, List<String> errors){
        super(message);
        this.errorCodes = errorCodes;
        this.errors = errors;
    }

}
