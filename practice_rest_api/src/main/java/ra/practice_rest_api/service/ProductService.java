package ra.practice_rest_api.service;

import ra.practice_rest_api.dto.request.ProductRequest;
import ra.practice_rest_api.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll(int page,int size,String direction, String orderBy,String searchBy);
    ProductResponse findById(String id);
    ProductResponse save(ProductRequest productRequest);
    ProductResponse update(ProductRequest productRequest, String id);
    boolean delete(String id);
    List<ProductResponse> searchByName(String searchBy);
}
