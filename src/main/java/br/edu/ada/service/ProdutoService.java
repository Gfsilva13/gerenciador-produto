package br.edu.ada.service;

import br.edu.ada.DTO.ProdutoRequestDTO;
import br.edu.ada.Exceptions.ProdutoNaoEncontradoException;
import br.edu.ada.DTO.Mapper.ProdutoMapper;
import br.edu.ada.cache.SimplesKeyGeneratorNull;
import br.edu.ada.model.Produto;
import br.edu.ada.repository.ProdutoRepository;
import io.quarkus.cache.*;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    @CacheName("lista-produto-id")
    Cache cache1;

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
        Log.info("Buscando do banco de dados lista produtos");
        return produtoRepository.findAll(Sort.ascending("nome")).list();
    }

    @CacheResult(cacheName = "lista-produto-id", keyGenerator = SimplesKeyGeneratorNull.class)
    public Object getProduto(Long id){
        Log.info("Buscando do banco de dados produto id : "+id);
        var produtoEncontrado = produtoRepository.findByIdOptional(id);
        return produtoEncontrado.orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @Transactional
    @CacheInvalidateAll(cacheName = "lista-produto-id")
    public void alterarProduto(Long id, ProdutoRequestDTO produtoRequestDTO) {
        var produtoCadastrado = produtoRepository.findByIdOptional(id)
                .orElseThrow(()-> new ProdutoNaoEncontradoException(id));
        produtoMapper.updateEntityFromDTO(produtoRequestDTO, produtoCadastrado);

    }

    @Transactional
    @CacheInvalidateAll(cacheName = "lista-produto-id")
    public void excluirProduto(Long id) {
        produtoRepository.findByIdOptional(id)
                .orElseThrow(()-> new ProdutoNaoEncontradoException(id));
        produtoRepository.deleteById(id);
    }
}
