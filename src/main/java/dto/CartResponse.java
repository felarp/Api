package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class CartResponse {
    private Cart cart;
    private double totalPrice;
    private double totalDiscount;

    public CartResponse() {
    }

    public CartResponse(Cart cart, double totalPrice, double totalDiscount) {
        this.cart = cart;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cart {
        private int id;
        private String name;
        private String category;
        private double price;
        private int discount;
        private int quantity;


        public Cart(int id, String name, String category, double price, int discount, int quantity) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
            this.discount = discount;
            this.quantity = quantity;
        }

        public Cart(int id, int quantity) {
            this.id = id;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}









