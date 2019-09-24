package demo.springbootrest.make;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MakeRepository extends JpaRepository<Make, Long> {

    Collection<Make> findByNameIgnoreCase(String name);
}
