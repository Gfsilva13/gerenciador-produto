package br.edu.ada.Exceptions;

import jakarta.validation.ConstraintViolation;

public class ProdutoErroValidacaoException extends RuntimeException {

    public ProdutoErroValidacaoException(String mensagem) {
        super(mensagem.toString());
    }

    public ConstraintViolation<?>[] getConstraintViolations() {
        return new ConstraintViolation[0];
    }

}
