package ra.practice_rest_api.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.practice_rest_api.dto.request.ProductRequest;
import ra.practice_rest_api.dto.response.ProductResponse;
import ra.practice_rest_api.mapper.ProductMapper;
import ra.practice_rest_api.model.Product;
import ra.practice_rest_api.repository.CategoriesRepository;
import ra.practice_rest_api.repository.ProductRepository;
import ra.practice_rest_api.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<ProductResponse> findAll(int page, int size, String direction, String orderBy,String searchBy) {
        Pageable pageable;
        if (direction.equals("ASC")) {
            pageable = PageRequest.of(page,size, Sort.by(orderBy).ascending());
        } else {
            pageable = PageRequest.of(page,size,Sort.by(orderBy).descending());
        }
        Page<Product> productPage;
        if (searchBy != null && !searchBy.isEmpty()) {
            productPage = productRepository.findBySearch(searchBy,pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }
        List<Product> listProduct = productPage.getContent();
        return listProduct.stream().map(product -> productMapper.mapperToResponse(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(String id) {
        Optional<Product> otpProduct = productRepository.findById(id);
        if (otpProduct.isPresent()) {
            return productMapper.mapperToResponse(otpProduct.get());
        }
        return null;
    }

    @Override
    public ProductResponse save(ProductRequest productRequest) {
        Product product = productRepository.save(productMapper.mapperToEntity(productRequest));
        return productMapper.mapperToResponse(product);
    }

    @Override
    public ProductResponse update(ProductRequest productRequest, String id) {
        boolean checkExist = productRepository.existsById(id);
        if (checkExist) {
            Product product = productRepository.save(productMapper.mapperToEntity(productRequest));
            return productMapper.mapperToResponse(product);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        boolean checkExist = productRepository.existsById(id);
        if (checkExist) {
            productRepository.deleteById(id);
        }
        return checkExist;
    }

    @Override
    public List<ProductResponse> searchByName(String searchBy) {
        return productRepository.findByName(searchBy).stream().
                map(product -> productMapper.mapperToResponse(product)).collect(Collectors.toList());
    }
}
