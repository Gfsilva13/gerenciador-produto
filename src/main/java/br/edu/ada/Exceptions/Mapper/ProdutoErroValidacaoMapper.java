package br.edu.ada.Exceptions.Mapper;

import br.edu.ada.Exceptions.ProdutoErroValidacaoException;
import jakarta.validation.ConstraintViolation;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class ProdutoErroValidacaoMapper implements ExceptionMapper<ProdutoErroValidacaoException> {

    @Override
    public Response toResponse(ProdutoErroValidacaoException exception) {
        Map<String, String> erros = new HashMap<>();
        for (ConstraintViolation<?> violacao : exception.getConstraintViolations()) {
            String campo = violacao.getPropertyPath().toString();
            String mensagem = violacao.getMessage();
            erros.put(campo, mensagem);
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(erros)
                .build();
    }
}
