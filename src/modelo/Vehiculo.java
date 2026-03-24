package modelo;

import enums.TipoVehiculo;

public class Vehiculo {

    private String placa;
    private TipoVehiculo tipoVehiculo;
    private String nombreConductor;
    private String identificacionConductor;
    private String horaIngreso;
    private String espacioAsignado;
    private String estado;

    public Vehiculo(String placa, TipoVehiculo tipoVehiculo, String nombreConductor,
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

    public String getPlaca() { return placa; }
    public TipoVehiculo getTipoVehiculo() { return tipoVehiculo; }
    public String getNombreConductor() { return nombreConductor; }
    public String getIdentificacionConductor() { return identificacionConductor; }
    public String getHoraIngreso() { return horaIngreso; }
    public String getEspacioAsignado() { return espacioAsignado; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
    public void setEspacioAsignado(String espacio) { this.espacioAsignado = espacio; }

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