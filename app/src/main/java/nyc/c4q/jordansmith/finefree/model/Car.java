package nyc.c4q.jordansmith.finefree.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jordansmith on 2/18/17.
 */

public class Car implements Serializable{
    Long _id;
    private String name;
    private String licensePlate;
    private static Set<Car> Carlist = new HashSet<>();

    public Car() {

    }

    public Car(String name, String licensePlate) {
        this.licensePlate = licensePlate;
        this.name = name;
    }

    public static Set<Car> getCarlist() {
        return Carlist;
    }

    public static void setCarlist(Set<Car> carlist) {
        Carlist = carlist;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
