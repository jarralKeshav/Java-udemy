package org.studyeasy;

import java.io.Serial;
import java.io.Serializable;

public class Vehicle implements Serializable {
    @Serial
    private static final long serialVersionUID = -8948434491824001605L;
    private final String type;
    private final int number;

    public Vehicle(String type, int number) {
        this.type = type;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", number=" + number +
                '}';
    }
}
