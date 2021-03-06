package type.reallifeobject.coffeemaker.v2.exceptions;

public class BaseNotSelectedException extends RuntimeException {
    public BaseNotSelectedException() {
        this("Base is not selected for this coffee");
    }

    public BaseNotSelectedException(String msg) {
        super(msg);
    }
}
