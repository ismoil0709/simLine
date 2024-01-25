package uz.pdp.simline.exception;

public class AlreadyTakenException extends RuntimeException {

    public AlreadyTakenException(String m) {
        super(m + " already taken");
    }
}
