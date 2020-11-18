package exceptions;

public class InvalidEnvironmentException extends IllegalArgumentException {

    public InvalidEnvironmentException(String message, String env) {
        super(String.format(message + ": '%s'", env));
    }
}
