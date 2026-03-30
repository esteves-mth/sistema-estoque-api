package br.com.esteves.dao;

import br.com.esteves.db.ConnectionFactory;
import br.com.esteves.model.Produto;

import java.sql.*;
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

  public Produto buscarPorID(int id) throws SQLException {
    String sql = "select * from produtos where id = ?";
    try {
      Connection conn = cf.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return new Produto(
            rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getInt("quantidade"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public void inserir(Produto produto) throws SQLException {
    try {
      Connection conn = cf.getConnection();
      PreparedStatement pstmt =
          conn.prepareStatement(
              "insert into produtos (nome, preco, quantidade) values (?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS);

      pstmt.setString(1, produto.getNome());
      pstmt.setDouble(2, produto.getPreco());
      pstmt.setInt(3, produto.getQuantidade());

      pstmt.executeUpdate();
      ResultSet generatedKeys = pstmt.getGeneratedKeys();

      if (generatedKeys.next()) {
        produto.setId(generatedKeys.getInt(1));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
