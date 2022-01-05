package tests;

import api.models.GooglePlacesModel;
import io.restassured.http.Method;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static constants.Constants.API_TOKEN;

public class SearchTestPositive extends BaseTest{
    @Test (description = "search some object", dataProvider = "searchText")
    public void searchPlaceWithText(String key, String input, String inputType){
        GooglePlacesModel.RequestModel requestModel = GooglePlacesModel.RequestModel.builder()
                .key(key)
                .input(input)
                .inputType(inputType)
                .build();
        apiManager.getGooglePlacesModel().search(requestModel, Method.GET, 200);
    }

    @DataProvider
    public Object[][] searchText(){
        return new Object[][]{{API_TOKEN, "new york","textquery"}};
    }
}
