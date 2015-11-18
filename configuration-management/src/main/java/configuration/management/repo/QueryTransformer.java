package configuration.management.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.dto.QueryDTO;
import common.transformer.Transformer;
import configuration.management.model.QueryDLO;

@Component
public class QueryTransformer extends Transformer<QueryDLO, QueryDTO> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public QueryDLO toLocal(QueryDTO remote) {
        QueryDLO item = new QueryDLO();
        item.setName(remote.getName());
        item.setQuery(remote.getQuery());

        return item;
    }

    @Override
    public QueryDTO toRemote(QueryDLO local) {
        QueryDTO item = new QueryDTO();
        item.setName(local.getName());
        item.setQuery(local.getQuery());

        return item;
    }

}
