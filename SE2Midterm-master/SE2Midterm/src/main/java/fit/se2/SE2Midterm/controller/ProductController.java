package fit.se2.SE2Midterm.controller;

import fit.se2.SE2Midterm.model.Product;
import fit.se2.SE2Midterm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    // Loại bỏ phương thức home với ánh xạ "/"
    @GetMapping("/product-list")
    public String listProducts(Model model) {
        List<Product> dealProducts = productService.getProductsByCategory("Deals");
        List<Product> shopNowProducts = productService.getProductsByCategory("Shop Now");

        model.addAttribute("dealProducts", dealProducts);
        model.addAttribute("shopNowProducts", shopNowProducts);

        return "Home/index";
    }
}