package service.exception;

/**
 * Exception for invalid discounts
 *
 * @Author Michael Pascale
 */
public class InvalidDiscountException extends RentalServiceException{
    public InvalidDiscountException(String errorMessage) {
        super(errorMessage);
    }
}
