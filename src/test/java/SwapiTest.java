import config.TestConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static constants.Contstants.Actions.SWAPI_GET_PEOPLE;
import static constants.Contstants.Path.SWAPI_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class SwapiTest extends TestConfig {

    @Test
    public void myFirstTest() {
//    given().log().all().//залогировали весь запрос
//        given().log().ifValidationFails().//логирует запрос если тест упал
        given().spec(requestSpecificationForSwapiTests).log().uri().//залогировали URI
                when().get(SWAPI_PATH+SWAPI_GET_PEOPLE + "1").
                then().log().all().statusCode(200);//залогировали ответ
//        then().log().body().statusCode(200);
    }

    @Test
    public void getSomeFieldInResponseWithIndexAssertion(){
        given().spec(requestSpecificationForSwapiTests).log().uri().
                when().get(SWAPI_PATH+SWAPI_GET_PEOPLE).
                then()
                .body("count", equalTo(82))//проверка полей библиотекой hamcrest
                .body("results.name[0]", equalTo("Luke Skywalker"))
                .log().body();
    }

    @Test
    public void getAllDataFromRequest(){
        Response response =
                given().spec(requestSpecificationForSwapiTests).log().uri().
                        when().get(SWAPI_PATH).
                        then().extract().response();//извлекаем тела ответа
        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void getCookieFromResponse(){
        Response response =
                given().spec(requestSpecificationForSwapiTests).log().uri().
                        when().get(SWAPI_PATH).
                        then().extract().response();
        Map<String, String> allCookie = response.getCookies();
        System.out.println("allCookie--> " + allCookie);//вывести все куки

        String someCookie = response.getCookie("...");
        System.out.println(someCookie);//вывести указанную куку
    }

    @Test
    public void getHeaderFromResponse(){
        Response response =
                given().spec(requestSpecificationForSwapiTests).log().uri().
                        when().get(SWAPI_PATH).
                        then().extract().response();
        Headers headers = response.getHeaders();
        System.out.println("headers-->" + headers);//получить все заголовки

        String contentType = response.getContentType();
        System.out.println(contentType);//получить конкретный заголовок
    }

    @Test//используем груви и джипасс
    public void getMapOfElementsWithSomeKey(){
        Response response =
                given().spec(requestSpecificationForSwapiTests).log().uri().
                        when().get(SWAPI_PATH+SWAPI_GET_PEOPLE);
//        System.out.println("response-> " + response.asString());
        Map <String, ?> someObject = response
                .path("results.find{it.name = 'Luke Skywalker'}");//достали из массива results объект в котором есть поле с таким значением
        System.out.println(someObject);
    }

    @Test
    public void getSingleElementWithSomeKey(){
        Response response =
                given().spec(requestSpecificationForSwapiTests).log().uri().
                        when().get(SWAPI_PATH + SWAPI_GET_PEOPLE);
        String url = response.path("results.find{it.name = 'Luke Skywalker'}.url");//значения параметра в объекте, где такое имя
        System.out.println("url->" + url);
    }

    @Test
    public void getAllElementsWithSomeKey(){
        Response response =
                given().spec(requestSpecificationForSwapiTests).log().uri().
                        when().get(SWAPI_PATH + SWAPI_GET_PEOPLE);
        List films = response.path("results.findAll{it.films}.name");//выбираем всех актеров с фильмами
        System.out.println("films-> " + films);
    }

}
