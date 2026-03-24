package excepciones;

public class PlacaDuplicadaException extends Exception {
    public PlacaDuplicadaException(String placa) {
        super("La placa " + placa + " ya está registrada en el parqueadero.");
    }
}