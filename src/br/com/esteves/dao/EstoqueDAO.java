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
    String sql = "select * from produtos";
    try (Connection conn = cf.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql); ) {
      while (rs.next()) {
        Produto p =
                new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"));
        list.add(p);
      }
      return list;
    }
  }

  public Produto buscarPorID(int id) throws SQLException {
    String sql = "select * from produtos where id = ?";
    try (Connection conn = cf.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql); ) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery(); ) {
        if (rs.next()) {
          return new Produto(
                  rs.getInt("id"),
                  rs.getString("nome"),
                  rs.getDouble("preco"),
                  rs.getInt("quantidade"));
        }
        return null;
      }
    }
  }

  public void inserir(Produto produto) throws SQLException {
    String sql = "insert into produtos (nome, preco, quantidade) values (?, ?, ?)";
    try (Connection conn = cf.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); ) {
      pstmt.setString(1, produto.getNome());
      pstmt.setDouble(2, produto.getPreco());
      pstmt.setInt(3, produto.getQuantidade());

      pstmt.executeUpdate();
      try (ResultSet generatedKeys = pstmt.getGeneratedKeys(); ) {
        if (generatedKeys.next()) {
          produto.setId(generatedKeys.getInt(1));
        }
      }
    }
  }

  public boolean atualizar(Produto produto) throws SQLException {
    String sql = "update produtos set nome = ?, preco = ?, quantidade = ? where id = ?";
    try (Connection conn = cf.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, produto.getNome());
      pstmt.setDouble(2, produto.getPreco());
      pstmt.setInt(3, produto.getQuantidade());
      pstmt.setInt(4, produto.getId());
      int rows = pstmt.executeUpdate();
      return rows > 0;
    }
  }

  public boolean deletar(int id) throws SQLException {
    String sql = "delete from produtos where id = ?";
    try (Connection conn = cf.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql); ) {
      pstmt.setInt(1, id);
      int rows = pstmt.executeUpdate();
      return rows > 0;
    }
  }
}
