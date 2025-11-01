package pr_1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class Person {
    private int person_id;
    @NotEmpty(message = "Поле не должно быть пустым")
    private String person_fio;
    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 1,message = "Год должен быть больше 0")
    private Integer person_year;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public void setPerson_fio(String person_fio) {
        this.person_fio = person_fio;
    }

    public void setPerson_year(Integer person_year) {
        this.person_year = person_year;
    }

    public String getPerson_fio() {
        return person_fio;
    }

    public Integer getPerson_year() {
        return person_year;
    }
}
