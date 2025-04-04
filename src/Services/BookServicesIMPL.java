package Services;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookServicesIMPL implements BookServices{

    private final ArrayList<Books> list = new ArrayList<>();

    @Override
    public void addItemToList(Books book) {
        list.add(book);
    }

    @Override
    public ArrayList<Books> getList() {
        return list;
    }

    @Override
    public Books getItemBook(String id) {
        return list.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Books> findByState(String state) {
        return (ArrayList<Books>) this.list.stream()
                .filter(book -> book.getState().equals(state))
                .collect(Collectors.toList());
    }

    @Override
    public void lendBook(String id) {
        list.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .ifPresent(book -> book.setState("borrowed"));
    }

    @Override
    public boolean isBorrowed(String id) {
        return this.list.stream().anyMatch(book -> book.getId().equals(id) && book.getState().equals("borrowed"));
    }
}
