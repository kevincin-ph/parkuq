package modelo;

public class Tarifa {

    // ATRIBUTOS
    private String tipoVehiculo;    // "Carro", "Moto", "Bicicleta"
    private double valorPorHora;    // Ej: 3000.0 pesos por hora

    // CONSTRUCTOR
    public Tarifa(String tipoVehiculo, double valorPorHora) {
        this.tipoVehiculo = tipoVehiculo;
        this.valorPorHora = valorPorHora;
    }

    // GETTERS
    public String getTipoVehiculo() { return tipoVehiculo; }
    public double getValorPorHora() { return valorPorHora; }

    // SETTER
    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    // MÉTODO CLAVE - calcula cuánto debe pagar un vehículo
    public double calcularCobro(double horas, double descuento) {
        double total = valorPorHora * horas;
        double totalConDescuento = total - (total * descuento);
        return totalConDescuento;
    }

    // MOSTRAR INFO
    @Override
    public String toString() {
        return "Tipo: " + tipoVehiculo +
               " | Valor por hora: $" + valorPorHora;
    }
}