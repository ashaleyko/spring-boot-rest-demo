package demo.springbootrest.common

import groovy.json.JsonBuilder
import io.restassured.http.ContentType
import io.restassured.response.Response

import static io.restassured.RestAssured.given

trait RequestHelper {

    static int appPort

    Response sendGet(String path) {
        given()
                .port(appPort)
                .when()
                .get(path)
    }

    Response sendPost(String path, Map json) {
        given()
                .port(appPort)
                .contentType(ContentType.JSON)
                .body(new JsonBuilder(json).toString())
                .when()
                .post(path)
    }
}
