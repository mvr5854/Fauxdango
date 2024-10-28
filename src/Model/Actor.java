package Model;

import java.time.LocalDate;

public class Actor extends DataStoreObj {

    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public Actor(long id, String firstName, String lastName, String birthday) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = LocalDate.parse(birthday);

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return String.format("%s %s (%s)", firstName, lastName, birthday);
    }
}
