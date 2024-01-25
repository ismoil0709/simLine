package uz.pdp.simline.exception;

public class TransactionFailedException extends RuntimeException {

    public TransactionFailedException(String s) {
        super(s);
    }
}
