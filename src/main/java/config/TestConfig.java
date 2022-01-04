package config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static constants.Contstants.RunVeriable.path;
import static constants.Contstants.RunVeriable.server;
import static constants.Contstants.Servers.REQUESTBIN_URL;
import static constants.Contstants.Servers.SWAPI_URL;

public class TestConfig {
    protected RequestSpecification requestSpecificationForSwapiTests = new RequestSpecBuilder()
            .setBaseUri(SWAPI_URL)
            .build();

    protected RequestSpecification requestSpecificationXML = new RequestSpecBuilder()
            .setBaseUri(REQUESTBIN_URL)
            .addHeader("Content-Type", "application/xml")
            .addCookie("testCookieXML")
            .build();

    protected ResponseSpecification responseSpecificationForGet = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    protected ResponseSpecification responseSpecificationForPost = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();


    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = server;
        RestAssured.basePath = path;

        RequestSpecification requestSpecificationJSON = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addCookie("testCookieJSON")
                .build();
        RestAssured.requestSpecification = requestSpecificationJSON;


    }
}
