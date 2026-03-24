package modelo;

import enums.TipoUsuario;

public class Usuario {

    private String nombre;
    private String identificacion;
    private TipoUsuario tipoUsuario;
    private double descuento;

    public Usuario(String nombre, String identificacion, TipoUsuario tipoUsuario) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.tipoUsuario = tipoUsuario;
        this.descuento = asignarDescuento(tipoUsuario);
    }

    private double asignarDescuento(TipoUsuario tipo) {
        if (tipo == TipoUsuario.ESTUDIANTE) {
            return 0.10;
        } else if (tipo == TipoUsuario.DOCENTE) {
            return 0.20;
        } else if (tipo == TipoUsuario.ADMINISTRATIVO) {
            return 0.15;
        } else {
            return 0.0;
        }
    }

    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public double getDescuento() { return descuento; }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        this.descuento = asignarDescuento(tipoUsuario);
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre +
               " | ID: " + identificacion +
               " | Tipo: " + tipoUsuario +
               " | Descuento: " + (descuento * 100) + "%";
    }
}