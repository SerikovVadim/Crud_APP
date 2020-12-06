package ru.sbrf.Crud.DAO;

import org.springframework.stereotype.Component;
import ru.sbrf.Crud.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private List<Person> people;


    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Boris",24,"boris@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Roman",26,"roman@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Bob", 30,"bob@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Mike",36,"mike@mail.ru"));
    }


    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
