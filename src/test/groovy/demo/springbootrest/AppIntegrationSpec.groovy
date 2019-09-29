package demo.springbootrest

import demo.springbootrest.common.RequestHelper
import io.restassured.response.Response
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Specification
import spock.lang.Unroll

import static org.apache.http.HttpStatus.SC_CREATED
import static org.apache.http.HttpStatus.SC_OK

@Unroll
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class AppIntegrationSpec extends Specification implements RequestHelper {

    @LocalServerPort
    private int localPort

    def setup() {
        appPort = localPort
    }

    def "should return 200 status for /makes"() {
        when: "request to /makes is sent"
            Response response = sendGet("/makes")

        then: "status is 200 and 5 makes are returned"
            with(response) {
                statusCode == SC_OK
                path('id').size == 5
            }
    }

    def "should return 201 status when #make is created"() {
        when: "one make is added"
            Response response = sendPost("/makes", [name         : make,
                                                          originCountry: originCountry,
                                                          foundedDate  : foundedDate])

        then: "status is 201 and added make is returned"
            with(response) {
                statusCode == SC_CREATED
                path('name') == make
                path('originCountry') == originCountry
                path('foundedDate') == foundedDate + 'T00:00:00.000+0000'
            }

        where:
            make            | originCountry | foundedDate
            'Mercedes-Benz' | 'Germany'     | '1926-06-28'
            'Holden'        | 'Australia'   | '1856-12-31'
            'Alfa Romeo'    | 'Italy'       | '1910-06-24'
    }
}
