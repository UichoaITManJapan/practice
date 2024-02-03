package ra.practice_rest_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.practice_rest_api.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByName(String searchBy);

    @Query("select p from Product p where p.name like %?1%")
    Page<Product> findBySearch(String searchBy,Pageable pageable);

}
