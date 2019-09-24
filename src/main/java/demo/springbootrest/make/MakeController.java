package demo.springbootrest.make;

import com.google.common.base.Preconditions;
import demo.springbootrest.core.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/makes")
class MakeController {

    @Autowired
    private MakeRepository repository;

    @GetMapping
    Collection<Make> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/id/{id}")
    Optional<Make> findById(@PathVariable("id") Long id) {
        return RestPreconditions.checkFound(repository.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    Collection<Make> findByName(@PathVariable("name") String name) {
        return RestPreconditions.checkFound(repository.findByNameIgnoreCase(name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Make create(@RequestBody Make newMake) {
        Preconditions.checkNotNull(newMake);
        return repository.save(newMake);
    }

    @PutMapping(value = "{id}")
    Make update(@PathVariable("id") Long id, @RequestBody Make newMake) {
        Preconditions.checkNotNull(newMake);
        return repository.findById(id)
                .map(make -> {
                    make.setName(newMake.getName());
                    make.setOriginCountry(newMake.getOriginCountry());
                    make.setFoundedDate(newMake.getFoundedDate());
                    return repository.save(make);
                })
                .orElseGet(() -> {
                    newMake.setId(id);
                    return repository.save(newMake);
                });
    }

    @DeleteMapping(value = "{id}")
    void delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

}
