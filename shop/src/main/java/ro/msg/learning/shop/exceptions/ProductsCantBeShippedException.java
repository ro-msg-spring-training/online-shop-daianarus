package ro.msg.learning.shop.exceptions;

public class ProductsCantBeShippedException extends RuntimeException{
    public ProductsCantBeShippedException(String message){
        super(message);
    }
}
