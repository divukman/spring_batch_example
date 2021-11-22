package tech.dimitar.spring.batch.processors;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import tech.dimitar.spring.batch.entities.Person;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(final Person person) {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}