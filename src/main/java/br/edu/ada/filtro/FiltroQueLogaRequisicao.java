package br.edu.ada.filtro;

import br.edu.ada.service.auditoria.AuditoriaService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

public class FiltroQueLogaRequisicao implements ContainerRequestFilter {
    @Inject AuditoriaService auditoriaService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String metodoHttp = requestContext.getRequest().getMethod();

        Log.info("absolutePath: " + requestContext.getUriInfo().getAbsolutePath());
        String mensagem = String.format("Chamando método %s() com o método HTTP %s",
                requestContext.getMethod(), metodoHttp);

        auditoriaService.auditar(mensagem);
        Log.info(mensagem);
    }
}
