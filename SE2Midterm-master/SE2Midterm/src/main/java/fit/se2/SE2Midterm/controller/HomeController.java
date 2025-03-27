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
        // Fetch all products with original_price (which indicates a deal)
        model.addAttribute("dealProducts", productService.getProductsWithOriginalPrice());
        model.addAttribute("shopNowProducts", productService.getProductsByCategory("Kitchen Appliances"));
        return "Home/index";
    }
}