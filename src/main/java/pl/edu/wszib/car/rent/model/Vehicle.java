package pl.edu.wszib.car.rent.model;

public class Vehicle {
    private int id;
    private String brand;
    private String model;
    private String plate;
    private boolean rent;

    public Vehicle(int id, String brand, String model, String plate, boolean rent) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.rent = rent;
    }

    public Vehicle() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public Vehicle clone() { return new Vehicle(this.id,this.brand,this.model, this.plate, this.rent); }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plate='" + plate + '\'' +
                ", rent=" + rent +
                '}';
    }
}
