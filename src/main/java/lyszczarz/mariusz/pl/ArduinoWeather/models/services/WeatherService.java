package lyszczarz.mariusz.pl.ArduinoWeather.models.services;

import lombok.Data;
import lyszczarz.mariusz.pl.ArduinoWeather.models.WeatherModel;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public double getMinTemp(List<WeatherModel> weatherModels) {
        double tempMin = 1000;
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

    public double getMaxTemp(List<WeatherModel> weatherModels) {
        double tempMax = -1000;
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

    public WeatherModel getMinTempThisDayInside(List<WeatherModel> weatherModels) {
        WeatherModel minTempInside = weatherModels.get(0);
        for (WeatherModel weatherModel : weatherModels) {
            if(weatherModel.getDate().after(Timestamp.valueOf(LocalDate.now().atStartOfDay())) && weatherModel.getTempIn() < minTempInside.getTempIn() ){
                minTempInside = weatherModel;
            }
        }
        return minTempInside;
    }

    public WeatherModel getMaxTempThisDayInside(List<WeatherModel> weatherModels) {
        WeatherModel maxTempInside = weatherModels.get(0);
        for (WeatherModel weatherModel : weatherModels) {
            if(weatherModel.getDate().after(Timestamp.valueOf(LocalDate.now().atStartOfDay())) && weatherModel.getTempIn() > maxTempInside.getTempIn() ){
                maxTempInside = weatherModel;
            }
        }
        return maxTempInside;
    }

    public WeatherModel getMinTempThisDayOutside(List<WeatherModel> weatherModels) {
        WeatherModel minWeatherOutside = weatherModels.get(0);
        for (WeatherModel weatherModel : weatherModels) {
            if(weatherModel.getDate().after(Timestamp.valueOf(LocalDate.now().atStartOfDay())) && weatherModel.getTempOut() < minWeatherOutside.getTempOut() ){
                minWeatherOutside = weatherModel;
            }
        }
        return minWeatherOutside;
    }

    public WeatherModel getMaxTempThisDayOutside(List<WeatherModel> weatherModels) {
        WeatherModel maxWeatherOutside = weatherModels.get(0);
        for (WeatherModel weatherModel : weatherModels) {
            if(weatherModel.getDate().after(Timestamp.valueOf(LocalDate.now().atStartOfDay())) && weatherModel.getTempOut() > maxWeatherOutside.getTempOut() ){
                maxWeatherOutside = weatherModel;
            }
        }
        return maxWeatherOutside;
    }
}
