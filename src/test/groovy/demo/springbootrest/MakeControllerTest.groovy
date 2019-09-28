package demo.springbootrest


import demo.springbootrest.make.MakeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
class MakeControllerTest extends Specification {

    @Autowired
    private MakeRepository makeRepository = Mock()

    @Autowired
    private MockMvc mvc

    def "should return 200 status for /makes"() {
        expect: "status is 200"
        mvc.perform(MockMvcRequestBuilders.get("/makes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
    }

    def "should return 200 status when get by name request is sent"() {
        expect: "status is 200 when get by name request is sent"
            MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/makes/name/Honda"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn()
    }
}
