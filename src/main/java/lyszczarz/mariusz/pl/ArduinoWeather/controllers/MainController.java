package lyszczarz.mariusz.pl.ArduinoWeather.controllers;

import lyszczarz.mariusz.pl.ArduinoWeather.models.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final
    WeatherRepository weatherRepository;

    @Autowired
    public MainController(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("listWeather", weatherRepository.findAll());
        return "mainPage";
    }
}
