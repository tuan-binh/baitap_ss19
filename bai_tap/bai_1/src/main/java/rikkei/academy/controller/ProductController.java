package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Product;
import rikkei.academy.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("home", "products", productService.findAll());
    }

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("add", "product", new Product());
    }

    @PostMapping("/finish/add")
    public String finish(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("view/{id}")
    public ModelAndView view(@PathVariable int id) {
        return new ModelAndView("view", "product", productService.findById(id));
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        return new ModelAndView("edit", "product", productService.findById(id));
    }

    @PostMapping("update")
    public String update(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/";
    }

    @PostMapping("search")
    public ModelAndView search(@RequestParam("search") String text) {
        List<Product> list = new ArrayList<>();
        for (Product p : productService.findAll()) {
            if (p.getName().toLowerCase().contains(text.toLowerCase())) {
                list.add(p);
            }
        }
        return new ModelAndView("home","products",list);
    }

}
