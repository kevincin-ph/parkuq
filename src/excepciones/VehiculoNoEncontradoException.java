package excepciones;

public class VehiculoNoEncontradoException extends Exception {
    public VehiculoNoEncontradoException(String placa) {
        super("No se encontró ningún vehículo con placa: " + placa);
    }
}
