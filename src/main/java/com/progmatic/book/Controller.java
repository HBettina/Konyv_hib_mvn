package com.progmatic.book;

import com.progmatic.book.HibernateContext;
import com.progmatic.book.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import java.time.Year;
import java.util.List;

public class Controller implements AutoCloseable {

    private HibernateContext model = new HibernateContext();

    public void addNewAuthor(Author authorToBeSaved) {
    // public void addNewAuthor(Author authorToBeSaved) int id, String name, Year dob, Gender gender {
        Session s = model.getSession();
        Transaction tx = s.beginTransaction();

        try {
//            Author a1 = new Author(id, name, dob, gender);
            s.persist(authorToBeSaved);

            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }
    }
    public void addNewBook(Long isbn, String title, int authorId, Year dop, int edition) {
        Session s = model.getSession();
        Transaction tx = s.beginTransaction();

        try {

            Book b1 = new Book(isbn, title, getAnAuthor(authorId), dop, edition);
            s.persist(b1);
            s.flush();
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }
    }
    public void addNewStore(Store storeToBeSaved) {
        Session s = model.getSession();
        Transaction tx = s.beginTransaction();
        try {
            s.persist(storeToBeSaved);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }
    }
    public void deleteAuthor(int aId) {
        Session session = model.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Author author = session.find(Author.class, aId);
            session.remove(author);
//            MutationQuery q = session.createMutationQuery("DELETE FROM Pizza WHERE id=:pid");
//            q.setParameter("pid", pid);
//            q.executeUpdate();
//            session.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public Author getAnAuthor(int authorId) {
        Session session = model.getSession();

//        Transaction tx = session.beginTransaction();

        Query<Author> q = session.createQuery("FROM Author", Author.class);
        Author a1 = new Author();
        for (Author author: q.list()) {
            if(author.getId() == authorId) {
                a1 = author;
            }
        }
//        session.clear();
//        tx.commit();
        return a1;
    }
    public void getBooksByTitle(String title){
        try (
            Session session = model.getSession();
        ) {
            Transaction tx = session.beginTransaction();
            Query<Book> q = session.createQuery("FROM Book", Book.class);
        for (Book book : q.list()) {
            if(title.equalsIgnoreCase(book.getTitle())) {
                System.out.println(book);
            }
        }
    }}
    public void getBooksByAuthor(String nameOfAuthor){
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();
        Query<Book> q = session.createQuery("FROM Book", Book.class);
        for (Book book : q.list()) {
            if(nameOfAuthor.equalsIgnoreCase(book.getAuthor().getName())) {
                System.out.println(book);
            }
        }
    }
    public void getBooksByIsbn(Long isbn){
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();
        Query<Book> q = session.createQuery("FROM Book", Book.class);
        for (Book book : q.list()) {
            if(isbn == book.getIsbn()) {
                System.out.println(book);
            }
        }
    }
    public void modifyAuthor(int idOfTheAuthor, String name, Year dob, Gender gender){
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Author author = (Author) session.get(Author.class, idOfTheAuthor);
            author.setName(name);
            author.setDob(dob);
            author.setGender(gender);
            session.merge(author);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void modifyBook(Long isbn, String title, int authorId, Year dop, int edition){
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Book book = (Book) session.get(Book.class, isbn);
            book.setTitle(title);
            book.setAuthor(getAnAuthor(authorId));
            book.setDop(dop);
            book.setEdition(edition);
            session.merge(book);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void modifyStore(int id, Address address, String owner){
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Store store = (Store) session.get(Store.class, id);
            store.setAddress(address);
            store.setOwner(owner);
            session.merge(store);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
//    public void addPizza(String name, Integer price) {
//
//        Pizza p = new Pizza();
//
//        p.setName(name);
//        p.setPrice(price);
//
//        Session session = model.getSession();
//
//        Transaction tx = session.beginTransaction();
//
//        session.persist(p);
//
////            session.clear();
////            session.merge(p);
////
////            session.remove(p);
////            session.persist(p);
////
////            Pizza a = session.find(Pizza.class, p.getId());
//
//        session.flush();
//
//        session.getTransaction().commit();
//        System.out.println("Pizza id = " + p.getId());
//    }
//
//    public void getAllPizza() {
//        Session session = model.getSession();
//
//        Transaction tx = session.beginTransaction();
//
////        Pizza1 p1 = new Pizza1();
////        p1.setName("Almas");
////        session.persist(p1);
//
////        Query<Pizza1> q = session.createQuery("FROM Pizza1", Pizza1.class);
////        for (Pizza1 p : q.list()) {
////            System.out.println(p);
////        }
//
////        NativeQuery<Object> pizzas = session.createNativeQuery("SELECT * FROM pizza p", Object.class);
////
////        for (Object a: pizzas.list()) {
////            System.out.println(a);
////        }
//
////        session.flush();
//
//        SelectionQuery<Pizza> q = session.createSelectionQuery("SELECT p FROM Pizza p", Pizza.class);
//
////        Query<String> q = session.createQuery("SELECT p.name FROM Pizza p", String.class);
////        for (String p : q.list()) {
////            System.out.println(p);
////        }
//
////        Query<Pizza> q = session.createQuery("SELECT P FROM Pizza P WHERE P.id = 6", Pizza.class);
////        Query<Pizza> q = session.createQuery("FROM Pizza P WHERE P.id > 3 ORDER BY P.price DESC", Pizza.class);
//
////        Query<Pizza> q = session.createQuery("FROM Pizza P WHERE P.price < :priceLimit ORDER BY P.price DESC", Pizza.class);
////        q.setParameter("priceLimit", 1000);
//
////        Query<Pizza> q = session.createQuery("FROM Pizza P ORDER BY P.price DESC Limit 10 offset 5", Pizza.class);
////        Query<Pizza> q = session.createQuery("FROM Pizza P ORDER BY P.price DESC", Pizza.class);
////        q.setFirstResult(5);
////        q.setMaxResults(10);
//
//        for (Pizza p : q.list()) {
//            System.out.println(p);
//        }
//        session.clear();
//
////        Long priceSum = session.createQuery("select sum(p.price) from Pizza p ", Long.class)
////                .getSingleResult();
////        System.out.println(String.format("Osszes pizza ara: %d", priceSum));
////
////        String pizzasName = session.createQuery("select group_concat(p.name) from Pizza p ", String.class)
////                .getSingleResult();
////        System.out.printf("Osszes pizza neve: %s%n", pizzasName);
//
//
//        session.getTransaction().commit();
//    }

    @Override
    public void close() throws Exception {
        model.close();
    }
//    public void listStudent() {
//        Session session = model.getSession();
//
//        Transaction tx = session.beginTransaction();
//
//        Query<Student> q = session.createQuery("FROM Student", Student.class);
//        for (Student p : q.list()) {
//            System.out.println(p);
//        }
//        session.clear();
//
//        tx.commit();
//    }
//
//    public void connectS2C(Integer sid, Integer cid) {
//        Session session = model.getSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            Student s = session.find(Student.class, sid);
//            Course c = session.find(Course.class, cid);
//
//            s.getCourses().add(c);
//            session.persist(s);
//
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//        }
//    }
//
//    public void listCourses() {
//        Session session = model.getSession();
//
//        Transaction tx = session.beginTransaction();
//
//        Query<Course> q = session.createQuery("FROM Course", Course.class);
//        for (Course p : q.list()) {
//            System.out.println(p);
//        }
//        session.clear();
//
//        session.getTransaction().commit();
//    }
}
