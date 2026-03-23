package br.com.esteves;

import br.com.esteves.model.Produto;

public class App {
  static void main() {
    System.out.println("Sistema Estoque API");
    Produto p = new Produto(1, "Computador", 3000, 1);
    System.out.println(p);
  }
}
