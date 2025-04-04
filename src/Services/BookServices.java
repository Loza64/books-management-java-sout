package Services;

import java.util.ArrayList;

public interface BookServices {
    void addItemToList(Books book);

    ArrayList<Books> getList();

    Books getItemBook(String id);

    ArrayList<Books> findByState(String state);

    void lendBook(String id);

    boolean isBorrowed(String id);

    public class Books {
        private String id;
        private String title;
        private String author;
        private String type;
        private String description;
        private String state = "available";

        public Books() {
        }

        public Books(String id, String title, String author, String type, String description,String state) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.type = type;
            this.description = description;
            this.state = state;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getType() {
            return type;
        }

        public String getState() {
            return state;
        }

        public String getDescription() {
            return description;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
