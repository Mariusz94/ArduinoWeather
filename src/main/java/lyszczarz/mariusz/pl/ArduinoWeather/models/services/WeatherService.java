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

    public int getMinTemp(List<WeatherModel> weatherModels) {
        int tempMin = 1000;
        for (WeatherModel weatherModel : weatherModels) {
            if(weatherModel.getTempIn() < tempMin){
                tempMin = (int)weatherModel.getTempIn();
            }
            if(weatherModel.getTempOut() < tempMin){
                tempMin = (int)weatherModel.getTempOut();
            }
        }
        return tempMin - 1;
    }

    public int getMaxTemp(List<WeatherModel> weatherModels) {
        int tempMax = -1000;
        for (WeatherModel weatherModel : weatherModels) {
            if(weatherModel.getTempOut() > tempMax){
                tempMax = (int)weatherModel.getTempIn();
            }
            if(weatherModel.getTempOut() > tempMax){
                tempMax = (int)weatherModel.getTempOut();
            }
        }
        return tempMax + 1;
    }

}
