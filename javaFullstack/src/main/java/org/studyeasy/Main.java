package org.studyeasy;


import java.io.*;

public class Main {

    public static void main(String[] args) {

     Vehicle bike = new Vehicle("Bike",54321);
     Vehicle car = new Vehicle("Car",67890);


        try(FileOutputStream fos = new FileOutputStream("studyeasy//vehicle.dat")){
            try (ObjectOutputStream obj = new ObjectOutputStream(fos);){
                obj.writeObject(bike);
                obj.writeObject(car);
                System.out.println("file operation was successful");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}