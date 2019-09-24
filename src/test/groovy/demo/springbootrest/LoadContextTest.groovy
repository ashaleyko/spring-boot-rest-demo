package demo.springbootrest

import demo.springbootrest.make.MakeController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

    @Autowired
    private MakeController makeController

    def "when context is loaded then all expected beans are created"() {
        expect: "MakeController is created"
        makeController
    }
}
