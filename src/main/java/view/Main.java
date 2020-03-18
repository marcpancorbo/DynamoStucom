package view;

import controller.DynamoManager;
import model.Empleado;

import java.util.List;

public class Main {

    private static DynamoManager manager = DynamoManager.getInstance();

    public static void main(String[] args) {
        showMenu();
    }


    public static void showMenu() {
        boolean stop = false;
        do {
            showFirstOptions();
            int option = InputAsker.askInt("¿Que quieres hacer?");
            switch (option) {
                case 1:
                    login();
                    break;
                case 0:
                    System.out.println("Cerrando...");
                    stop = true;
                    break;
                default:
                    break;
            }
        } while (!stop);
    }

    public static void showFirstOptions() {
        System.out.println("1 - Login");
        System.out.println("0 - Close");
    }


    public static void login() {
        String userName = InputAsker.askString("Username: ");
        String pass = InputAsker.askString("Contrasena: ");

    }

}
