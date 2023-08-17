package rikkei.academy.service;

import org.springframework.stereotype.Service;
import rikkei.academy.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
        products.add(new Product(1, "Quần Jean", 1200, true));
        products.add(new Product(2, "Áo Khoác", 1300, true));
        products.add(new Product(3, "Giầy Da", 1100, false));
        products.add(new Product(4, "Mũ Cối", 1000, true));
    }

    public List<Product> findAll() {
        return products;
    }

    public void save(Product product) {
        if(product.getId() == 0) {
            product.setId(getNewId());
            products.add(product);
        } else {
            products.set(products.indexOf(findById(product.getId())),product);
        }
    }

    public void delete(int id) {
        products.remove(findById(id));
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public int getNewId() {
        int idMax = 0;
        for (Product p : products) {
            if (p.getId() > idMax) {
                idMax = p.getId();
            }
        }
        return idMax + 1;
    }

}
