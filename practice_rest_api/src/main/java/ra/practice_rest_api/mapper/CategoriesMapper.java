package ra.practice_rest_api.mapper;

import org.springframework.stereotype.Component;
import ra.practice_rest_api.dto.request.CategoriesRequest;
import ra.practice_rest_api.dto.response.CategoriesResponse;
import ra.practice_rest_api.model.Categories;

@Component
public class CategoriesMapper implements GenericMapper<Categories, CategoriesRequest, CategoriesResponse>{

    @Override
    public Categories mapperToEntity(CategoriesRequest categoriesRequest) {
        return Categories.builder().id(categoriesRequest.getId())
                .name(categoriesRequest.getName())
                .priority(categoriesRequest.getPriority())
                .descriptions(categoriesRequest.getDescriptions())
                .status(categoriesRequest.isStatus()).build();
    }

    @Override
    public CategoriesResponse mapperToResponse(Categories categories) {
        return new CategoriesResponse(
                categories.getId(),
                categories.getName(),
                categories.getPriority(),
                categories.getDescriptions(),
                categories.isStatus()
        );
    }
}
