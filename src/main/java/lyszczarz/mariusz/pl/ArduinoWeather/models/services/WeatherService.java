package lyszczarz.mariusz.pl.ArduinoWeather.models.services;

import lombok.Data;
import lyszczarz.mariusz.pl.ArduinoWeather.models.WeatherModel;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class WeatherService {

    public PointModel[] getArrayTempIn (List<WeatherModel> weatherModels){
        PointModel[] weatherTempInArray = new PointModel[weatherModels.size()];
        for (int i = 0; i < weatherModels.size(); i++) {
            weatherTempInArray[i]= new PointModel(weatherModels.get(i).getDate().getTime(),weatherModels.get(i).getTempIn());
        }
        return weatherTempInArray;
    }

    public PointModel[] getArrayTempOut (List<WeatherModel> weatherModels){
        PointModel[] weatherTempInArray = new PointModel[weatherModels.size()];
        for (int i = 0; i < weatherModels.size(); i++) {
            weatherTempInArray[i]= new PointModel(weatherModels.get(i).getDate().getTime(),weatherModels.get(i).getTempOut());
        }
        return weatherTempInArray;
    }

}
