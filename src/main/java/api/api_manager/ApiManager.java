package api.api_manager;

import api.models.GooglePlacesModel;
import lombok.Getter;
import lombok.Setter;

public class ApiManager {
    @Getter
    @Setter
    GooglePlacesModel googlePlacesModel = new GooglePlacesModel();

}
