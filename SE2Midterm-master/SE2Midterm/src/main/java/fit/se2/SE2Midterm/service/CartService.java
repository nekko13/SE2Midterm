package fit.se2.SE2Midterm.service;

import fit.se2.SE2Midterm.model.Cart;
import fit.se2.SE2Midterm.model.Product;
import fit.se2.SE2Midterm.repository.CartRepository;
import fit.se2.SE2Midterm.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public Cart addToCart(Long productId, HttpSession session) {
        // Lấy hoặc tạo mới Cart từ session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        // Tìm sản phẩm
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Thêm sản phẩm vào giỏ hàng
        cart.addItem(product);

        // Lưu cart vào session
        session.setAttribute("cart", cart);

        // Lưu cart vào database (nếu cần)
        return cartRepository.save(cart);
    }

    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}