package uz.pdp.simline.exception;

public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(String m) {
        super("Invalid " + m);
    }
}
