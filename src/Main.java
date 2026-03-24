import enums.TipoUsuario;
import modelo.Usuario;
import servicio.ParqueaderoService;
import servicio.UsuarioService;


public class Main {
    public static void main(String[] args) {

        ParqueaderoService parqueadero = new ParqueaderoService();
// PRUEBA UsuarioService
        System.out.println("\n=============================");
        System.out.println("  PRUEBA USUARIOS");
        System.out.println("=============================");

        UsuarioService usuarioService = new UsuarioService();

        // Mostrar todos
        usuarioService.mostrarUsuarios();

        // Buscar uno específico
        System.out.println("\n--- BUSCANDO USUARIO 1002 ---");
        Usuario u = usuarioService.buscarUsuario("1002");
        if (u != null) {
            System.out.println("Encontrado: " + u);
            System.out.println("Su descuento es: " + (u.getDescuento() * 100) + "%");
        }

        // Registrar uno nuevo
        System.out.println("\n--- REGISTRANDO NUEVO USUARIO ---");
        System.out.println(usuarioService.registrarUsuario(
            "Kevin Dev", "9999", TipoUsuario.ESTUDIANTE));

        // Intentar duplicado
        System.out.println(usuarioService.registrarUsuario(
            "Otro Kevin", "9999", TipoUsuario.DOCENTE));

        // Ver solo docentes
        usuarioService.mostrarUsuariosPorTipo(TipoUsuario.DOCENTE);
    }
}