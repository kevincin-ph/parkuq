import enums.TipoUsuario;
import enums.TipoVehiculo;
import java.util.Scanner;
import servicio.ParqueaderoService;
import servicio.UsuarioService;

public class Menu {

    private static ParqueaderoService parqueadero = new ParqueaderoService();
    private static UsuarioService usuarioService  = new UsuarioService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenuPrincipal();
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> menuOperador();
                case 2 -> menuAdministrador();
                case 0 -> System.out.println("\nHasta luego! 👋");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    // ─── MENÚ PRINCIPAL ──────────────────────────────────────────
    static void mostrarMenuPrincipal() {
        System.out.println("\n=============================");
        System.out.println("       PARKUQ - MENÚ");
        System.out.println("=============================");
        System.out.println("1. Operador");
        System.out.println("2. Administrador");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // ─── MENÚ OPERADOR ───────────────────────────────────────────
    static void menuOperador() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENÚ OPERADOR ---");
            System.out.println("1. Registrar ingreso de vehículo");
            System.out.println("2. Registrar salida de vehículo");
            System.out.println("3. Ver vehículos en el parqueadero");
            System.out.println("4. Ver espacios disponibles");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarIngreso();
                case 2 -> registrarSalida();
                case 3 -> parqueadero.mostrarVehiculosDentro();
                case 4 -> parqueadero.mostrarEspaciosDisponibles();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // ─── MENÚ ADMINISTRADOR ──────────────────────────────────────
    static void menuAdministrador() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Ver todos los usuarios");
            System.out.println("2. Registrar nuevo usuario");
            System.out.println("3. Ver usuarios por tipo");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> usuarioService.mostrarUsuarios();
                case 2 -> registrarUsuario();
                case 3 -> verUsuariosPorTipo();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // ─── ACCIONES OPERADOR ───────────────────────────────────────
    static void registrarIngreso() {
        System.out.println("\n-- Registrar Ingreso --");
        System.out.print("Placa: ");
        String placa = scanner.nextLine().toUpperCase();

        System.out.println("Tipo de vehículo:");
        System.out.println("1. Carro  2. Moto  3. Bicicleta");
        System.out.print("Seleccione: ");
        int tipo = leerEntero();
        TipoVehiculo tipoVehiculo;
        switch (tipo) {
            case 1 -> tipoVehiculo = TipoVehiculo.CARRO;
            case 2 -> tipoVehiculo = TipoVehiculo.MOTO;
            case 3 -> tipoVehiculo = TipoVehiculo.BICICLETA;
            default -> {
                System.out.println("Tipo inválido.");
                return;
            }
        }

        System.out.print("Nombre del conductor: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificación: ");
        String id = scanner.nextLine();
        System.out.print("Hora de ingreso (HH:MM): ");
        String hora = scanner.nextLine();
        System.out.print("AM o PM: ");
        String ampm = scanner.nextLine().toUpperCase();
        hora = convertirA24Horas(hora, ampm);

        
       
        try {
            String resultado = parqueadero.registrarIngreso(
                placa, tipoVehiculo, nombre, id, hora);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

  static void registrarSalida() {
    System.out.println("\n-- Registrar Salida --");
    System.out.print("Placa: ");
    String placa = scanner.nextLine().toUpperCase();
    System.out.print("Hora de salida (HH:MM): ");
    String hora = scanner.nextLine();
    System.out.print("AM o PM: ");
    String ampm = scanner.nextLine().toUpperCase();
    hora = convertirA24Horas(hora, ampm);

    try {
        String resultado = parqueadero.registrarSalida(placa, hora);
        System.out.println(resultado);
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}

    // ─── ACCIONES ADMINISTRADOR ──────────────────────────────────
    static void registrarUsuario() {
        System.out.println("\n-- Registrar Usuario --");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificación: ");
        String id = scanner.nextLine();

        System.out.println("Tipo de usuario:");
        System.out.println("1. Estudiante  2. Docente  3. Administrativo  4. Visitante");
        System.out.print("Seleccione: ");
        int tipo = leerEntero();
        TipoUsuario tipoUsuario;
        switch (tipo) {
            case 1 -> tipoUsuario = TipoUsuario.ESTUDIANTE;
            case 2 -> tipoUsuario = TipoUsuario.DOCENTE;
            case 3 -> tipoUsuario = TipoUsuario.ADMINISTRATIVO;
            case 4 -> tipoUsuario = TipoUsuario.VISITANTE;
            default -> {
                System.out.println("Tipo inválido.");
                return;
            }
        }

        System.out.println(usuarioService.registrarUsuario(nombre, id, tipoUsuario));
    }

    static void verUsuariosPorTipo() {
        System.out.println("Tipo: 1.Estudiante  2.Docente  3.Administrativo  4.Visitante");
        System.out.print("Seleccione: ");
        int tipo = leerEntero();
        switch (tipo) {
            case 1 -> usuarioService.mostrarUsuariosPorTipo(TipoUsuario.ESTUDIANTE);
            case 2 -> usuarioService.mostrarUsuariosPorTipo(TipoUsuario.DOCENTE);
            case 3 -> usuarioService.mostrarUsuariosPorTipo(TipoUsuario.ADMINISTRATIVO);
            case 4 -> usuarioService.mostrarUsuariosPorTipo(TipoUsuario.VISITANTE);
            default -> System.out.println("Opción inválida.");
        }
    }

    

    // ─── UTILIDAD ────────────────────────────────────────────────
    static int leerEntero() {
        try {
            int valor = Integer.parseInt(scanner.nextLine());
            return valor;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    // Convierte hora colombiana (12h) a formato 24 horas
static String convertirA24Horas(String hora, String ampm) {
    String[] partes = hora.split(":");
    int hh = Integer.parseInt(partes[0]);
    int mm = Integer.parseInt(partes[1]);

    if (ampm.equals("AM")) {
        if (hh == 12) hh = 0;      // 12:xx AM = 00:xx
    } else {
        if (hh != 12) hh += 12;    // 1:xx PM = 13:xx, 2:xx PM = 14:xx...
    }

    // Formatea con cero adelante si es necesario
    String hhStr = hh < 10 ? "0" + hh : "" + hh;
    String mmStr = mm < 10 ? "0" + mm : "" + mm;
    return hhStr + ":" + mmStr;
}

}