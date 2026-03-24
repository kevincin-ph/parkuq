package excepciones;

public class SinEspaciosException extends Exception {
    public SinEspaciosException(String tipoVehiculo) {
        super("No hay espacios disponibles para: " + tipoVehiculo);
    }
}