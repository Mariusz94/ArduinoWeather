package lyszczarz.mariusz.pl.ArduinoWeather.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "weather")
@Getter
@NoArgsConstructor
public class WeatherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "temp_in")
    private double tempIn;
    @Column(name = "temp_out")
    private double tempOut;
    @Column (name = "time_date")
    private Timestamp date;
}
