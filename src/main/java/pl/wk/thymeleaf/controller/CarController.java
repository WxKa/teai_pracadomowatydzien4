package pl.wk.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wk.thymeleaf.model.Car;
import pl.wk.thymeleaf.model.SearchCar;
import pl.wk.thymeleaf.service.CarService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
public class CarController {

	private CarService carService;

	@Autowired
	public CarController( CarService carService) {
		this.carService = carService;
	}

	private SearchCar searchCar = new SearchCar();

	@GetMapping("/cars")
	public String getCar( Model model) {
		model.addAttribute("cars", carService.getCars(searchCar));
		model.addAttribute("newCar", new Car());
		model.addAttribute("searchCar", searchCar);
		return "cars";
	}

	@PostMapping("/search-car")
	public String searchCar(@ModelAttribute SearchCar car) {
		searchCar = car;
		return "redirect:/cars";
	}

	@PostMapping("/add-car")
	public String addCar( @ModelAttribute Car car) {
		System.out.println(car);
		carService.addCar(car);
		return "redirect:/cars";
	}

	@PostMapping("/modify-car")
	public String modCar(@ModelAttribute Car newcar) {
		Optional<Car> find = carService.findCarById(newcar.getCarId());
		if(find.isPresent()){
			carService.modifyCar(find, newcar);
		} else {
			Optional.empty();
		}
		return "redirect:/cars";
	}

	@PostMapping("/delete-car")
	public String removeCar(@ModelAttribute Car car) {
		carService.removeCar(car.getCarId());
		return "redirect:/cars";
	}

}
