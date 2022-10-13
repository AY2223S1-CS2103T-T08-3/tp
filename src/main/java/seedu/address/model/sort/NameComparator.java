package seedu.address.model.sort;

import java.util.Comparator;

import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * NameComparator class to sort the persons list based on names.
 */
public class NameComparator implements Comparator<Person> {
    private final String comparator = "name";
    private Email getN;

    @Override
    public int compare(Person x, Person y) {
        getN = y.getEmail();
        return x.getName().fullName.compareTo(y.getName().fullName);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
