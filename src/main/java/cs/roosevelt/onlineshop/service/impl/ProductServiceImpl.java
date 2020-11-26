package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.entity.Product;
import cs.roosevelt.onlineshop.repository.ProductRepository;
import cs.roosevelt.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    // retrieve all the products from the db
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
