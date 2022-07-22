package rca.risbo.gestiondestock.exception;

import lombok.Getter;

/**
 * @author ybgabamano
 *  Leve une exception pour une entité qui n'existe pas dans la BDD
 *  ou lorsque l'on cherche des données qui n'existe pas dans la BDD
 */
public class EntityNotFoundException extends RuntimeException{

    @Getter
    private ErrorCodes errorCodes;

    public EntityNotFoundException(String message){
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public EntityNotFoundException(String message, Throwable cause, ErrorCodes errorCodes){
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    public EntityNotFoundException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }

}
