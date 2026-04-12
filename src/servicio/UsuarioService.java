package servicio;

import enums.TipoUsuario;
import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioService {

    private ArrayList<Usuario> usuarios;

    public UsuarioService() {
        usuarios = new ArrayList<>();
        inicializarUsuarios();
    }

    private void inicializarUsuarios() {
        usuarios.add(new Usuario("Carlos Pérez",    "1001", TipoUsuario.ESTUDIANTE));
        usuarios.add(new Usuario("Ana Gómez",       "1002", TipoUsuario.DOCENTE));
        usuarios.add(new Usuario("Luis Martínez",   "1003", TipoUsuario.ADMINISTRATIVO));
        usuarios.add(new Usuario("Pedro Visitante", "1004", TipoUsuario.VISITANTE));
    }

    public String registrarUsuario(String nombre, String identificacion,
                                   TipoUsuario tipoUsuario) {
        for (Usuario u : usuarios) {
            if (u.getIdentificacion().equals(identificacion)) {
                return "ERROR: Ya existe un usuario con ID " + identificacion;
            }
        }
        usuarios.add(new Usuario(nombre, identificacion, tipoUsuario));
        return "Usuario registrado exitosamente: " + nombre;
    }

    public Usuario buscarUsuario(String identificacion) {
        for (Usuario u : usuarios) {
            if (u.getIdentificacion().equals(identificacion)) {
                return u;
            }
        }
        return null;
    }

    public double obtenerDescuento(String identificacion) {
        Usuario u = buscarUsuario(identificacion);
        if (u != null) {
            return u.getDescuento();
        }
        return 0.0;
    }

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

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}