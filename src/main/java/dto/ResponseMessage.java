package dto;

import java.util.List;

public class ResponseMessage {
    private String message;
    private User user;
    private List<User.Product> products;
    private User.Cart cart;


    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(String message, User user, List<User.Product> products, User.Cart cart) {
        this.message = message;
        this.user = user;
        this.products = products;
        this.cart = cart;
    }

    public ResponseMessage(String userRegisteredSuccessfully, User user) {
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User.Product> getProducts() {
        return products;
    }

    public void setProducts(List<User.Product> products) {
        this.products = products;
    }

    public User.Cart getCart() {
        return cart;
    }

    public void setCart(User.Cart cart) {
        this.cart = cart;
    }
}

