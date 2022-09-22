package ro.msg.learning.shop.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
