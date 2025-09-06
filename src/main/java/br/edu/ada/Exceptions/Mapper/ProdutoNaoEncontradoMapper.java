package br.edu.ada.Exceptions.Mapper;

import br.edu.ada.Exceptions.ProdutoNaoEncontradoException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProdutoNaoEncontradoMapper implements ExceptionMapper<ProdutoNaoEncontradoException> {

    @Override
    public Response toResponse(ProdutoNaoEncontradoException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage()).build();
    }
}
