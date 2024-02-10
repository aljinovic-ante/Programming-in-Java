package drugaVjezba;

import lombok.Getter;

import java.util.Random;

@Getter
public class Sensor {
    private double value;
    private int factor;
    private String unit;
    private String subject;

    Sensor(double range1,double range2,int factor,String unit,String subject){
        Random number=new Random();
        this.value=number.nextInt((int)(range2-range1))+(int)range1;
        this.factor=factor;
        this.unit=unit;
        this.subject=subject;
    }

    public String getSensorData(){
        return "\nDevice: "+subject+" \nValue: "+value+" \nFactor: "+factor+" \nUnit: "+unit+"\n\n";
    }

}
