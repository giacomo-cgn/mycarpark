package giacomo.cignoni.testandroid.mycarpark;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class Car {
    @PrimaryKey(autoGenerate = true)
    private long carId;
    private String name;

    public Car(String name){
        this.name = name;
    }

    public long getCarId() {
        return carId;
    }

    public String getName() {
        return name;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public void setName(String name) {
        this.name = name;
    }


}
