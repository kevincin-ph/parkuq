package modelo;

import enums.TipoEspacio;

public class EspacioParqueadero {

    private String codigo;
    private TipoEspacio tipoEspacio;
    private String estado;
    private Vehiculo vehiculoAsignado;

    public EspacioParqueadero(String codigo, TipoEspacio tipoEspacio) {
        this.codigo = codigo;
        this.tipoEspacio = tipoEspacio;
        this.estado = "disponible";
        this.vehiculoAsignado = null;
    }

    public String getCodigo() { return codigo; }
    public TipoEspacio getTipoEspacio() { return tipoEspacio; }
    public String getEstado() { return estado; }
    public Vehiculo getVehiculoAsignado() { return vehiculoAsignado; }

    public void setEstado(String estado) { this.estado = estado; }
    public void setVehiculoAsignado(Vehiculo vehiculo) { this.vehiculoAsignado = vehiculo; }

    public boolean estaDisponible() {
        return this.estado.equals("disponible");
    }

    @Override
    public String toString() {
        return "Espacio: " + codigo +
               " | Tipo: " + tipoEspacio +
               " | Estado: " + estado;
    }
}
