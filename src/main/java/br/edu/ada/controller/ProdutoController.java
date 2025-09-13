package br.edu.ada.controller;

import br.edu.ada.DTO.ProdutoRequestDTO;
import br.edu.ada.Exceptions.ProdutoErroValidacaoException;
import br.edu.ada.model.Produto;
import br.edu.ada.service.ProdutoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cadastrar novo produto",
            description = "Cadastra novo produto")
    public Response cadastrarProduto(@Valid ProdutoRequestDTO produtoRequestDTO){
        try {
            produtoService.cadastraProduto(produtoRequestDTO);
            return Response.status(Response.Status.CREATED).
                    entity("Produto Cadastrado com sucesso").build();
        } catch (ConstraintViolationException e) {
            StringBuilder mensagens = new StringBuilder();
            for (ConstraintViolation<?> violacao : e.getConstraintViolations()) {
                mensagens.append(violacao.getMessage()).append("\n");
            }
            throw new ProdutoErroValidacaoException(mensagens.toString());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Listar todos produtos cadastrados",
            description = "Retorna uma lista de produtos cadastrados")
    public List<Produto> buscarProdutos() {
        return this.produtoService.getProdutos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Operation(summary = "Buscar produto pelo 'id'",
            description = "Retorna um produto com o 'id' informado")
    public Response buscarProdutoPorId(@RestPath Long id) {
        return Response.ok(produtoService.getProduto(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Alterar produto cadastrado",
            description = "Altera dados do produto com id informado")
    @Path ("{id}")
    public Response alterarProduto(@RestPath Long id, @Valid ProdutoRequestDTO produtoRequestDTO){
        try {
            this.produtoService.alterarProduto(id, produtoRequestDTO);
            return Response.status(Response.Status.OK)
                    .entity("Produto alterado com sucesso").build();
        }
        catch (ConstraintViolationException e) {
            StringBuilder mensagens = new StringBuilder();
            for (ConstraintViolation<?> violacao : e.getConstraintViolations()) {
                mensagens.append(violacao.getMessage()).append("\n");
            }
            throw new ProdutoErroValidacaoException(mensagens.toString());
        }
    }

    @DELETE
    @Operation(summary = "Excluir produto cadastrado",
            description = "Exclui produto com o id informado")
    @Path ("{id}")
    public Response excluirProduto(@RestPath Long id){
        this.produtoService.excluirProduto(id);
        return Response.status(Response.Status.OK)
                .entity("Produto deletado com sucesso").build();
    }

}
