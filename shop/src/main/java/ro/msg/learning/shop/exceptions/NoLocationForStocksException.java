package ro.msg.learning.shop.exceptions;

public class NoLocationForStocksException extends RuntimeException {
    public NoLocationForStocksException(String message) {
        super(message);
    }
}
