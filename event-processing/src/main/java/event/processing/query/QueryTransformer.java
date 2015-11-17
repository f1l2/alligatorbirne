package event.processing.query;

import org.springframework.stereotype.Component;

import common.data.dto.QueryDTO;
import common.transformer.Transformer;

@Component
public class QueryTransformer extends Transformer<Query, QueryDTO> {

    @Override
    public Query toLocal(QueryDTO remote) {
        Query item = new Query();
        item.setName(remote.getName());

        return item;
    }

    @Override
    public QueryDTO toRemote(Query local) {

        QueryDTO item = new QueryDTO();
        item.setQuery(local.getNativeQuery());
        item.setName(local.getName());

        return item;
    }

}
