package br.edu.ada.service.auditoria;

import br.edu.ada.interceptador.MeuTransactional;
import br.edu.ada.model.Auditoria;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditoriaService {

    @MeuTransactional
    public void auditar(String mensagem) {
        Auditoria.persist(new Auditoria(mensagem));
    }
}
