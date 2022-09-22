package ro.msg.learning.shop.exceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(Integer id) {
        super("The product " + id + " is not in our stock right now");
    }
}
