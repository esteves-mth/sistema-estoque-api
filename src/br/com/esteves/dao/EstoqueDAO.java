package br.com.esteves.dao;

import br.com.esteves.db.ConnectionFactory;
import br.com.esteves.model.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
  private final ConnectionFactory cf;

  public EstoqueDAO(ConnectionFactory cf) {
    this.cf = cf;
  }

  public List<Produto> listarTodos() throws SQLException {
    List<Produto> list = new ArrayList<>();
    try {
      Connection conn = cf.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from produtos");
      while (rs.next()) {
        Produto p =
            new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDouble("preco"),
                rs.getInt("quantidade"));
        list.add(p);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }
}
