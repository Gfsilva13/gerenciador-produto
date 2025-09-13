package br.edu.ada.service;

import br.edu.ada.DTO.ProdutoRequestDTO;
import br.edu.ada.Exceptions.ProdutoErroValidacaoException;
import br.edu.ada.Exceptions.ProdutoNaoEncontradoException;
import br.edu.ada.DTO.Mapper.ProdutoMapper;
import br.edu.ada.model.Produto;
import br.edu.ada.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Transactional
    public void cadastraProduto(ProdutoRequestDTO produtoRequestDTO){
      Produto produto = produtoMapper.toEntity(produtoRequestDTO);
      produtoRepository.persist(produto);
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll().list();
    }

    public Object getProduto(Long id){
        var produtoEncontrado = produtoRepository.findByIdOptional(id);
        return produtoEncontrado.orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @Transactional
    public void alterarProduto(Long id, ProdutoRequestDTO produtoRequestDTO) {
        var produtoCadastrado = produtoRepository.findByIdOptional(id)
                .orElseThrow(()-> new ProdutoNaoEncontradoException(id));
        produtoMapper.updateEntityFromDTO(produtoRequestDTO, produtoCadastrado);
    }

    @Transactional
    public void excluirProduto(Long id) {
        produtoRepository.findByIdOptional(id)
                .orElseThrow(()-> new ProdutoNaoEncontradoException(id));
        produtoRepository.deleteById(id);
    }
}
