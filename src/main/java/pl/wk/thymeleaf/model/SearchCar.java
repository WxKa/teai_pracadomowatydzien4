package pl.wk.thymeleaf.model;

public class SearchCar {

	private String carId;
	private String mark;
	private String model;
	private String color;

	public String getCarId() {
		return carId;
	}

	public void setCarId( String carId ) {
		this.carId = carId;
	}

	public String getMark() {
		return mark;
	}

	public void setMark( String mark ) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel( String model ) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor( String color ) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car{" +
			"carId=" + carId +
			", mark='" + mark + '\'' +
			", model='" + model + '\'' +
			", color='" + color + '\'' +
			'}';
	}
}
