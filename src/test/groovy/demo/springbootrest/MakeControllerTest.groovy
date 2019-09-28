package demo.springbootrest

import demo.springbootrest.make.Make
import demo.springbootrest.make.MakeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
class MakeControllerTest extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private MakeRepository makeRepository = Mock()

    def "should return 200 status for /makes"() {
        expect: "status is 200"
            mvc.perform(MockMvcRequestBuilders.get("/makes"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
    }

    def "should return 200 status when get by name request is sent"() {
        given: "one make is added"
            makeRepository.save(new Make("Toyota", "Japan", Date.parse("yyyy-MM-dd", "1937-08-28") as Date))


        expect: "status is 200 when get by name request is sent"
            mvc.perform(MockMvcRequestBuilders.get("/makes/name/Toyota"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("\$..name").value("Toyota"))
    }
}
