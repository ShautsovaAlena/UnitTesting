package parser;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException() {
        super();
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
