package br.edu.ada.controller;

import br.edu.ada.DTO.ProdutoRequestDTO;
import br.edu.ada.Exceptions.ProdutoErroValidacaoException;
import br.edu.ada.service.auditoria.AuditoriaService;
import br.edu.ada.model.Produto;
import br.edu.ada.service.ProdutoService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/produtos")
@RequestScoped
@Authenticated
@SecurityRequirement(name = "BearerAuth")
public class ProdutoController {
    @Inject
    AuditoriaService auditoriaService;

    @Inject
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    @Operation(summary = "Cadastrar novo produto",
            description = "Cadastra novo produto")
    public Response cadastrarProduto(@Valid ProdutoRequestDTO produtoRequestDTO){

        Log.info("Chamando método cadastraProduto() com método HTTP @POST");
        try {
            produtoService.cadastraProduto(produtoRequestDTO);
            auditoriaService.auditar(" Produto:  "+produtoRequestDTO.getNome()+ " cadastrado");

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
    @RolesAllowed({"admin", "user"})
    @Operation(summary = "Listar todos produtos cadastrados",
            description = "Retorna uma lista de produtos cadastrados")
    public List<Produto> buscarProdutos(
            @QueryParam("page") Integer page,
            @QueryParam("pageSize") Integer pageSize
    ) {
      return this.produtoService.getProdutos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "user"})
    @Path("{id}")
    @Operation(summary = "Buscar produto pelo 'id'",
            description = "Retorna um produto com o 'id' informado")
    public Response buscarProdutoPorId(@RestPath Long id,
                                       @QueryParam("page") Integer page,
                                       @QueryParam("pageSize") Integer pageSize
    ) {
        long startAt = System.currentTimeMillis();
        Log.info("Chamando método buscarProdutoPorId() com o método HTTP @GET");
        Log.debug("Lista de produto Id = "+id);
        Log.info("AuditoriaService injetada: " + auditoriaService.toString());
        long tempoQuePassou = System.currentTimeMillis() - startAt;
        Log.info("Busca produto id= "+id+" executou em %d ms".formatted(tempoQuePassou));

        return Response.ok(produtoService.getProduto(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    @Operation(summary = "Alterar produto cadastrado",
            description = "Altera dados do produto com id informado")
    @Path ("{id}")
    public Response alterarProduto(@RestPath Long id, @Valid ProdutoRequestDTO produtoRequestDTO){
        try {
            this.produtoService.alterarProduto(id, produtoRequestDTO);
            auditoriaService.auditar(" Produto:  "+produtoRequestDTO.getNome()+ " alterado");

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
    @RolesAllowed({"admin"})
    @Operation(summary = "Excluir produto cadastrado",
            description = "Exclui produto com o id informado")
    @Path ("{id}")
    public Response excluirProduto(@RestPath Long id){
        this.produtoService.excluirProduto(id);
        auditoriaService.auditar(" Produto com id:  "+id+ " excluído");

        return Response.status(Response.Status.OK)
                .entity("Produto deletado com sucesso").build();
    }

}
