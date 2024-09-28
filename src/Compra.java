public class Compra {

    private Cliente cliente;
    private double valorCompra;

    public Compra(Cliente cliente, double valorCompra) {
        this.cliente = cliente;
        this.valorCompra = valorCompra;
    }

    public double processarCompra() {
        if (!cliente.podeComprar(valorCompra)) {
            System.out.println("Compra recusada. O valor excede o limite de crédito para novos clientes.");
            return 0;
        }

        double valorComDesconto = cliente.aplicarDesconto(valorCompra);
        imprimirDetalhesCompra(valorComDesconto);

        return valorComDesconto;
    }

    private void imprimirDetalhesCompra(double valorComDesconto) {
        if (cliente.temDesconto()) {
            System.out.println("Desconto de 10% aplicado. Valor original: R$ " + valorCompra +
                    ", valor com desconto: R$ " + valorComDesconto);
        } else {
            System.out.println("Nenhum desconto aplicado. Valor da compra: R$ " + valorCompra);
        }
        System.out.println("Compra processada com sucesso para o cliente: " + cliente.getNome() +
                ". Valor final da compra: R$ " + valorComDesconto);
        System.out.println(" ");
    }
}
