package uni.plovdiv.database.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uni.plovdiv.database.application.models.CategoryModel;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

    @Query(value = "SELECT * FROM CATEGORY " +
            "WHERE title LIKE %:title% ",
            nativeQuery = true)
    List<CategoryModel> getCategoryByTitle(@Param("title") String title);
}
