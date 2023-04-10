package uni.plovdiv.database.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uni.plovdiv.database.application.models.ShoeModel;

@Repository
public interface ShoeRepository extends JpaRepository<ShoeModel, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SHOE_BRAND (shoe_id, brand_id)" +
            "VALUES (:shoe_id, :brand_id)",
            nativeQuery = true)
    void insertShoeBrand(@Param("shoe_id") Long shoeId, @Param("brand_id") Long brandId);
}