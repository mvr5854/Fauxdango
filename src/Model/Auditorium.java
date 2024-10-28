package Model;

import java.util.ArrayList;
import java.util.List;

public class Auditorium extends DataStoreObj {
    private int auditoriumNum;
    private List<Showing> showings = new ArrayList<>();

    public Auditorium(long id, int auditoriumNum) {
        super(id);
        this.auditoriumNum = auditoriumNum;
    }

    public String toString() {
        return String.valueOf(auditoriumNum);
    }

    public void addShowing(long id) {
        Showing showing = Datastore.getShowingById(id);
        showings.add(showing);
    }

    public List<Showing> getShowings() {
        return showings;
    }
}
