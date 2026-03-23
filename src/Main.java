import servicio.ParqueaderoService;

public class Main {
    public static void main(String[] args) {

        // Creamos el servicio del parqueadero
        ParqueaderoService parqueadero = new ParqueaderoService();

        System.out.println("=============================");
        System.out.println("  BIENVENIDO AL PARKUQ");
        System.out.println("=============================");

        // ─── PRUEBA 1: Ver espacios disponibles al inicio ───
        System.out.println("\n--- ESPACIOS AL INICIO ---");
        parqueadero.mostrarEspaciosDisponibles();

        // ─── PRUEBA 2: Registrar ingreso de vehículos ───
        System.out.println("\n--- REGISTRANDO INGRESOS ---");
        System.out.println(parqueadero.registrarIngreso(
            "ABC123", "Carro", "Juan Pérez", "1234567", "08:00"));

        System.out.println(parqueadero.registrarIngreso(
            "XYZ789", "Moto", "María López", "7654321", "08:30"));

        System.out.println(parqueadero.registrarIngreso(
            "ABC123", "Carro", "Juan Pérez", "1234567", "09:00")); // placa duplicada

        // ─── PRUEBA 3: Ver vehículos adentro ───
        System.out.println("\n--- VEHÍCULOS ADENTRO ---");
        parqueadero.mostrarVehiculosDentro();

        // ─── PRUEBA 4: Registrar salida ───
        System.out.println("\n--- REGISTRANDO SALIDA ---");
        System.out.println(parqueadero.registrarSalida("ABC123", "10:30"));

        // ─── PRUEBA 5: Ver espacios después de la salida ───
        System.out.println("\n--- ESPACIOS DESPUÉS DE SALIDA ---");
        parqueadero.mostrarEspaciosDisponibles();

    }
}