package ro.msg.learning.shop.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer id){
        super("The product " + id + " does not exists");
    }
}
