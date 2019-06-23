package lyszczarz.mariusz.pl.ArduinoWeather.models.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class PointModel {
    public PointModel(long x, double y) {
        this.x = x;
        this.y = y;
    }

    private long x;
    private double y;
}
