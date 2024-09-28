import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        LocalDate dataRegistro = solicitarData(scanner, "Digite a data de registro do cliente (dd/MM/yyyy): ");
        Cliente cliente = new Cliente(nome, dataRegistro);


        boolean continuarComprando = true;
        while (continuarComprando) {
            System.out.print("Deseja iniciar uma nova compra? (s/n): ");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                cliente.adicionarCompra(); // Adiciona uma compra ao histórico

                System.out.print("Digite o valor da compra: R$ ");
                double valorCompra = lerValorCompra(scanner);

                Compra compra = new Compra(cliente, valorCompra);
                compra.processarCompra();
            } else if (resposta.equalsIgnoreCase("n")) {
                continuarComprando = false;
                System.out.println("Finalizando o processo de compras.");
            } else {
                System.out.println("Opção inválida. Digite 's' para Sim ou 'n' para Não.");
            }
        }

        scanner.close();
    }

    private static LocalDate solicitarData(Scanner scanner, String mensagem) {
        LocalDate data = null;
        while (data == null) {
            System.out.print(mensagem);
            String inputData = scanner.nextLine();
            try {
                data = Cliente.parseData(inputData);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
            }
        }
        return data;
    }

    private static double lerValorCompra(Scanner scanner) {
        double valor = -1;
        while (valor < 0) {
            try {
                valor = Double.parseDouble(scanner.nextLine());
                if (valor < 0) {
                    System.out.print("Valor inválido. Digite um valor positivo: R$ ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número: R$ ");
            }
        }
        return valor;
    }
}
