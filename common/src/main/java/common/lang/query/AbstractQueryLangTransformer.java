package common.lang.query;

import common.data.dto.QueryDTO;
import common.transformer.Transformer;

public abstract class AbstractQueryLangTransformer extends Transformer<QueryLang, QueryDTO> {

    @Override
    public QueryLang toLocal(QueryDTO remote) {
        QueryLang item = new QueryLang();
        item.setName(remote.getName());

        return item;
    }

    @Override
    public QueryDTO toRemote(QueryLang local) {

        QueryDTO item = new QueryDTO();
        item.setQuery(local.getNativeQuery());
        item.setName(local.getName());

        return item;
    }

}
