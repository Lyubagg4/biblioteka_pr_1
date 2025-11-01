package pr_1.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pr_1.Model.Book;
import pr_1.Model.Person;

import java.util.List;

@Component
public class BookDAO {
    PersonDAO personDAO;
    BookDAO bookDAO;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> listOfBooks(){
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book){
        jdbcTemplate.update("insert into book" +
                "(book_name,book_author,book_year) values(?,?,?)", book.getBook_name(), book.getBook_author(), book.getBook_year());
    }
    public Book show(int id){
        return jdbcTemplate.query("select * from book where book_id = ?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public void update(int id, Book book){
        jdbcTemplate.update("update book set " +
                "book_name = ?, book_author = ?, book_year = ? where book_id = ?", book.getBook_name(), book.getBook_author(), book.getBook_year(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("delete from book where book_id = ?", id);
    }
    public List<Book> booksOfPerson(int id){
        return jdbcTemplate.query("select * from person p join book b on p.person_id = b.person_id where p.person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
    public void free(int id, Person person){
        jdbcTemplate.update("update book set person_id=null where book_id=?",id);
    }
    public void addPerson(int idOfTheBook, int idOfThePerson){
        jdbcTemplate.update("update book set person_id=? where book_id=?",idOfThePerson,idOfTheBook);
    }
}
