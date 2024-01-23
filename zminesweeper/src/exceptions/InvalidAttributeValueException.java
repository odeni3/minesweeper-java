package exceptions;

import java.io.Serializable;

public class InvalidAttributeValueException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public InvalidAttributeValueException(String message) {
        super(message);
    }
}
