package sense.nl.eevee.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by budi-ahmad-syidiq on 12/10/16.
 */
public class UserSerialize implements Serializable {
    private List<User> results;


    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}
