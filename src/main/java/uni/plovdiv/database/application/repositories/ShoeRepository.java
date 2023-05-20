package uni.plovdiv.database.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.plovdiv.database.application.models.Shoe;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {

}