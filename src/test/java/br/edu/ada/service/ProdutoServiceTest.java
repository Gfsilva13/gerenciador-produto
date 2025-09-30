package br.edu.ada.service;

import br.edu.ada.DTO.ProdutoRequestDTO;
import br.edu.ada.model.Produto;
import br.edu.ada.repository.ProdutoRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static br.edu.ada.service.utils.ProdutoDTOTest.criaProdutoDTO;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
public class ProdutoServiceTest {

    @InjectMock
    private ProdutoRepository produtoRepository;

    @Inject
    private ProdutoService produtoService;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        Mockito.doNothing().when(produtoRepository).persist(Mockito.any(Produto.class));
    }

    @Test
    public void deveCadastrarProdutoValido(){
        produtoService.cadastraProduto(criaProdutoDTO("Teste", BigDecimal.valueOf(10.65)));
        Mockito.verify(produtoRepository, Mockito.times(1))
                .persist(Mockito.any(Produto.class));
    }

    @Test
    public void deveNaoCadastrarNomeVazio() {
        var produto = new Produto();
        ProdutoRequestDTO produtoRequestDTO = criaProdutoDTO(" ", BigDecimal.valueOf(10.65));
        produtoService.cadastraProduto(produtoRequestDTO);
        Mockito.verify(produtoRepository, Mockito.never()).persist(produto);
    }

    @Test
    public void deveNaoCadastrarPrecoNegativo(){
        var produto = new Produto();
        ProdutoRequestDTO produtoRequestDTO = criaProdutoDTO("Teste", BigDecimal.valueOf(-10.65));
        produtoService.cadastraProduto(produtoRequestDTO);
        Mockito.verify(produtoRepository, Mockito.never()).persist(produto);
    }

    @Test
    public void deveNaoCadastrarPrecoZero(){
        var produto = new Produto();
        ProdutoRequestDTO produtoRequestDTO = criaProdutoDTO("Teste", BigDecimal.valueOf(0));
        produtoService.cadastraProduto(produtoRequestDTO);
        Mockito.verify(produtoRepository, Mockito.never()).persist(produto);
    }

    @Test
    public void deveRetornarListaDeProdutos(){

    }

    @Test
    public void deveRetornarProdutoPeloId(){

    }

    @Test
    public void deveRetornarMensagemBuscaProdutoInexistente(){

    }

    @Test
    public void deveAlterarProdutoExistente(){

    }

    @Test
    public void deveNaoAlterarProdutoInexistente(){

    }

    @Test
    public void deveNaoAlterarNomeVazio(){

    }

    @Test
    public void deveNaoAlterarNomeInvalido(){

    }

    @Test
    public void deveNaoAlterarPrecoNegativo(){

    }

    @Test
    public void deveNaoAlterarPreçoZero(){

    }

    @Test
    public void deveNaoAlterarPreçoInvalido(){

    }

    @Test
    public void deveExcluirProdutoExistente(){

    }

    @Test
    public void deveNaoExcluirProdutoInexistente(){

    }

    @Test
    public void deveBuscarPorId() {

    }


}
