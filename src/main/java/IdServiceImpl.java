import java.util.UUID;

public class IdServiceImpl implements IdService {
    @Override
    public UUID generateId() {
        return UUID.randomUUID();
    }
}