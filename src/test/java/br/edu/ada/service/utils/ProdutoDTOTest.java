package br.edu.ada.service.utils;

import br.edu.ada.DTO.ProdutoRequestDTO;

import java.math.BigDecimal;

public class ProdutoDTOTest {

    private String nome;
    private String descricao;
    private BigDecimal preco;

    public static ProdutoRequestDTO criaProdutoDTO(String nome, BigDecimal preco){
        var produtoRequestDTO = new ProdutoRequestDTO();
        produtoRequestDTO.setNome(nome);
        produtoRequestDTO.setDescricao("");
        produtoRequestDTO.setPreco(preco);

        return produtoRequestDTO;
    }
}
