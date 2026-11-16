package excecao;

public class MissingFuncionarioException extends RuntimeException {
    public MissingFuncionarioException(String message) {
        super(message);
    }
}
