package com.example.tdd_practice.iloveyouboss;

import java.util.*;
import java.util.stream.*;

public class Person {
    private List<Question> characteristics = new ArrayList<>();

    public void add(Question characteristic) {
        characteristics.add(characteristic);
    }

    public List<Question> getCharacteristics() {
        return characteristics;
    }

    public List<Question> withCharacteristic(String questionPattern) {
        return characteristics.stream().filter(c -> c.getText().endsWith(questionPattern)).collect(Collectors.toList());
    }

}
