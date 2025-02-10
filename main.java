import java.util.ArrayList;
import java.util.Scanner;

// Classe principal do programa
public class Main {

    // Declaração de listas para armazenar produtos, clientes e vendas
    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Venda> vendas = new ArrayList<>();

    // Método principal que inicia a execução do programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Objeto Scanner para ler a entrada do usuário
        int opcao;

        do {
            mostrarMenu(); // Exibe o menu de opções
            opcao = scanner.nextInt(); // Lê a opção selecionada pelo usuário

            switch (opcao) {
                case 1:
                    adicionarProduto(scanner); // Chama o método para adicionar um produto
                    break;
                case 2:
                    adicionarCliente(scanner); // Chama o método para adicionar um cliente
                    break;
                case 3:
                    registrarVenda(scanner); // Chama o método para registrar uma venda
                    break;
                case 4:
                    listarVendas(); // Chama o método para listar as vendas registradas
                    break;
                case 5:
                    System.out.println("Saindo"); // Mensagem de saída do programa
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente."); // Mensagem para opção inválida
            }
        } while (opcao != 5); // Continua no loop enquanto a opção selecionada não for 5

        scanner.close(); // Fecha o scanner para liberar recursos
    }

    // Método que exibe o menu de opções para o usuário
    private static void mostrarMenu() {
        System.out.println("======== MENU ========");
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Adicionar Cliente");
        System.out.println("3. Registrar Venda");
        System.out.println("4. Listar Vendas");
        System.out.println("5. Sair");
        System.out.print("Escolha uma Opção: ");
    }

    // Método para adicionar um novo produto à lista de produtos
    private static void adicionarProduto(Scanner scanner) {
        System.out.print("Escreva o código do Produto: ");
        int id = scanner.nextInt(); // Lê o ID do produto

        // Verifica se o ID já existe na lista de produtos
        boolean idExiste = false;
        for (Produto p : produtos) {
            if (p.getId() == id) {
                idExiste = true;
                break;
            }
        }

        if (idExiste) {
            System.out.println("ID já existente! Por favor, insira um ID único.");
            return; // Sai do método se o ID já estiver em uso
        }

        System.out.print("Nome do Produto: ");
        String nome = scanner.next(); // Lê o nome do produto
        System.out.print("Preço do Produto: ");
        double preco = scanner.nextDouble(); // Lê o preço do produto

        // Cria um novo objeto Produto com os dados fornecidos
        Produto produto = new Produto(id, nome, preco);

        produtos.add(produto); // Adiciona o produto à lista de produtos
        System.out.println("Produto adicionado!");
    }

    // Método para adicionar um novo cliente à lista de clientes
    private static void adicionarCliente(Scanner scanner) {
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt(); // Lê o ID do cliente

        // Verifica se o ID já existe na lista de clientes
        boolean idExiste = false;
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                idExiste = true;
                break;
            }
        }

        if (idExiste) {
            System.out.println("ID já existente! Por favor, insira um ID único.");
            return; // Sai do método se o ID já estiver em uso
        }

        System.out.print("Nome do Cliente: ");
        String nome = scanner.next(); // Lê o nome do cliente

        // Cria um novo objeto Cliente com os dados fornecidos
        Cliente cliente = new Cliente(id, nome);

        clientes.add(cliente); // Adiciona o cliente à lista de clientes
        System.out.println("Cliente adicionado!");
    }

    // Método para registrar uma nova venda
    private static void registrarVenda(Scanner scanner) {
        System.out.print("ID da Venda: ");
        int idVenda = scanner.nextInt(); // Lê o ID da venda

        // Verifica se o ID já existe na lista de vendas
        boolean idExiste = false;
        for (Venda v : vendas) {
            if (v.getId() == idVenda) {
                idExiste = true;
                break;
            }
        }

        if (idExiste) {
            System.out.println("ID de venda já existente! Por favor, insira um ID único.");
            return; // Sai do método se o ID já estiver em uso
        }

        System.out.print("ID do Cliente: ");
        int idCliente = scanner.nextInt(); // Lê o ID do cliente que está comprando

        // Busca o cliente na lista de clientes pelo ID
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getId() == idCliente) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Cliente não encontrado!"); // Informa se o cliente não existe
            return; // Encerra o método se o cliente não for encontrado
        }

        // Cria um novo objeto Venda associando o cliente
        Venda venda = new Venda(idVenda, cliente);

        while (true) {
            System.out.print("ID do Produto (0 para encerrar): ");
            int idProduto = scanner.nextInt(); // Lê o ID do produto a ser adicionado à venda

            if (idProduto == 0)
                break; // Encerra a adição de produtos se o usuário digitar 0

            // Busca o produto na lista de produtos pelo ID
            Produto produto = null;
            for (Produto p : produtos) {
                if (p.getId() == idProduto) {
                    produto = p;
                    break;
                }
            }

            if (produto == null) {
                System.out.println("Produto não encontrado!"); // Informa se o produto não existe
            } else {
                venda.adicionarProduto(produto); // Adiciona o produto à venda
                System.out.println("Produto adicionado à venda!");
            }
        }

        vendas.add(venda); // Adiciona a venda à lista de vendas
        System.out.println("Venda registrada!");
    }

    // Método para listar todas as vendas registradas
    private static void listarVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada."); // Informa se não há vendas
        } else {
            for (Venda v : vendas) {
                System.out.println(v); // Imprime cada venda registrada
            }
        }
    }
}
