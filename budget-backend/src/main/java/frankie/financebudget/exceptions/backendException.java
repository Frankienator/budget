package frankie.financebudget.exceptions;

public class backendException extends RuntimeException{

    public backendException(int code, String message) {
        throw new RuntimeException(code + "|" + message);
    }
}
