package ra.practice_rest_api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.practice_rest_api.dto.request.ProductRequest;
import ra.practice_rest_api.dto.response.ProductResponse;
import ra.practice_rest_api.model.Categories;
import ra.practice_rest_api.model.Product;
import ra.practice_rest_api.repository.CategoriesRepository;

@Component
public class ProductMapper implements GenericMapper<Product, ProductRequest, ProductResponse> {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Product mapperToEntity(ProductRequest productRequest) {
        return Product.builder().id(productRequest.getId())
                .name(productRequest.getName())
                .title(productRequest.getTitle())
                .descriptions(productRequest.getDescriptions())
                .price(productRequest.getPrice())
                .catalog(categoriesRepository.findById(productRequest.getCatalog()).get())
                .status(productRequest.isStatus())
                .build();
    }

    @Override
    public ProductResponse mapperToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getTitle(),
                product.getDescriptions(),
                product.getCatalog().getId(),
                product.getCatalog().getName(),
                product.isStatus()
        );
    }
}
