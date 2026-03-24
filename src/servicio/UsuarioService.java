package servicio;

import modelo.Usuario;
import enums.TipoUsuario;
import java.util.ArrayList;

public class UsuarioService {

    private ArrayList<Usuario> usuarios;

    // CONSTRUCTOR
    public UsuarioService() {
        usuarios = new ArrayList<>();
        inicializarUsuarios();
    }

    // Usuarios de prueba al arrancar
    private void inicializarUsuarios() {
        usuarios.add(new Usuario("Carlos Pérez",   "1001", TipoUsuario.ESTUDIANTE));
        usuarios.add(new Usuario("Ana Gómez",      "1002", TipoUsuario.DOCENTE));
        usuarios.add(new Usuario("Luis Martínez",  "1003", TipoUsuario.ADMINISTRATIVO));
        usuarios.add(new Usuario("Pedro Visitante","1004", TipoUsuario.VISITANTE));
    }

    // REGISTRAR usuario nuevo
    public String registrarUsuario(String nombre, String identificacion,
                                   TipoUsuario tipoUsuario) {
        // Verificar que no exista ya
        for (Usuario u : usuarios) {
            if (u.getIdentificacion().equals(identificacion)) {
                return "ERROR: Ya existe un usuario con ID " + identificacion;
            }
        }
        usuarios.add(new Usuario(nombre, identificacion, tipoUsuario));
        return "Usuario registrado exitosamente: " + nombre;
    }

    // BUSCAR usuario por identificacion
    public Usuario buscarUsuario(String identificacion) {
        for (Usuario u : usuarios) {
            if (u.getIdentificacion().equals(identificacion)) {
                return u;
            }
        }
        return null; // no encontrado
    }

    // OBTENER descuento de un usuario
    public double obtenerDescuento(String identificacion) {
        Usuario u = buscarUsuario(identificacion);
        if (u != null) {
            return u.getDescuento();
        }
        return 0.0; // si no está registrado, sin descuento
    }

    // MOSTRAR todos los usuarios
    public void mostrarUsuarios() {
        System.out.println("\n=== Usuarios registrados ===");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    // MOSTRAR usuarios por tipo
    public void mostrarUsuariosPorTipo(TipoUsuario tipo) {
        System.out.println("\n=== Usuarios tipo: " + tipo + " ===");
        boolean hayUsuarios = false;
        for (Usuario u : usuarios) {
            if (u.getTipoUsuario() == tipo) {
                System.out.println(u);
                hayUsuarios = true;
            }
        }
        if (!hayUsuarios) {
            System.out.println("No hay usuarios de este tipo.");
        }
    }
}