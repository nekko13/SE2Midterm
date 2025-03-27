package fit.se2.SE2Midterm.controller;

import fit.se2.SE2Midterm.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        model.addAttribute("cart", cartService.getCart(session));
        return "Home/cart";
    }

    @PostMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable Long productId, HttpSession session) {
        cartService.addToCart(productId, session);
        return "redirect:/cart";
    }
}