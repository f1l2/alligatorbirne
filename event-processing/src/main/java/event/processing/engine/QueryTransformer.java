package event.processing.engine;

import java.util.List;

import event.processing.query.Query;

public abstract class QueryTransformer {

    public abstract List<String> transform(String query);

    public abstract List<String> transform(Query query);

}
