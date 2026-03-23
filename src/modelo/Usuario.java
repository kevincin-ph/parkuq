package modelo;

public class Usuario {

    // ATRIBUTOS
    private String nombre;
    private String identificacion;
    private String tipoUsuario;   // "Estudiante", "Docente", "Administrativo", "Visitante"
    private double descuento;     // Ej: 0.10 = 10% de descuento

    // CONSTRUCTOR
    public Usuario(String nombre, String identificacion, String tipoUsuario) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.tipoUsuario = tipoUsuario;
        this.descuento = asignarDescuento(tipoUsuario); // se asigna automático
    }

    // MÉTODO PRIVADO - asigna descuento según tipo
    private double asignarDescuento(String tipo) {
        if (tipo.equals("Estudiante")) {
            return 0.10;       // 10% descuento
        } else if (tipo.equals("Docente")) {
            return 0.20;       // 20% descuento
        } else if (tipo.equals("Administrativo")) {
            return 0.15;       // 15% descuento
        } else {
            return 0.0;        // Visitante, sin descuento
        }
    }

    // GETTERS
    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }
    public String getTipoUsuario() { return tipoUsuario; }
    public double getDescuento() { return descuento; }

    // SETTER
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        this.descuento = asignarDescuento(tipoUsuario); // actualiza el descuento
    }

    // MOSTRAR INFO
    @Override
    public String toString() {
        return "Usuario: " + nombre +
               " | ID: " + identificacion +
               " | Tipo: " + tipoUsuario +
               " | Descuento: " + (descuento * 100) + "%";
    }
}