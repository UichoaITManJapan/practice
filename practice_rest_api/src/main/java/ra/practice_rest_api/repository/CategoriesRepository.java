package ra.practice_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.practice_rest_api.dto.response.CategoriesResponseActive;
import ra.practice_rest_api.model.Categories;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    List<Categories> findByStatus(boolean status);
}
