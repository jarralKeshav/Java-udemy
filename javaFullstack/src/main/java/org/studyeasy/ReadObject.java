package org.studyeasy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadObject {

    public static void main(String[] args) {

        try(FileInputStream fis = new FileInputStream("studyeasy//vehicle.dat")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                Vehicle v1 = (Vehicle) ois.readObject();
                Vehicle v2 = (Vehicle) ois.readObject();

                System.out.println(v1);
                System.out.println(v2);


            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
