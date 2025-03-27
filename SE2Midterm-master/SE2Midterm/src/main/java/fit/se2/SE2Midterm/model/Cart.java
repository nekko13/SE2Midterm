package fit.se2.SE2Midterm.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    private double totalPrice;

    public void addItem(Product product) {
        // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng chưa
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                calculateTotalPrice();
                return;
            }
        }

        // Nếu chưa tồn tại, thêm mới
        CartItem newItem = new CartItem();
        newItem.setProduct(product);
        newItem.setQuantity(1);
        items.add(newItem);
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        totalPrice = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}