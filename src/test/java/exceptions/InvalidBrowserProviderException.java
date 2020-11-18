package exceptions;

public class InvalidBrowserProviderException extends IllegalArgumentException {

    public InvalidBrowserProviderException(String message, String browser) {
        super(String.format(message + ": '%s'", browser));
    }
}
