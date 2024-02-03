package ra.practice_rest_api.service;

import ra.practice_rest_api.dto.request.CategoriesRequest;
import ra.practice_rest_api.dto.response.CategoriesResponse;
import ra.practice_rest_api.dto.response.CategoriesResponseActive;

import java.util.List;

public interface CategoriesService {
    List<CategoriesResponse> find(int page,int size,String direction,String orderBy);
    CategoriesResponse findById(long id);
    CategoriesResponse save(CategoriesRequest categoriesRequest);
    CategoriesResponse update(CategoriesRequest categoriesRequest, long id);
    CategoriesResponse updateStatus(long id);
    List<CategoriesResponseActive> activeCategories();
}
