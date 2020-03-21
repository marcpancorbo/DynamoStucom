package view;

import controller.DynamoManager;
import model.*;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    private static DynamoManager manager = DynamoManager.getInstance();

    public static void main(String[] args)  {
        showMenu();
    }

    /**
     * Metodo que sirve para seleccionar la opcion del menu de login
     */
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

    /**
     * Opciones del menu de login
     */
    public static void showFirstOptions() {
        System.out.println("1 - Login");
        System.out.println("0 - Close");
    }

    /**
     * Metodo que sirve para pedir los datos necesarios para hacer login
     */
    public static void login() {
        String userName = InputAsker.askString("Username: ");
        String pass = InputAsker.askString("Contrasena: ");
        if (manager.login(userName, pass)) {
            showUserOptions();
        } else {
            System.out.println("Error!");
        }
    }

    /**
     * Metodo que sirve para mostrar un menu con las opciones que tiene el usuario
     */
    public static void showUserOptions() {
        boolean stop = false;
        do {
            System.out.println("--- " + manager.getWorkerActive().getUserName() + " ---");
            System.out.println("1 - Empleados");
            System.out.println("2 - Incidencias");
            System.out.println("3 - Eventos");
            System.out.println("4 - Ver Ultimo Inicio de Sesion");
            System.out.println("5 - Ver Ranking");
            System.out.println("0 - Logout");
            int option = InputAsker.askInt("¿Que quieres hacer?");
            switch (option) {
                case 1:
                    showWorkerOptions();
                    break;
                case 2:
                    showIncidenceOptions();
                    break;
                case 3:
                    showEventsOptions();
                    break;
                case 4:
                    getUltimoInicioSesion();
                    break;
                case 5:
                    showRanking();
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

    /**
     * Metodo que muestra el menu del empleado
     */
    public static void showWorkerOptions() {
        boolean stop = false;
        do {
            System.out.println("--- " + manager.getWorkerActive().getUserName() + " ---");
            System.out.println("1 - Ver todos los empleados");
            System.out.println("2 - Modificar empleado");
            System.out.println("3 - Eliminar empleado");
            System.out.println("4 - Crear empleado");
            System.out.println("0 - Atras");
            int option = InputAsker.askInt("¿Que quieres hacer?");
            switch (option) {
                case 1:
                    showAllWorkers();
                    break;
                case 2:
                    updateEmpleado();
                    break;
                case 3:
                    deleteWorker();
                    break;
                case 4:
                    createWorker();
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

    /**
     * metodo que sirve para mostrar el menu de los eventos
     */
    public static void showEventsOptions() {
        boolean stop = false;
        do {
            System.out.println("--- " + manager.getWorkerActive().getUserName() + " ---");
            System.out.println("1 - Ver todos los eventos");
            System.out.println("0 - Atras");
            int option = InputAsker.askInt("¿Que quieres hacer?");
            switch (option) {
                case 1:
                    showAllEvents();
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

    /**
     * Metodo que sirve para pedir los datos necesarios para crear un empleado
     */
    public static void createWorker() {
        Empleado empleado = new Empleado();
        empleado.setName(InputAsker.askString("Name: "));
        boolean existe = false;
        String username;
        do {
            username = InputAsker.askString("Username: ");
            if (manager.getWorkerByUsername(username) != null) {
                System.out.println("Ese nombre de usuario ya está registrado");
                existe = true;
            } else {
                existe = false;
            }
        } while (existe);
        empleado.setUserName(username);
        boolean verif;
        String password;
        do {
            password = InputAsker.askString("Password: ");
            String passwordVerif = InputAsker.askString("Verifica tu password");
            if (!password.equalsIgnoreCase(passwordVerif)) {
                verif = false;
                System.out.println("Tus dos contrasenas no coinciden");
            } else {
                verif = true;
            }
        } while (!verif);
        empleado.setPassword(password);
        empleado.setPhoneNumber(InputAsker.askString("PhoneNumber: "));
        manager.storeWorker(empleado);
        System.out.println("Usuario registrado");
    }

    /**
     * Metodo que muestra todos los empleados
     */
    public static void showAllWorkers() {
        List<Empleado> workers = manager.getAllWorkers();
        if (!workers.isEmpty()) {
            for (Empleado e : manager.getAllWorkers()) {
                System.out.println("- " + e.toString());
            }
        } else {
            System.out.println("NO existen workers que mostrar");
        }
    }

    /**
     * Metodo que borra un empleado
     */
    public static void deleteWorker() {
        Empleado worker = selectEmpleado();
        manager.removeEmpleado(worker);
        System.out.println("Empleado borrado correctamente");
    }

    /**
     * Metodo que muestra el menu de las incidencias
     */
    public static void showIncidenceOptions() {
        boolean stop = false;
        do {
            System.out.println("--- " + manager.getWorkerActive().getUserName() + " ---");
            System.out.println("1 - Ver todas las incidencias");
            System.out.println("2 - Crear incidencia");
            System.out.println("3 - Buscar por origen");
            System.out.println("4 - Buscar por destino");
            System.out.println("0 - Atras");
            int option = InputAsker.askInt("¿Que quieres hacer?");
            switch (option) {
                case 1:
                    showAllIncidence();
                    break;
                case 2:
                    createIncidence();
                    break;
                case 3:
                    findIncidenceByOrigin();
                    break;
                case 4:
                    findIncidenceByDestination();
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

    /**
     * Metodo que sirve para pedir los datos necesarios para crear una incidencia
     */
    public static void createIncidence() {
        Empleado worker = selectEmpleado();
        Incidencia incidence = new Incidencia();
        incidence.setDestination(worker.getUserName());
        incidence.setDetail(InputAsker.askString("Detalle de la incidencia: "));
        ArrayList<String> options = new ArrayList<>();
        options.add(TipoUrgencia.NORMAL.name());
        options.add(TipoUrgencia.URGENTE.name());
        incidence.setType(InputAsker.askString("Tipo de la incidencia: ", options));
        incidence.setDate(new Date());
        incidence.setOrigin(manager.getWorkerActive().getUserName());
        manager.storeIncidencia(incidence);
        System.out.println("Incidencia creada correctamente");
    }

    /**
     * Metodo que muestra todas las incidencias
     */
    public static void showAllIncidence() {
        List<Incidencia> incidencias = manager.getAllIncidencia();
        if (!incidencias.isEmpty()) {
            incidencias.stream().sorted(Comparator.comparing(Incidencia::getDate).reversed()).forEach(
                    incidencia -> System.out.println(incidencia.toString()));
        } else {
            System.out.println("NO existen incidencias que mostrar");
        }
    }

    /**
     * Metodo que muestra todos los eventos
     */
    public static void showAllEvents() {
        List<Evento> eventos = manager.getAllEventos();
        if (!eventos.isEmpty()) {
            eventos.stream().sorted(Comparator.comparing(Evento::getDate).reversed()).forEach(
                    evento -> System.out.println(evento.toString())
            );
        } else {
            System.out.println("NO existen eventos que mostrar");
        }
    }

    /**
     * Metodo que busca una incidencia por su origen
     */
    public static void findIncidenceByOrigin() {
        Empleado worker = selectEmpleado();
        List<Incidencia> incidencie = manager.getIncidenciaByOrigen(worker);
        if (!incidencie.isEmpty()) {
            System.out.println("--- INCIDENCIAS ---");
            for (Incidencia i : incidencie) {
                System.out.println("- " + i.toString());
            }
        } else {
            System.out.println("NO existen incidencias creadas por este trabajador");
        }
    }

    /**
     * Metodo que busca una incidencia por su destino
     */
    public static void findIncidenceByDestination() {
        Empleado worker = selectEmpleado();
        List<Incidencia> incidencie = manager.getIncidenciaByDestino(worker);
        if (!incidencie.isEmpty()) {
            System.out.println("--- INCIDENCIAS ---");
            for (Incidencia i : incidencie) {
                System.out.println("- " + i.toString());
            }
        } else {
            System.out.println("NO existen incidencias enviadas a este trabajador");
        }
    }

    /**
     * Metodo que muestra el ultimo login
     */
    public static void getUltimoInicioSesion() {
        Empleado worker = selectEmpleado();
        Evento evento = manager.getUltimoInicioSesion(worker);
        if (evento == null) {
            System.out.println("Este empleado aún no ha iniciado sesión");
        } else {
            System.out.println(evento.toString());
        }
    }

    /**
     * Metodo para seleccionar un empleado
     * @return
     */
    public static Empleado selectEmpleado() {
        List<Empleado> workers = manager.getAllWorkers();
        if (!workers.isEmpty()) {
            for (int i = 0; i < workers.size(); i++) {
                System.out.println(i + 1 + " - " + workers.get(i).toString());
            }
            int escogido = InputAsker.askInt("Escoge el trabajador: ", 1, workers.size());
            return workers.get(escogido - 1);
        } else {
            System.out.println("NO existen workers registrados");
        }
        return null;
    }

    /**
     * Metodo que da las opciones del menu para editar un empleado
     */
    public static void updateEmpleado() {
        Empleado empleado = manager.getWorkerActive();
        boolean salir = false;
        do {
            menuUpdate();
            int option = InputAsker.askInt("Introduce la opción para modificar el usuario");
            switch (option) {
                case 1:
                    updateNameWorker(empleado);
                    break;
                case 2:
                    updatePasswordWorker(empleado);
                    break;
                case 3:
                    updatePhoneNumberWorker(empleado);
                    break;
                case 0:
                    salir=true;
                    break;
                default:
                    System.out.println("El numero no corresponde a ninguna opción.");
            }
        }while (!salir);
    }

    /**
     * Menu de modificar un empleado
     */
    public static void menuUpdate() {
        System.out.println("Modificar Empleado");
        System.out.println("1.Name");
        System.out.println("2.Password");
        System.out.println("3.PhoneNumber");
        System.out.println("0.Salir");
    }

    /**
     * Metodo que sirve para pedir los datos necesarios para modificar el nombre del empleado
     * @param empleado
     */
    public static void updateNameWorker(Empleado empleado) {
        String name = InputAsker.askString("Introduce tu nuevo nombre");
        String pass = InputAsker.askString("Introduce tu password");
        if(manager.checkPasswordWorker(pass,empleado)){
            empleado.setName(name);
            manager.updateEmpleado(empleado);
            System.out.println("Nombre del empleado actualizado.");
        }else{
            System.out.println("El password introducido no coincide con la que tiene el empleado");
        }
    }

    /**
     * Metodo que sirve para pedir los datos necesarios para modificar la password del empleado
     * @param empleado
     */
    public static void updatePasswordWorker(Empleado empleado){
        String currentPass = InputAsker.askString("Introduce tu password actual");
        String newPass = InputAsker.askString("Introduce tu nueva password");
        String newPass2 = InputAsker.askString("Vuelve a introducir la nueva password");
        if(manager.checkPasswordWorker(currentPass,empleado)){
            if(manager.checkNewPassword(newPass,newPass2)){
                empleado.setPassword(newPass);
                manager.updateEmpleado(empleado);
                System.out.println("Password del empleado actualizada.");
            }else{
                System.out.println("La nueva password no ha sido escrita de forma incorrecta");
            }
        }else {
            System.out.println("El password introducido no coincide con la que tiene el empleado");
        }
    }

    /**
     * Metodo que sirve para pedir los datos necesarios para modificar el numero de telefono de un empleado
     * @param empleado
     */
    public static void updatePhoneNumberWorker(Empleado empleado){
        String phoneNumber=InputAsker.askString("Introduce tu número de teléfono");
        String pass=InputAsker.askString("Introduce tu password");
        if(manager.checkPasswordWorker(pass,empleado)){
            empleado.setPhoneNumber(phoneNumber);
            manager.updateEmpleado(empleado);
            System.out.println("PhoneNumber del empleado actualizado");
        }else{
            System.out.println("La password escrita no coincide con la del empleado");
        }
    }
    public static void showRanking(){
        List<RankingTO> ranking = manager.getRanking();
        ranking.stream().sorted(Comparator.comparing(RankingTO::getIncidencias).reversed()).forEach(rankingTO -> {
            System.out.println("Username: "+rankingTO.getUsername()+" Indicendias: "+rankingTO.getIncidencias());
        });
    }
}
