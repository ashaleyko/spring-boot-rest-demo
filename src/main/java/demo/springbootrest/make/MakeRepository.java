package demo.springbootrest.make;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MakeRepository extends JpaRepository<Make, Long> {

    Collection<Make> findByNameIgnoreCase(String name);
}
