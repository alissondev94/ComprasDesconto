import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Cliente {

    private String nome;
    private LocalDate dataRegistro;
    private int comprasRecentes;

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Cliente(String nome, LocalDate dataRegistro) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome;
        this.dataRegistro = dataRegistro;
        this.comprasRecentes = 0;
    }

    public void adicionarCompra() {
        comprasRecentes++;
    }

    public boolean temDesconto() {
        return comprasRecentes > 5;
    }

    public boolean podeComprar(double valorCompra) {
        return !dataRegistro.isAfter(LocalDate.now().minusMonths(3)) || valorCompra <= 500;
    }

    public double aplicarDesconto(double valorCompra) {
        return temDesconto() ? valorCompra * 0.90 : valorCompra;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public static LocalDate parseData(String data) throws DateTimeParseException {
        return LocalDate.parse(data, FORMATO_DATA);
    }

    public static String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA);
    }
}
