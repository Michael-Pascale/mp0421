package service.exception;

/**
 * Exception for invalid rental durations
 *
 * @Author Michael Pascale
 */
public class InvalidDurationException extends RentalServiceException {
    public InvalidDurationException(String errorMessage) {
        super(errorMessage);
    }
}
