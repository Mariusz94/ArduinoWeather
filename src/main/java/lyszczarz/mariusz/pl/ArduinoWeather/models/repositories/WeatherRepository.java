package lyszczarz.mariusz.pl.ArduinoWeather.models.repositories;

import lyszczarz.mariusz.pl.ArduinoWeather.models.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherModel, Integer> {

    @Query(value = "SELECT * From weather ORDER BY id DESC LIMIT 288",
            nativeQuery = true)
    List<WeatherModel> getLastDay();

/*    @Query(value = "SELECT * From weather ORDER BY id DESC LIMIT 864",
            nativeQuery = true)
    List<WeatherModel> getLastThreeDay();

    @Query(value = "SELECT * From weather ORDER BY id DESC LIMIT 2016",
            nativeQuery = true)
    List<WeatherModel> getLastWeek();*/
}
