package br.com.esteves;

import br.com.esteves.dao.EstoqueDAO;
import br.com.esteves.model.Produto;
import br.com.esteves.db.ConnectionFactory;
import java.sql.*;
import java.util.List;

public class App {
  static void main() {
    System.out.println("Sistema Estoque API");
    Produto p = new Produto(1, "Computador", 3000, 1);
    System.out.println(p);
    ConnectionFactory cf = new ConnectionFactory();
    EstoqueDAO dao = new EstoqueDAO(cf);
    try (Connection conn = cf.getConnection()) {
      System.out.println("Connection OK!");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    try {
      List<Produto> produtos = dao.listarTodos();
      produtos.forEach(System.out::println);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
