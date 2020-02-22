package pl.wk.thymeleaf.service;

import org.springframework.stereotype.Service;
import pl.wk.thymeleaf.model.Car;
import pl.wk.thymeleaf.model.SearchCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CarService {

	private List<Car> cars;

	public CarService() {
		cars = new ArrayList<>();
		addCar( new Car(1, "Polonez", "caro", "yellow"));
		addCar( new Car(2, "Mazda", "626", "blue"));
		addCar( new Car(3, "Peugeot", "306", "red"));
		addCar( new Car(4, "Fiat", "punto", "green"));
	}

	private static boolean nullorempty( String s ) {
		return s == null || s.isEmpty();
	}

	private static boolean containslowercase( String text, String sub ) {
		if( !nullorempty(sub) ) {
			if( !nullorempty(text) ) {
				if( !text.toLowerCase().contains( sub.toLowerCase() )) {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return true;
	}

	private static boolean getCarsFilter( Car e, SearchCar searchCar ) {
		if( !nullorempty(searchCar.getCarId() )) {
			if( e.getCarId() != Long.parseLong(searchCar.getCarId()) ) {
				return false;
			}
		}
		if( !containslowercase(e.getMark(), searchCar.getMark()) ) {
			return false;
		}
		if( !containslowercase(e.getModel(), searchCar.getModel()) ) {
			return false;
		}
		if( !containslowercase(e.getColor(), searchCar.getColor()) ) {
			return false;
		}
		return true;
	}

	public List<Car> getCars( SearchCar searchCar ) {
		if( nullorempty(searchCar.getCarId()) &&
			nullorempty(searchCar.getMark()) &&
			nullorempty(searchCar.getModel()) &&
			nullorempty(searchCar.getColor()) )
		{
			return cars;
		}
		else {
			return cars.stream().filter( e -> getCarsFilter(e, searchCar) ).collect(Collectors.toList());
		}
	}

	public Optional<Car> findCarById(long id) {
		return cars != null ? cars.stream().filter(car -> car.getCarId() == id).findFirst() : Optional.empty();
	}

	public int getCarIndex( long id  ) {
		List<Car> entries = cars;
		int index = IntStream.range(0, entries.size())
			.filter(i -> id == entries.get(i).getCarId())
			.findFirst().orElse(-1);
		return index;
	}

	public Car getCarById(long id) {
		Optional<Car> r = cars
			.stream()
			.filter(car -> car.getCarId() == id)
			.findFirst();
		return r.isPresent() ? r.get() : null;
	}

	public long getMaxId() {
		return cars.size() > 0 ? cars.get(cars.size()-1).getCarId() : 0;
	}

	public boolean addCar( Car car ) {
		if( car.getCarId() == 0 ) {
			car.setCarId( getMaxId() + 1);
			cars.add( car );
			return true;
		}
		else {
			if( car.getCarId() < 0 ) {
				return false;
			}
//			int index = getCarIndex( car.getId() );
			int i = 0;
			for( i = 0; i < cars.size(); ++i ) {
				if( cars.get(i).getCarId() > car.getCarId() ) {
					break;
				}
				if( cars.get(i).getCarId() == car.getCarId() ) {
					return false;
				}
			}
			cars.add( i, car );
			return true;
		}
	}

	public boolean modifyCar(Optional<Car> car, Car newcar) {
		if (!newcar.getMark().isEmpty()) {
			car.get().setMark(newcar.getMark());
		}
		if (!newcar.getModel().isEmpty()) {
			car.get().setModel(newcar.getModel());
		}
		if (!newcar.getColor().isEmpty()) {
			car.get().setColor(newcar.getColor());
		}
		return true;
	}

	public Boolean removeCar(long id) {
		Optional<Car> carOptional = findCarById(id);
		return carOptional.map(car -> cars.remove(car)).orElse(false);
	}
}
