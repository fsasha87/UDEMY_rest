import config.TestConfig;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static constants.Contstants.Actions.*;
import static io.restassured.RestAssured.given;

public class JsonPlaceHolderTest extends TestConfig {

    @Test//get-request
    public void GET(){
        given().queryParam("PostID", 1).log().uri().//отправим запрос с использованием Query-параметров (https://...?PostID=1)
                when().get(JSONPLACEHOLDER_GET).
                then().log().body().statusCode(200);
    }
    @Test
    public void Put(){
        //проверяем json здесь: http://json.parser.online.fr/
        String putBodyJson = "{   \n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"foo\",\n" +
                "    \"body\": \"bar\",\n" +
                "    \"userId\": 1\n" +
                "}";
        given().body(putBodyJson).log().uri().
                when().put(JSONPLACEHOLDER_PUT).
                then().log().body().statusCode(200);
    }

    @Test
    public void Delete(){
        given().log().uri().
                when().delete(JSONPLACEHOLDER_DELETE).
                then().log().body().statusCode(200);
    }

    @Test
    public void postWithJson() {//передаем запрос на сервер в json
        String postJsonBody = "{\n" +
                "\"title\":\"foo\",\n" +
                "\"body\":\"bar\",\n" +
                "\"userId\":1\n" +
                "}";
        given().body(postJsonBody).log().uri().
                when().post(JSONPLACEHOLDER_POST).
                then().log().body().statusCode(201);//создается объект
        //или проверка вынесена в общую респонс-спецификацию
//                then().spec(responseSpecificationForPost).log().body();
    }

    @Test
    public void postWithXML() {//передаем запрос на сервер в xml
        String postXMLBody = "<Company>\n" +//случайный xml на https://www.tutorialspoint.com/online_xml_editor.htm
                "  <Employee>\n" +
                "      <FirstName>Tanmay</FirstName>\n" +
                "      <LastName>Patil</LastName>\n" +
                "      <ContactNo>1234567890</ContactNo>\n" +
                "      <Email>tanmaypatil@xyz.com</Email>\n" +
                "      <Address>\n" +
                "           <City>Bangalore</City>\n" +
                "           <State>Karnataka</State>\n" +
                "           <Zip>560212</Zip>\n" +
                "      </Address>\n" +
                "  </Employee>\n" +
                "</Company>\n";
        given().spec(requestSpecificationXML).body(postXMLBody).log().uri().//отдельная спецификация
                when().post("").
                then().log().body().statusCode(200);//200 а не 201, потому что сервис транслирующий, объект не создается
    }

}
