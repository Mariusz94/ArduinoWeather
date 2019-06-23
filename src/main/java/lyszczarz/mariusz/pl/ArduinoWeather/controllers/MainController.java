package lyszczarz.mariusz.pl.ArduinoWeather.controllers;

import lyszczarz.mariusz.pl.ArduinoWeather.models.WeatherModel;
import lyszczarz.mariusz.pl.ArduinoWeather.models.repositories.WeatherRepository;
import lyszczarz.mariusz.pl.ArduinoWeather.models.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final
    WeatherRepository weatherRepository;
    private final
    WeatherService weatherService;

    @Autowired
    public MainController(WeatherRepository weatherRepository, WeatherService weatherService) {
        this.weatherRepository = weatherRepository;
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        List<WeatherModel> weatherAtLastDay = weatherRepository.getLastDay();
        model.addAttribute("weather", weatherAtLastDay.get(0));
        model.addAttribute("arrayTempIn", weatherService.getArrayTempIn(weatherAtLastDay));
        model.addAttribute("arrayTempOut", weatherService.getArrayTempOut(weatherAtLastDay));
        return "mainPage";
    }
}
