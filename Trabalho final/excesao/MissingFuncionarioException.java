package excesao;

public class MissingFuncionarioException extends RuntimeException {
    public MissingFuncionarioException(String message) {
        super(message);
    }
}
