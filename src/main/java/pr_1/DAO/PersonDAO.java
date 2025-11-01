package pr_1.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pr_1.Model.Book;
import pr_1.Model.Person;

import java.util.List;
@Component
public class PersonDAO {
    PersonDAO personDAO;
    BookDAO bookDAO;
    JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> listOfPeople(){
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }
    public void save(Person person){
        jdbcTemplate.update("insert into person(person_fio, person_year) values(?,?)", person.getPerson_fio(), person.getPerson_year());
    }
    public Person show(int id){
        return jdbcTemplate.query("select * from person where person_id = ?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public void update(int id, Person person){
        jdbcTemplate.update("update person set person_fio = ?, person_year = ? where person_id = ?",person.getPerson_fio(), person.getPerson_year(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("delete from person where person_id = ?", id);
    }

    public Person personOfBook(int id){
        return jdbcTemplate.query("select * from person p join book b on p.person_id = b.person_id where b.book_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

}
