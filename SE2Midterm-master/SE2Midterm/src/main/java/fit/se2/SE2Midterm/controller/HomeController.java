package fit.se2.SE2Midterm.controller;

import fit.se2.SE2Midterm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("dealProducts", productService.getProductsByCategory("Deals"));
        model.addAttribute("shopNowProducts", productService.getProductsByCategory("Shop Now"));
        return "Home/index";
    }
    @GetMapping("/cart")
    public String cart() {
        return "Home/cart";  // Đảm bảo đúng đường dẫn đến file cart.html
    }
}