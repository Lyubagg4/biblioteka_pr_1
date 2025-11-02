package pr_1.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pr_1.DAO.BookDAO;
import pr_1.DAO.PersonDAO;
import pr_1.Model.Book;

@Controller
@RequestMapping("/books")
public class BooksController {
    BookDAO bookDAO;
    PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.listOfBooks());
        return "books/listOfBooks";
    }
    @GetMapping("/new")
    public String create(@ModelAttribute("book") Book book){
        return "books/new";
    }
    @GetMapping("/{id}")
    public String show(Model model,
                       @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("person", personDAO.personOfBook(id));
        model.addAttribute("people",personDAO.listOfPeople());
        return "books/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }
    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("book") @Valid Book book,
                       BindingResult bindingResult,
                       @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/addPerson")
    public String addPerson(@RequestParam("person_id") int person_id,
                       @PathVariable("id") int idOfTheBook){
        bookDAO.addPerson(idOfTheBook, person_id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id" )int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
