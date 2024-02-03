package ra.practice_rest_api.mapper;

import org.springframework.stereotype.Component;
import ra.practice_rest_api.dto.request.CategoriesRequestActive;
import ra.practice_rest_api.dto.response.CategoriesResponseActive;
import ra.practice_rest_api.model.Categories;

@Component
public class CategoriesMapperActive implements GenericMapper<Categories, CategoriesRequestActive, CategoriesResponseActive> {
    @Override
    public Categories mapperToEntity(CategoriesRequestActive categoriesListStatus) {
        return Categories.builder().id(categoriesListStatus.getId())
                .name(categoriesListStatus.getName()).build();
    }

    @Override
    public CategoriesResponseActive mapperToResponse(Categories categories) {
        return new CategoriesResponseActive(
                categories.getId(),
                categories.getName()
        );
    }
}
