package hr.antealjinovic.exception;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String message) {
        super(message);
    }
}
