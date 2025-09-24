package br.edu.ada.service;

import br.edu.ada.DTO.ProdutoRequestDTO;
import br.edu.ada.Exceptions.ProdutoNaoEncontradoException;
import br.edu.ada.DTO.Mapper.ProdutoMapper;
//import br.edu.ada.cache.SimplesKeyGeneratorNull;
import br.edu.ada.model.Produto;
import br.edu.ada.repository.ProdutoRepository;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

//    @Inject
//    @CacheName("lista-produtos")
//    Cache cache;

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

    //@CacheResult(cacheName = "lista-produtos", keyGenerator = SimplesKeyGeneratorNull.class)
    public List<Produto> getProdutos() {
        Log.info("Buscando do banco de dados");
        return produtoRepository.findAll(Sort.ascending("nome")).list();
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
