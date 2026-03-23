package servicio;

import modelo.Vehiculo;
import modelo.EspacioParqueadero;
import modelo.Usuario;
import modelo.Tarifa;
import java.util.ArrayList;

public class ParqueaderoService {

    // LISTAS - aquí se guardan todos los datos del parqueadero
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<EspacioParqueadero> espacios;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Tarifa> tarifas;

    // CONSTRUCTOR - inicializa las listas y carga datos base
    public ParqueaderoService() {
        vehiculos = new ArrayList<>();
        espacios  = new ArrayList<>();
        usuarios  = new ArrayList<>();
        tarifas   = new ArrayList<>();
        inicializarEspacios();
        inicializarTarifas();
    }

    // Crea los espacios del parqueadero al arrancar
    private void inicializarEspacios() {
        espacios.add(new EspacioParqueadero("A1", "Carro"));
        espacios.add(new EspacioParqueadero("A2", "Carro"));
        espacios.add(new EspacioParqueadero("A3", "Carro"));
        espacios.add(new EspacioParqueadero("B1", "Moto"));
        espacios.add(new EspacioParqueadero("B2", "Moto"));
        espacios.add(new EspacioParqueadero("C1", "Bicicleta"));
        espacios.add(new EspacioParqueadero("C2", "Bicicleta"));
    }

    // Crea las tarifas base al arrancar
    private void inicializarTarifas() {
        tarifas.add(new Tarifa("Carro",      3000.0));
        tarifas.add(new Tarifa("Moto",       2000.0));
        tarifas.add(new Tarifa("Bicicleta",  500.0));
    }

    // ─── REGISTRAR INGRESO ───────────────────────────────────────
    public String registrarIngreso(String placa, String tipoVehiculo,
                                   String nombreConductor, String identificacion,
                                   String horaIngreso) {
        // 1. Verificar que la placa no esté ya adentro
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa) && v.getEstado().equals("dentro")) {
                return "ERROR: La placa " + placa + " ya está en el parqueadero.";
            }
        }

        // 2. Buscar un espacio disponible para ese tipo de vehículo
        EspacioParqueadero espacioLibre = null;
        for (EspacioParqueadero e : espacios) {
            if (e.getTipoEspacio().equals(tipoVehiculo) && e.estaDisponible()) {
                espacioLibre = e;
                break;
            }
        }

        // 3. Si no hay espacio, avisamos
        if (espacioLibre == null) {
            return "ERROR: No hay espacios disponibles para " + tipoVehiculo;
        }

        // 4. Registrar el vehículo y ocupar el espacio
        Vehiculo nuevo = new Vehiculo(placa, tipoVehiculo, nombreConductor,
                                      identificacion, horaIngreso,
                                      espacioLibre.getCodigo());
        vehiculos.add(nuevo);
        espacioLibre.setEstado("ocupado");
        espacioLibre.setVehiculoAsignado(nuevo);

        return "Ingreso exitoso. Espacio asignado: " + espacioLibre.getCodigo();
    }

    // ─── REGISTRAR SALIDA ────────────────────────────────────────
    public String registrarSalida(String placa, String horaSalida) {
        // 1. Buscar el vehículo adentro
        Vehiculo vehiculoEncontrado = null;
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa) && v.getEstado().equals("dentro")) {
                vehiculoEncontrado = v;
                break;
            }
        }

        // 2. Si no existe, avisamos
        if (vehiculoEncontrado == null) {
            return "ERROR: No se encontró el vehículo con placa " + placa;
        }

        // 3. Calcular horas (simple por ahora)
        double horas = calcularHoras(vehiculoEncontrado.getHoraIngreso(), horaSalida);

        // 4. Buscar tarifa según tipo de vehículo
        Tarifa tarifa = buscarTarifa(vehiculoEncontrado.getTipoVehiculo());
        double total = tarifa.calcularCobro(horas, 0.0);

        // 5. Liberar el espacio
        for (EspacioParqueadero e : espacios) {
            if (e.getCodigo().equals(vehiculoEncontrado.getEspacioAsignado())) {
                e.setEstado("disponible");
                e.setVehiculoAsignado(null);
                break;
            }
        }

        // 6. Marcar vehículo como salido
        vehiculoEncontrado.setEstado("salio");

        return "Salida registrada." +
               "\nTiempo: " + horas + " horas" +
               "\nTotal a pagar: $" + total;
    }

    // ─── MÉTODOS DE APOYO ────────────────────────────────────────
    private double calcularHoras(String horaIngreso, String horaSalida) {
        // Formato esperado: "HH:MM"
        String[] inicio = horaIngreso.split(":");
        String[] fin    = horaSalida.split(":");
        int minInicio = Integer.parseInt(inicio[0]) * 60 + Integer.parseInt(inicio[1]);
        int minFin    = Integer.parseInt(fin[0])    * 60 + Integer.parseInt(fin[1]);
        double minutos = minFin - minInicio;
        return Math.round((minutos / 60.0) * 10.0) / 10.0; // redondea a 1 decimal
    }

    private Tarifa buscarTarifa(String tipoVehiculo) {
        for (Tarifa t : tarifas) {
            if (t.getTipoVehiculo().equals(tipoVehiculo)) {
                return t;
            }
        }
        return new Tarifa(tipoVehiculo, 0.0);
    }

    // ─── CONSULTAS ───────────────────────────────────────────────
    public void mostrarVehiculosDentro() {
        System.out.println("\n=== Vehículos en el parqueadero ===");
        boolean hayVehiculos = false;
        for (Vehiculo v : vehiculos) {
            if (v.getEstado().equals("dentro")) {
                System.out.println(v);
                hayVehiculos = true;
            }
        }
        if (!hayVehiculos) System.out.println("No hay vehículos en este momento.");
    }

    public void mostrarEspaciosDisponibles() {
        System.out.println("\n=== Espacios disponibles ===");
        for (EspacioParqueadero e : espacios) {
            if (e.estaDisponible()) {
                System.out.println(e);
            }
        }
    }
}