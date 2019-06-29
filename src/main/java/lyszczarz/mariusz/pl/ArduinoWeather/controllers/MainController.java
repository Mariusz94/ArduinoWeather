package lyszczarz.mariusz.pl.ArduinoWeather.controllers;

import lyszczarz.mariusz.pl.ArduinoWeather.models.WeatherModel;
import lyszczarz.mariusz.pl.ArduinoWeather.models.repositories.WeatherRepository;
import lyszczarz.mariusz.pl.ArduinoWeather.models.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
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

    @GetMapping("/prototype")
    public String mainPrototypePage(Model model){
        List<WeatherModel> weatherAtLastDay = weatherRepository.getLastDay();
        model.addAttribute("weather", weatherAtLastDay.get(0));
        model.addAttribute("arrayTempIn", weatherService.getArrayTempIn(weatherAtLastDay));
        model.addAttribute("arrayTempOut", weatherService.getArrayTempOut(weatherAtLastDay));
        model.addAttribute("minTemp", weatherService.getMinTemp(weatherAtLastDay));
        model.addAttribute("maxTemp", weatherService.getMaxTemp(weatherAtLastDay));

        model.addAttribute("minTempThisDayInside", weatherService.getMinTempThisDayInside(weatherAtLastDay));
        model.addAttribute("maxTempThisDayInside", weatherService.getMaxTempThisDayInside(weatherAtLastDay));
        model.addAttribute("minTempThisDayOutside", weatherService.getMinTempThisDayOutside(weatherAtLastDay));
        model.addAttribute("maxTempThisDayOutside", weatherService.getMaxTempThisDayOutside(weatherAtLastDay));
        return "prototype_dashboard";
    }

    @GetMapping("/")
    public String mainPage(Model model){
        List<WeatherModel> weatherAtLastDay = weatherRepository.getLastDay();
        model.addAttribute("weather", weatherAtLastDay.get(0));
        model.addAttribute("arrayTempIn", weatherService.getArrayTempIn(weatherAtLastDay));
        model.addAttribute("arrayTempOut", weatherService.getArrayTempOut(weatherAtLastDay));
        model.addAttribute("minTemp", weatherService.getMinTemp(weatherAtLastDay));
        model.addAttribute("maxTemp", weatherService.getMaxTemp(weatherAtLastDay));
        return "dashboard";
    }

    @GetMapping("/chart/{number}")
    public String lastDayPage(@PathVariable("number") int numberOfDays,
                              Model model){
        List<WeatherModel> weatherAtLastDay;
        switch (numberOfDays){
            case 1:
                weatherAtLastDay = weatherRepository.getLastDay();
                model.addAttribute("topic", "Pogoda z jednego dnia");
                break;
            case 3:
                weatherAtLastDay = weatherRepository.getLastDay();
                model.addAttribute("topic", "Pogoda z trzech dni");
                break;
            case 7:
                weatherAtLastDay = weatherRepository.getLastDay();
                model.addAttribute("topic", "Pogoda z tygodnia");
                break;
            case 30:
                weatherAtLastDay = weatherRepository.getLastDay();
                model.addAttribute("topic", "Pogoda z miesiąca");
                break;
        }
        weatherAtLastDay = weatherRepository.getLastDay();
        model.addAttribute("weather", weatherAtLastDay.get(0));
        model.addAttribute("arrayTempIn", weatherService.getArrayTempIn(weatherAtLastDay));
        model.addAttribute("arrayTempOut", weatherService.getArrayTempOut(weatherAtLastDay));
        model.addAttribute("minTemp", weatherService.getMinTemp(weatherAtLastDay));
        model.addAttribute("maxTemp", weatherService.getMaxTemp(weatherAtLastDay));
        return "chart";
    }

    @GetMapping("/home")
    public String homePage(Model model){
        List<WeatherModel> weatherAtLastDay = weatherRepository.getLastDay();
        model.addAttribute("weather", weatherAtLastDay.get(0));
        model.addAttribute("arrayTempIn", weatherService.getArrayTempIn(weatherAtLastDay));
        model.addAttribute("arrayTempOut", weatherService.getArrayTempOut(weatherAtLastDay));
        model.addAttribute("minTemp", weatherService.getMinTemp(weatherAtLastDay));
        model.addAttribute("maxTemp", weatherService.getMaxTemp(weatherAtLastDay));
        return "home";
    }
}
