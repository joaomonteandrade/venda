import java.util.ArrayList;

public class Venda {
    private int id;
    private Cliente cliente;
    private ArrayList<Produto> produtos;

    public Venda(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
   public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Código da Venda: ").append(id).append("\n");
    sb.append("Cliente: ").append(cliente.getNome()).append("\n");
    sb.append("Produtos:\n");
    for (Produto produto : produtos) {
        sb.append(" - ").append(produto.getNome())
          .append(" (ID: ").append(produto.getId())
          .append(", Preço: R$ ").append(produto.getPreco()).append(")\n");
    }
    return sb.toString();
}
    }
