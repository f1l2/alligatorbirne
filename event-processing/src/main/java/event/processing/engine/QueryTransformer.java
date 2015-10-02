package event.processing.engine;

import java.io.IOException;
import java.util.List;

import event.processing.query.Query;

public interface QueryTransformer {

    public List<String> transform(String query) throws IOException;

    public List<String> transform(Query query);

}
