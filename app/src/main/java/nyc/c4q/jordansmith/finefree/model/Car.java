package nyc.c4q.jordansmith.finefree.model;

import java.io.Serializable;

/**
 * Created by jordansmith on 2/18/17.
 */

public class Car implements Serializable{
    Long _id;
    private String name;
    private String licensePlate;

    public Car() {

    }

    public Car(String name, String licensePlate) {
        this.licensePlate = licensePlate;
        this.name = name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getName() {
        return name;
    }

}
