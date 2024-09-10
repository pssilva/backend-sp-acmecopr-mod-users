package br.gov.acmecorp.modules.users.arch.clean.infrastructure.exceptions;

import java.io.Serial;

public class RecordNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(String uuid) {
        super("Registro não encontrado com o id: " + uuid);
    }
}
