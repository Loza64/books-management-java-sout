import Services.BookServices.Books;
import Services.BookServicesIMPL;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Loans loans = new Loans();
        BookServicesIMPL bookServices = new BookServicesIMPL(); // Replace with the actual implementation class

        bookServices.addItemToList(new Books("LC-0001-A", "Cien años de soledad", "Gabriel García Márquez", "Libro", "Una novela que narra la historia de la familia Buendía en el pueblo ficticio de Macondo.", "available"));
        bookServices.addItemToList(new Books("LC-0002-B", "El nombre del viento", "Patrick Rothfuss", "Libro", "La historia de Kvothe, un joven con un talento extraordinario, que relata sus aventuras y desventuras.", "available"));
        bookServices.addItemToList(new Books("LC-0003-C", "Orgullo y prejuicio", "Jane Austen", "Libro", "Una novela clásica que explora las complejidades del amor, el estatus social y la vida en la Inglaterra del siglo XIX.", "available"));

        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            System.out.print("Ingrese una opción: \n" +
                    "1) Listado de libros \n" +
                    "2) Realizar préstamo \n" +
                    "3) Agregar un nuevo libro \n" +
                    "4) Listado de prestamos \n" +
                    "5) Listado de libros prestados \n" +
                    "0) Salir \n");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("{");
                    System.out.println("  \"Listado de libros\": [");
                    // Utilizar un contador para facilitar la impresión
                    int count = 0;
                    int totalBooks = bookServices.getList().size(); // Obtener el total de libros

                    for (Books book1 : bookServices.getList()) {
                        System.out.println("    {");
                        System.out.println("      \"ID\": " + book1.getId() + ",");
                        System.out.println("      \"Autor\": \"" + book1.getAuthor() + "\",");
                        System.out.println("      \"Título\": \"" + book1.getTitle() + "\",");
                        System.out.println("      \"Tipo\": \"" + book1.getType() + "\",");
                        System.out.println("      \"Descripcion\": \"" + book1.getDescription() + "\",");
                        System.out.println("      \"Estado\": \"" + book1.getState() + "\"");
                        System.out.print("    }");
                        if (count < totalBooks - 1) {
                            System.out.println(",");
                        } else {
                            System.out.println();
                        }
                        count++;
                    }

                    System.out.println("  ]");
                    System.out.println("}");
                    break;

                case 2:

                    System.out.println("Ingrese su nombre:");
                    String peopleName = scanner.nextLine();

                    System.out.println("Si es mayor de edad ingrese su documento si no ponga 00000000-0:");
                    String dui = scanner.nextLine();

                    System.out.println("Ingrese el ID del libro:");
                    String bookId = scanner.nextLine();

                    boolean check = bookServices.isBorrowed(bookId);
                    if (check) {
                        System.out.println("El libro ya está prestado.");
                    } else {
                        loans.newLoan(new Loans(
                                bookId,
                                LocalDate.now().toString(),
                                LocalDate.now().plusMonths(3).toString(),
                                peopleName,
                                dui));
                        bookServices.lendBook(bookId);
                        System.out.println("El préstamo se realizó correctamente.");
                    }
                    break;

                case 3:
                    System.out.println("Ingrese el título del libro:");
                    String title = scanner.nextLine();

                    System.out.println("Ingrese el autor:");
                    String author = scanner.nextLine();

                    System.out.println("Ingrese el tipo (Libro/Manga/Periódico):");
                    String type = scanner.nextLine();

                    System.out.println("Ingrese descripcion el libro:");
                    String description = scanner.nextLine();

                    String newId = generateBookId(type);
                    bookServices.addItemToList(new Books(newId, title, author, type, description, "available"));
                    System.out.println("Libro agregado con éxito. ID asignado: " + newId);
                    break;

                case 4:
                    System.out.println("{");
                    System.out.println("  \"Listado de préstamos\": [");

                    int countLoans = 0;
                    int totalLoans = loans.getLoans().size(); // Obtener el total de préstamos

                    for (Loans loans1 : loans.getLoans()) {
                        System.out.println("    {");
                        System.out.println("      \"Persona\": \"" + loans1.getPersonName() + "\",");
                        System.out.println("      \"Persona DOC\": \"" + loans1.getPersonDoc() + "\",");
                        System.out.println("      \"Libro ID\": " + loans1.getIdBook() + ",");
                        System.out.println("      \"Fecha de préstamo\": \"" + loans1.getStartLoan() + "\",");
                        System.out.println("      \"Fecha de devolución\": \"" + loans1.getEndLoan() + "\",");
                        System.out.print("    }");

                        if (countLoans < totalLoans - 1) {
                            System.out.println(",");
                        } else {
                            System.out.println();
                        }
                        countLoans++;
                    }

                    System.out.println("  ]");
                    System.out.println("}");
                    break;

                case 5:
                    System.out.println("{");
                    System.out.println("  \"Listado de libros\": [");
                    // Utilizar un contador para facilitar la impresión
                    int countLoandBooks = 0;
                    int totalLoandsBooks = bookServices.getList().size(); // Obtener el total de libros

                    for (Books book1 : bookServices.findByState("borrowed")) {
                        System.out.println("    {");
                        System.out.println("      \"ID\": " + book1.getId() + ",");
                        System.out.println("      \"Autor\": \"" + book1.getAuthor() + "\",");
                        System.out.println("      \"Título\": \"" + book1.getTitle() + "\",");
                        System.out.println("      \"Tipo\": \"" + book1.getType() + "\",");
                        System.out.println("      \"Descripcion\": \"" + book1.getDescription() + "\",");
                        System.out.println("      \"Estado\": \"" + book1.getState() + "\"");
                        System.out.print("    }");
                        if (countLoandBooks < totalLoandsBooks - 1) {
                            System.out.println(",");
                        } else {
                            System.out.println();
                        }
                        countLoandBooks++;
                    }

                    System.out.println("  ]");
                    System.out.println("}");
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    public static String generateBookId(String type) {
        Random random = new Random();
        int num = 1000 + random.nextInt(9000);
        char letter = (char) ('A' + random.nextInt(26));
        String prefix = switch (type.toLowerCase()) {
            case "manga" -> "MG";
            case "periódico" -> "PR";
            default -> "LC";
        };
        return prefix + "-" + num + "-" + letter;
    }
}
