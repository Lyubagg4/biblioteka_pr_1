package pr_1.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pr_1.DAO.BookDAO;
import pr_1.DAO.PersonDAO;
import pr_1.Model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    BookDAO bookDAO;
    PersonDAO personDAO;

    @Autowired
    public PeopleController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("person",personDAO.listOfPeople());
        return "people/listOfPeople";
    }
    @GetMapping("/new")
    public String create(@ModelAttribute("person") Person person){
        return "people/new";
    }
    @GetMapping("/{id}")
    public String show(Model model,
                       @PathVariable("id") int id){
        model.addAttribute("person",personDAO.show(id));
        model.addAttribute("books", bookDAO.booksOfPerson(id));
        return "people/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personDAO.show(id));
        return "people/edit";
    }
    @PostMapping()
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Возвращаемся обратно на форму, чтобы показать ошибки
            return "people/new"; // имя вашего Thymeleaf шаблона с формой
        }
        personDAO.save(person);
        return "redirect:/people";
    }
    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("person") @Valid Person person,
                       BindingResult bindingResult,
                       @PathVariable("id") int id){
        if (bindingResult.hasErrors()) {
            // Возвращаемся обратно на форму, чтобы показать ошибки
            return "people/edit"; // имя вашего Thymeleaf шаблона с формой
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }
    @PatchMapping("/{book_id}/free")
    public String free(@ModelAttribute("person") Person person,
                       @PathVariable("book_id") int id){
        bookDAO.free(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id" )int id){
        personDAO.delete(id);
        return "redirect:/people";
    }


}
