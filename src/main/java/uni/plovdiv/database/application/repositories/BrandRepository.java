package uni.plovdiv.database.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uni.plovdiv.database.application.models.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query(value = "SELECT * FROM BRAND " +
            "WHERE title LIKE %:title% ",
            nativeQuery = true)
    List<Brand> getBrandByTitle(@Param("title") String title);
}