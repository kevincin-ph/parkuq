import enums.TipoVehiculo;
import servicio.ParqueaderoService;

public class Main {
    public static void main(String[] args) {

        ParqueaderoService parqueadero = new ParqueaderoService();

        System.out.println("=============================");
        System.out.println("  BIENVENIDO AL PARKUQ");
        System.out.println("=============================");

        // PRUEBA 1: Espacios al inicio
        System.out.println("\n--- ESPACIOS AL INICIO ---");
        parqueadero.mostrarEspaciosDisponibles();

        // PRUEBA 2: Ingresos con try/catch
        System.out.println("\n--- REGISTRANDO INGRESOS ---");
        try {
            System.out.println(parqueadero.registrarIngreso(
                "ABC123", TipoVehiculo.CARRO, "Juan Pérez", "1234567", "08:00"));

            System.out.println(parqueadero.registrarIngreso(
                "XYZ789", TipoVehiculo.MOTO, "María López", "7654321", "08:30"));

            // Placa duplicada - lanzará excepción
            System.out.println(parqueadero.registrarIngreso(
                "ABC123", TipoVehiculo.CARRO, "Juan Pérez", "1234567", "09:00"));

        } catch (Exception e) {
            System.out.println("EXCEPCION CAPTURADA: " + e.getMessage());
        }

        // PRUEBA 3: Vehículos adentro
        System.out.println("\n--- VEHÍCULOS ADENTRO ---");
        parqueadero.mostrarVehiculosDentro();

        // PRUEBA 4: Salida
        System.out.println("\n--- REGISTRANDO SALIDA ---");
        try {
            System.out.println(parqueadero.registrarSalida("ABC123", "10:30"));

            // Vehículo que no existe - lanzará excepción
            System.out.println(parqueadero.registrarSalida("ZZZ999", "10:30"));

        } catch (Exception e) {
            System.out.println("EXCEPCION CAPTURADA: " + e.getMessage());
        }

        // PRUEBA 5: Espacios después
        System.out.println("\n--- ESPACIOS DESPUÉS DE SALIDA ---");
        parqueadero.mostrarEspaciosDisponibles();
    }
}