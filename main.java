import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Venda> vendas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            mostrarMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    adicionarProduto(scanner);
                    break;
                case 2:
                    adicionarCliente(scanner);
                    break;
                case 3:
                    registrarVenda(scanner);
                    break;
                case 4:
                    listarVendas();
                    break;
                case 5:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opçao inválida! Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("======== MENU ========");
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Adicionar Cliente");
        System.out.println("3. Registrar Venda");
        System.out.println("4. Listar Vendas");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static void adicionarProduto(Scanner scanner) {
        System.out.print("Escreva o codigo do Produto: ");
        int id = scanner.nextInt();
        System.out.print("Nome do Produto: ");
        String nome = scanner.next();
        System.out.print("Preço do Produto: ");
        double preco = scanner.nextDouble();
        Produto produto = new Produto(id, nome, preco);
        produtos.add(produto);
        System.out.println("Produto adicionado!");
    }

    private static void adicionarCliente(Scanner scanner) {
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt();
        System.out.print("Nome do Cliente: ");
        String nome = scanner.next();
        Cliente cliente = new Cliente(id, nome);
        clientes.add(cliente);
        System.out.println("Cliente adicionado!");
    }

    private static void registrarVenda(Scanner scanner) {
        System.out.print("ID da Venda: ");
        int idVenda = scanner.nextInt();
        System.out.print("ID do Cliente: ");
        int idCliente = scanner.nextInt();

        Cliente cliente = clientes.stream().filter(c -> c.getId() == idCliente).findFirst().orElse(null);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado!");
            return;
        }

        Venda venda = new Venda(idVenda, cliente);

        while (true) {
            System.out.print("ID do Produto (0 para encerrar): ");
            int idProduto = scanner.nextInt();
            if (idProduto == 0) break;

            Produto produto = produtos.stream().filter(p -> p.getId() == idProduto).findFirst().orElse(null);

            if (produto == null) {
                System.out.println("Produto nao encontrado!");
            } else {
                venda.adicionarProduto(produto);
                System.out.println("Produto adicionado à venda!");
            }
        }

        vendas.add(venda);
        System.out.println("Venda registrada!");
    }

    private static void listarVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            vendas.forEach(System.out::println);
        }
    }
}

