import java.util.ArrayList;

public class Loans {
    private String idBook;
    private String startLoan;
    private String endLoan;
    private String personName;
    private String personDoc = "00000000-0";
    private ArrayList<Loans> list = new ArrayList<>();

    public Loans(String idBook, String startLoan, String endLoan, String personName, String personDoc) {
        this.idBook = idBook;
        this.startLoan = startLoan;
        this.endLoan = endLoan;
        this.personName = personName;
        this.personDoc = personDoc;
    }

    public Loans() {
    }


    public String getIdBook() {
        return idBook;
    }

    public String getStartLoan() {
        return startLoan;
    }

    public String getEndLoan() {
        return endLoan;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonDoc() {
        return personDoc;
    }

    public ArrayList<Loans> getList() {
        return list;
    }

    public void newLoan(Loans loans) { list.add(loans); }

    public ArrayList<Loans> getLoans() { return list; }
}




