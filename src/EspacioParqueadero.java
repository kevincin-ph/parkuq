package modelo;

public class EspacioParqueadero {

    // ATRIBUTOS
    private String codigo;           // Ej: "A1", "B3"
    private String tipoEspacio;      // "Carro", "Moto", "Bicicleta"
    private String estado;           // "disponible", "ocupado", "fuera de servicio"
    private Vehiculo vehiculoAsignado; // el vehículo que está en ese espacio

    // CONSTRUCTOR
    public EspacioParqueadero(String codigo, String tipoEspacio) {
        this.codigo = codigo;
        this.tipoEspacio = tipoEspacio;
        this.estado = "disponible";      // por defecto todo espacio empieza disponible
        this.vehiculoAsignado = null;    // null = vacío, sin vehículo
    }

    // GETTERS
    public String getCodigo() { return codigo; }
    public String getTipoEspacio() { return tipoEspacio; }
    public String getEstado() { return estado; }
    public Vehiculo getVehiculoAsignado() { return vehiculoAsignado; }

    // SETTERS
    public void setEstado(String estado) { this.estado = estado; }
    public void setVehiculoAsignado(Vehiculo vehiculo) { this.vehiculoAsignado = vehiculo; }

    // MÉTODO ÚTIL - saber si está disponible
    public boolean estaDisponible() {
        return this.estado.equals("disponible");
    }

    // MOSTRAR INFO
    @Override
    public String toString() {
        return "Espacio: " + codigo +
               " | Tipo: " + tipoEspacio +
               " | Estado: " + estado;
    }
}
