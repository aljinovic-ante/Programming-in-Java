package hr.antealjinovic.exception;

public class SameRecordExistsException extends RuntimeException {
    public SameRecordExistsException(String message) {
        super(message);
    }
}
