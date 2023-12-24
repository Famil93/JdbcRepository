package az.spring.familbudaqjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
        private int id;
        private String name;
        private String surname;
        private int age;
        private double salary;



}
