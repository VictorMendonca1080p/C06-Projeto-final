package excesao;

public class MissingFilmeException extends RuntimeException {
    public MissingFilmeException(String message) {
        super(message);
    }
}
