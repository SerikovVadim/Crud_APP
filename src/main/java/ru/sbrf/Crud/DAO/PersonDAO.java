package ru.sbrf.Crud.DAO;

import org.springframework.stereotype.Component;
import ru.sbrf.Crud.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Person> index(){
        List<Person> people = new ArrayList<>();

        try {
            ResultSet resultSet;
            try (Statement statement = connection.createStatement()) {
                String SQL = "SELECT * FROM Person";
                resultSet = statement.executeQuery(SQL);
            }

            while (resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return people;
    }

    public void save(Person person){
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
                    "'," + person.getAge() + ",'" + person.getEmail() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Person show(int id){
        return null;
    }

    public void update(int id, Person updatedPerson){

    }

    public void delete(int id){

    }
}
