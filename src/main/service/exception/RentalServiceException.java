package service.exception;

/**
 * Base exception for the rental service
 *
 * @Author Michael Pascale
 */
public class RentalServiceException extends Exception{
    public RentalServiceException(String errorMessage) {
        super(errorMessage);
    }
}
