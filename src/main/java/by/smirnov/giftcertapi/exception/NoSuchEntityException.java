package by.smirnov.giftcertapi.exception;

public class NoSuchEntityException extends RuntimeException {

    private static final String NOT_FOUND_MESSAGE = "Wrong ID, object not found";

    public NoSuchEntityException() {
        super(NOT_FOUND_MESSAGE);
    }

    @Override
    public String toString() {
        return "NoSuchEntityException{}" + super.toString();
    }
}
