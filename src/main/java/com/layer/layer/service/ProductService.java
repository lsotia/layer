package com.layer.layer.service;

import com.layer.layer.dto.ProductCreateDto;
import com.layer.layer.entity.Product;
import com.layer.layer.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
/* private final ProductRepository productRepository;
* 이건 Spring이 자동으로 Repository를 넣어준다(DI, 의존성 주입)는 의미

앞으로 이 패턴을 계속 사용함
*  */

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void save(ProductCreateDto dto){

        Product product = new Product();

        product.setName(dto.getName());

        product.setBrand(dto.getBrand());

        product.setPrice(dto.getPrice());

        product.setImage(dto.getImage());

        product.setDescription(dto.getDescription());

        productRepository.save(product);

    }

    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    public void update(Long id, ProductCreateDto dto){
        Product product = getProduct(id);

        product.setName(dto.getName());

        product.setBrand(dto.getBrand());

        product.setPrice(dto.getPrice());

        product.setImage(dto.getImage());

        product.setDescription(dto.getDescription());

        productRepository.save(product);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> search(String keyword){
        return productRepository.findByNameContainingOrBrandContaining(keyword,keyword);
    }

    public List<Product> findAll(){

        return productRepository.findAll();

    }

    public List<String> findBrands() {
        return productRepository.findDistinctBrands();
    }

    public List<Product> findByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }
    public List<Product> findNewest(){

        return productRepository.findAllByOrderByIdDesc(
                PageRequest.of(0,12)
        );

    }



}



/*Security
        ↓
username
        ↓
Repository
        ↓
Member 객체
        ↓
Service*/