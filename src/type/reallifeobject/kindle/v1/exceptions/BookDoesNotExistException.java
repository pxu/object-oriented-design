package type.reallifeobject.kindle.v1.exceptions;

public class BookDoesNotExistException extends RuntimeException {
    public BookDoesNotExistException() {
        this("This book is not in this kindle, please download first!");
    }

    public BookDoesNotExistException(String message) {
        super(message);
    }
}
