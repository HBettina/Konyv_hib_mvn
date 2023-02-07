package com.progmatic.book;

import com.progmatic.book.model.Address;
import com.progmatic.book.model.Author;
import com.progmatic.book.model.Gender;
import com.progmatic.book.model.Store;
import org.hibernate.type.descriptor.java.YearJavaType;

import java.time.Year;
import java.util.Scanner;

public class View implements AutoCloseable {
    private HibernateContext model = new HibernateContext();
    Controller controller = new Controller();
    public void mainMenu(Scanner sc) {
        String choice = "qwer";
        while (!choice.equalsIgnoreCase("x")) {
            printMenu();
            choice=sc.nextLine();
            switch (choice) {
                case "1":{
                    System.out.println("Add meg a szerző azonosító számát!");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Add meg a szerző nevét!");
                    String name = sc.nextLine();
                    System.out.println("Add meg a szerző születési dátumát!");
                    Year dob = Year.of(Integer.parseInt(sc.nextLine()));
                    System.out.println("Férfi vagy nő? F/N");
                    String g = sc.nextLine();
                    Gender gender = null;
                    if (g.equalsIgnoreCase("f")) {
                        gender = Gender.MALE;
                    }
                    if (g.equalsIgnoreCase("n")) {
                        gender = Gender.FEMALE;
                    }
                    controller.addNewAuthor(new Author(id, name, dob, gender));
                    System.out.println("A szerzőt elmentettem.");
                    break;
                }
                case "2":
                    System.out.println("Add meg a könyv ISBN számát!");
                    Long isbn = Long.parseLong(sc.nextLine());
                    System.out.println("Add meg a könyv címét!");
                    String title = sc.nextLine();
                    System.out.println("Add meg a szerző azonosító számát!");
                    int authorId = Integer.parseInt(sc.nextLine());
                    System.out.println("Add meg a kiadás évét!");
                    Year dop = Year.of(Integer.parseInt(sc.nextLine()));
                    System.out.println("Add meg a kiadás számát!");
                    int edition = Integer.parseInt(sc.nextLine());
                    controller.addNewBook(isbn, title, authorId, dop, edition);
                    System.out.println("A könyvet elmentettem.");
                    break;
                case "3":
                    System.out.println("Add meg a bolt azonosító számát!");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Add meg a bolt címét! Város:");
                    String city = sc.nextLine();
                    System.out.println("Utca neve és közterület típusa:");
                    String street = sc.nextLine();
                    System.out.println("Házszám:");
                    int houseNumber = Integer.parseInt(sc.nextLine());
                    System.out.println("Add meg a bolt tulajdonosának nevét!");
                    String owner = sc.nextLine();
                    controller.addNewStore(new Store(id, new Address(city, street, houseNumber), owner));
                    System.out.println("A boltot elmentettem.");
                    break;
                case "4":
                    System.out.println("Melyik szerzőt szeretnéd törölni? Add meg az azonosító számát!");
                    int aId = Integer.parseInt(sc.nextLine());
                    controller.deleteAuthor(aId);
                    System.out.println("A szerzőt töröltem.");
                    break;
                case "5":
                    System.out.println("Add meg a szerző azonosító számát!");
                    int idOfTheAuthor = Integer.parseInt(sc.nextLine());
                    System.out.println("Add meg a szerző nevét!");
                    String name = sc.nextLine();
                    System.out.println("Add meg a szerző születési dátumát!");
                    Year dob = Year.of(Integer.parseInt(sc.nextLine()));
                    System.out.println("Férfi vagy nő? F/N");
                    String g = sc.nextLine();
                    Gender gender = null;
                    if (g.equalsIgnoreCase("f")) {
                        gender = Gender.MALE;
                    }
                    if (g.equalsIgnoreCase("n")) {
                        gender = Gender.FEMALE;
                    }
                    controller.modifyAuthor(idOfTheAuthor, name, dob, gender);
                    System.out.println("A szerzőt módosítottam.");
                    break;
                case "6":
                    System.out.println("Add meg a könyv ISBN számát!");
                    Long isbnToBeModifidBook = Long.parseLong(sc.nextLine());
                    System.out.println("Add meg a könyv címét!");
                    String titleToBeModifiedBook = sc.nextLine();
                    System.out.println("Add meg a szerző azonosító számát!");
                    int authorIdToBeModifiedBook = Integer.parseInt(sc.nextLine());
                    System.out.println("Add meg a kiadás évét!");
                    Year dopToBeModifiedBook = Year.of(Integer.parseInt(sc.nextLine()));
                    System.out.println("Add meg a kiadás számát!");
                    int editionToBeModifiedBook = Integer.parseInt(sc.nextLine());
                    controller.modifyBook(isbnToBeModifidBook, titleToBeModifiedBook,
                            authorIdToBeModifiedBook, dopToBeModifiedBook,
                            editionToBeModifiedBook);
                    System.out.println("A könyvet módosítottam.");
                    break;
                case "7":
                    System.out.println("Add meg a bolt azonosító számát!");
                    int idToBeModified = Integer.parseInt(sc.nextLine());
                    System.out.println("Add meg a bolt címét! Város:");
                    String newCity = sc.nextLine();
                    System.out.println("Utca neve és közterület típusa:");
                    String newStreet = sc.nextLine();
                    System.out.println("Házszám:");
                    int newHouseNumber = Integer.parseInt(sc.nextLine());
                    System.out.println("Add meg a bolt tulajdonosának nevét!");
                    String newOwner = sc.nextLine();
                    Address newAddress = new Address(newCity, newStreet, newHouseNumber);
                    controller.modifyStore(idToBeModified, newAddress, newOwner);
                    System.out.println("A boltot módosítottam.");
                    break;
                case "8":
                    System.out.println("Add meg a könyv címét!");
                    String t = sc.nextLine();
                    controller.getBooksByTitle(t);
                    break;
                case "9":
                    System.out.println("Add meg a szerző nevét!");
                    String a = sc.nextLine();
                    controller.getBooksByAuthor(a);
                    break;
                case "10":
                    System.out.println("Add meg a könyv ISBN számát!");
                    Long isbnOfTheBook = Long.parseLong(sc.nextLine());
                    controller.getBooksByIsbn(isbnOfTheBook);
                    break;
                default: {

                }
            }
        }
    }
    private void printMenu(){
        System.out.println("*".repeat(21));
        System.out.println("""
                Válassz az alábbi menüpontokból!
                1. Új szerző felvitele
                2. Új könyv felvitele
                3. Új bolt felvitele
                4. Szerző törlése
                5. Szerző módosítása
                6. Könyv módosítása
                7. Bolt módosítása
                8. Könyvek listázása cím alapján
                9. Könyvek listázása szerző alapján
                10. Könyvek listázása ISBN alapján
                X. Kilépés""");
        System.out.println("*".repeat(21));
    }

    @Override
    public void close() throws Exception {
        model.close();
    }
}
