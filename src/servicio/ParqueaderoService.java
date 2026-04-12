package servicio;

import enums.TipoEspacio;
import enums.TipoVehiculo;
import excepciones.PlacaDuplicadaException;
import excepciones.SinEspaciosException;
import excepciones.VehiculoNoEncontradoException;
import java.util.ArrayList;
import modelo.EspacioParqueadero;
import modelo.Tarifa;
import modelo.Vehiculo;

public class ParqueaderoService {

    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<EspacioParqueadero> espacios;
    private ArrayList<Tarifa> tarifas;

    public ParqueaderoService() {
        vehiculos = new ArrayList<>();
        espacios  = new ArrayList<>();
        tarifas   = new ArrayList<>();
        inicializarEspacios();
        inicializarTarifas();
    }

    private void inicializarEspacios() {
        espacios.add(new EspacioParqueadero("A1", TipoEspacio.CARRO));
        espacios.add(new EspacioParqueadero("A2", TipoEspacio.CARRO));
        espacios.add(new EspacioParqueadero("A3", TipoEspacio.CARRO));
        espacios.add(new EspacioParqueadero("B1", TipoEspacio.MOTO));
        espacios.add(new EspacioParqueadero("B2", TipoEspacio.MOTO));
        espacios.add(new EspacioParqueadero("C1", TipoEspacio.BICICLETA));
        espacios.add(new EspacioParqueadero("C2", TipoEspacio.BICICLETA));
    }

    private void inicializarTarifas() {
        tarifas.add(new Tarifa("CARRO",     3000.0));
        tarifas.add(new Tarifa("MOTO",      2000.0));
        tarifas.add(new Tarifa("BICICLETA", 500.0));
    }

    public String registrarIngreso(String placa, TipoVehiculo tipoVehiculo,
                                   String nombreConductor, String identificacion,
                                   String horaIngreso)
            throws PlacaDuplicadaException, SinEspaciosException {

        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa) && v.getEstado().equals("dentro")) {
                throw new PlacaDuplicadaException(placa);
            }
        }

        EspacioParqueadero espacioLibre = null;
        for (EspacioParqueadero e : espacios) {
            if (e.getTipoEspacio().toString().equals(tipoVehiculo.toString())
                    && e.estaDisponible()) {
                espacioLibre = e;
                break;
            }
        }

        if (espacioLibre == null) {
            throw new SinEspaciosException(tipoVehiculo.toString());
        }

        Vehiculo nuevo = new Vehiculo(placa, tipoVehiculo, nombreConductor,
                                      identificacion, horaIngreso,
                                      espacioLibre.getCodigo());
        vehiculos.add(nuevo);
        espacioLibre.setEstado("ocupado");
        espacioLibre.setVehiculoAsignado(nuevo);

        return "Ingreso exitoso. Espacio asignado: " + espacioLibre.getCodigo();
    }

    public String registrarSalida(String placa, String horaSalida)
            throws VehiculoNoEncontradoException {

        Vehiculo vehiculoEncontrado = null;
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa) && v.getEstado().equals("dentro")) {
                vehiculoEncontrado = v;
                break;
            }
        }

        if (vehiculoEncontrado == null) {
            throw new VehiculoNoEncontradoException(placa);
        }

        double horas = calcularHoras(vehiculoEncontrado.getHoraIngreso(), horaSalida);
        Tarifa tarifa = buscarTarifa(vehiculoEncontrado.getTipoVehiculo().toString());
        double total = tarifa.calcularCobro(horas, 0.0);

        for (EspacioParqueadero e : espacios) {
            if (e.getCodigo().equals(vehiculoEncontrado.getEspacioAsignado())) {
                e.setEstado("disponible");
                e.setVehiculoAsignado(null);
                break;
            }
        }

        vehiculoEncontrado.setEstado("salio");

        return "Salida registrada." +
               "\nTiempo: " + horas + " horas" +
               "\nTotal a pagar: $" + total;
    }

    private double calcularHoras(String horaIngreso, String horaSalida) {
        String[] inicio = horaIngreso.split(":");
        String[] fin    = horaSalida.split(":");
        int minInicio = Integer.parseInt(inicio[0]) * 60 + Integer.parseInt(inicio[1]);
        int minFin    = Integer.parseInt(fin[0])    * 60 + Integer.parseInt(fin[1]);
        if (minFin < minInicio) minFin += 24 * 60;
        double minutos = minFin - minInicio;
        return Math.round((minutos / 60.0) * 10.0) / 10.0;
    }

    private Tarifa buscarTarifa(String tipoVehiculo) {
        for (Tarifa t : tarifas) {
            if (t.getTipoVehiculo().equals(tipoVehiculo)) {
                return t;
            }
        }
        return new Tarifa(tipoVehiculo, 0.0);
    }

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

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<EspacioParqueadero> getEspacios() {
        return espacios;
    }
}