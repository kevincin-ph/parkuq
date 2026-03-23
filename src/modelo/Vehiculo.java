package modelo;

public class Vehiculo {

    // ATRIBUTOS - los datos de cada vehículo
    private String placa;
    private String tipoVehiculo;
    private String nombreConductor;
    private String identificacionConductor;
    private String horaIngreso;
    private String espacioAsignado;
    private String estado;

    // CONSTRUCTOR - para crear un vehículo nuevo
    public Vehiculo(String placa, String tipoVehiculo, String nombreConductor,
                    String identificacionConductor, String horaIngreso,
                    String espacioAsignado) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.nombreConductor = nombreConductor;
        this.identificacionConductor = identificacionConductor;
        this.horaIngreso = horaIngreso;
        this.espacioAsignado = espacioAsignado;
        this.estado = "dentro";
    }

    // GETTERS
    public String getPlaca() { return placa; }
    public String getTipoVehiculo() { return tipoVehiculo; }
    public String getNombreConductor() { return nombreConductor; }
    public String getIdentificacionConductor() { return identificacionConductor; }
    public String getHoraIngreso() { return horaIngreso; }
    public String getEspacioAsignado() { return espacioAsignado; }
    public String getEstado() { return estado; }

    // SETTERS
    public void setEstado(String estado) { this.estado = estado; }
    public void setEspacioAsignado(String espacio) { this.espacioAsignado = espacio; }

    // MOSTRAR INFO
    @Override
    public String toString() {
        return "Placa: " + placa +
               " | Tipo: " + tipoVehiculo +
               " | Conductor: " + nombreConductor +
               " | Ingreso: " + horaIngreso +
               " | Espacio: " + espacioAsignado +
               " | Estado: " + estado;
    }
}
