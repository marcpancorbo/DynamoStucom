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
                    System.out.println("Ese numero no corresponde a ninguna opcion");
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
        if (manager.login(userName, pass)) {
            showWorkerOptions();
        } else {
            System.out.println("Error!");
        }
    }

    public static void showWorkerOptions() {
        boolean stop = false;
        do {
            System.out.println("--- " + manager.getWorkerActive().getUserName() + " ---");
            System.out.println("1 - Usuarios");
            System.out.println("2 - Incidencias");
            System.out.println("3 - Eventos");
            int option = InputAsker.askInt("¿Que quieres hacer?");
            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    stop = true;
                    break;
                default:
                    System.out.println("Ese numero no corresponde a ninguna opcion");
                    break;
            }
        } while (!stop);
    }

    public static void showUserOptions() {
        boolean stop = false;
        do {
            System.out.println("");
        } while (!stop);
    }


}
