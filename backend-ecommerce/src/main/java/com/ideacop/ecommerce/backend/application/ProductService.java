package com.ideacop.ecommerce.backend.application;

import com.ideacop.ecommerce.backend.domain.model.Product;
import com.ideacop.ecommerce.backend.domain.port.IProductRepository;
import com.ideacop.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ProductService {
    private final IProductRepository iProductRepository;
    private final UploadFile uploadFile;


    public ProductService(IProductRepository iProductRepository, UploadFile uploadFile
    ) {
        this.iProductRepository = iProductRepository;
        this.uploadFile = uploadFile;
    }

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        if(product.getId() != null && product.getId() != 0){ // Si es una actualización

            product.setUserId(product.getUserId());
            if(multipartFile == null && product.getUrlImage().isBlank()) {
                deleteImage(product.getId());
            }

            if(multipartFile != null) {
                deleteImage(product.getId());
                product.setUrlImage(uploadFile.upload(multipartFile));
            } else {
                product.setUrlImage(product.getUrlImage());
            }
        } else { //Si es una inserción
            product.setUrlImage(uploadFile.upload(multipartFile));
        }
        return iProductRepository.save(product);
    }

    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }

    public Product findById(Integer id) {
        return iProductRepository.findById(id);
    }

    public void deleteById(Integer id) {
        deleteImage(id);
        iProductRepository.deleteById(id);
    }

    public void deleteImage(Integer id) {
        Product productBaseDatos = findById(id);
        String urlImage= productBaseDatos.getUrlImage();
        if(!urlImage.isBlank()){
            String urlResource = "http://localhost:3000/images/";
            String nameFile = urlImage.substring(urlResource.length());
            if(!nameFile.equals("default.jpg")) {
                uploadFile.delete(nameFile);
            }
        }
    }
}
