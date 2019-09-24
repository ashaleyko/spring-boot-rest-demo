package demo.springbootrest

import demo.springbootrest.make.MakeRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@AutoConfigureMockMvc
@WebMvcTest
class MakeControllerTest extends Specification {

    @SpringBean
    private MakeRepository makeRepository = Mock()

    @Autowired
    private MockMvc mvc

    def "when get is performed then response has status 200"() {
        expect: "status is 200"
        mvc.perform(MockMvcRequestBuilders.get("/makes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
    }
}
