package br.edu.ada.interceptador;

import io.quarkus.narayana.jta.QuarkusTransaction;
import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@MeuTransactional
@Priority(Interceptor.Priority.APPLICATION)
public class MeuTransactionalInterceptor {

    @AroundInvoke
    public Object verTempoDoMetodo(InvocationContext invocationContext) throws Exception {
        try {
            QuarkusTransaction.begin();

            Object retornoDoMetodoReal = invocationContext.proceed();
            QuarkusTransaction.commit();
            return retornoDoMetodoReal;
        } catch (RuntimeException e) {
            QuarkusTransaction.rollback();
            throw new RuntimeException(e);
        }
    }
}
