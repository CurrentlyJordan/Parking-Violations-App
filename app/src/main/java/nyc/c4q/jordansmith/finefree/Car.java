package nyc.c4q.jordansmith.finefree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordansmith on 2/18/17.
 */

public class Car {

     private String name;
    private String licensePlate;
    private static List<Car> Carlist = new ArrayList<>();

    Car(String name, String licensePlate){
        this.licensePlate = licensePlate;
        this.name = name;
    }

    public static List<Car> getCarlist() {
        return Carlist;
    }

    public static void setCarlist(List<Car> carlist) {
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


}
