package com.progmatic.book;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        try (
                Scanner sc = new Scanner(System.in);
                Controller controller = new Controller();
                View view = new View();
        ) {
            view.mainMenu(sc);
//            controller.addNewItem();
//            System.out.println(controller.getAnAuthor(565));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}