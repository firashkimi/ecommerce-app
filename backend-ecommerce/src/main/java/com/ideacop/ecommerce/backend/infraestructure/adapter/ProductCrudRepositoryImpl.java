package com.ideacop.ecommerce.backend.infraestructure.adapter;

import com.ideacop.ecommerce.backend.domain.model.Product;
import com.ideacop.ecommerce.backend.domain.port.IProductRepository;
import com.ideacop.ecommerce.backend.infraestructure.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCrudRepositoryImpl implements IProductRepository {
    private final IProductCrudRepository iProductCrudRepository;
    private final ProductMapper productMapper;

    public ProductCrudRepositoryImpl(IProductCrudRepository iProductCrudRepository, ProductMapper productMapper) {
        this.iProductCrudRepository = iProductCrudRepository;
        this.productMapper = productMapper;
    }


    @Override
    public Product save(Product product) {
        return productMapper.toProduct(iProductCrudRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public Iterable<Product> findAll() {
        return productMapper.toProductList(iProductCrudRepository.findAll());
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.toProduct(iProductCrudRepository.findById(id).get());
    }

    @Override
    public void deleteById(Integer id) {
        iProductCrudRepository.deleteById(id);
    }
}
