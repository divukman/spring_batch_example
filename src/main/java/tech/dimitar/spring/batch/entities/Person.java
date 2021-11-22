package tech.dimitar.spring.batch.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Person {
    private String lastName;
    private String firstName;
}
