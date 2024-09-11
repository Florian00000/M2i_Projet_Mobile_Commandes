package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.dto.product.ProductDtoGet;
import org.example.dto.product.ProductDtoPost;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductService {


    @Inject
    ProductRepository productRepository;

    @Inject
    CategoryRepository categoryRepository;

    public List<ProductDtoGet> getAllProducts() {
        List<Product> products = productRepository.listAll();
        return products.stream().map(ProductDtoGet::new).toList();
    }

    public ProductDtoGet getProductById(long id) {
        Optional<Product> product = productRepository.findByIdOptional(id);
        return product.map(ProductDtoGet::new).orElse(null);

    }

    @Transactional
    public ProductDtoGet createProduct(ProductDtoPost productDtoPost) {
        Product product = convertProductDtoToProduct(productDtoPost);
        productRepository.persist(product);
        return new ProductDtoGet(product);
    }

    @Transactional
    public ProductDtoGet updateProduct(long id, ProductDtoPost productDtoPost) {
        Optional<Product> product = productRepository.findByIdOptional(id);
        if (product.isPresent()) {
            product.get().setName(productDtoPost.getName());
            product.get().setPrice(productDtoPost.getPrice());
            product.get().setDescription(productDtoPost.getDescription());
            product.get().setStock(productDtoPost.getStock());

            List<Category> categories = productDtoPost.getListProductIds().stream().map(idCategory -> categoryRepository.findById(idCategory)).toList();
            product.get().setCategories(categories);
            productRepository.persist(product.get());
            return new ProductDtoGet(product.get());
        }else{
            return null;
        }
    }

    @Transactional
    public boolean deleteProduct(long id) {
        Optional<Product> product = productRepository.findByIdOptional(id);
        if (product.isPresent()) {

            for (Category category : product.get().getCategories()) {
                category.getProducts().remove(product.get());
            }
            return productRepository.deleteById(id);
        }else return false;
    }

    @Transactional
    public ProductDtoGet upStock(long id, int stock) {
        Optional<Product> product = productRepository.findByIdOptional(id);
        if (product.isPresent()) {
            product.get().setStock(product.get().getStock() + stock);
            productRepository.persist(product.get());
            return new ProductDtoGet(product.get());
        }else return null;
    }

    @Transactional
    public ProductDtoGet downStock(long id, int stock) {
        Optional<Product> product = productRepository.findByIdOptional(id);
        if (product.isPresent()) {
            if (product.get().getStock() - stock > 0) {
                product.get().setStock(product.get().getStock() - stock);
                productRepository.persist(product.get());
                return new ProductDtoGet(product.get());
            } else throw new IllegalArgumentException("Stock out of stock");

        }else return null;
    }

    private Product convertProductDtoToProduct(ProductDtoPost productDtoPost) {
        List<Category> categories = productDtoPost.getListProductIds().stream().map(id -> categoryRepository.findById(id)).toList();
        return Product.builder()
                .name(productDtoPost.getName())
                .stock(productDtoPost.getStock())
                .description(productDtoPost.getDescription())
                .price(productDtoPost.getPrice())
                .categories(categories)
                .build();

    }
}
