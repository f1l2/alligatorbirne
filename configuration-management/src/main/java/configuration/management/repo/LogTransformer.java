package configuration.management.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.dto.LogDTO;
import common.transformer.Transformer;
import configuration.management.model.LogDLO;

@Component
public class LogTransformer extends Transformer<LogDLO, LogDTO> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public LogDLO toLocal(LogDTO remote) {
        LogDLO item = new LogDLO();
        item.setMessage(remote.getMessage());
        item.setName(remote.getName());
        item.setDate(remote.getDate());
        return item;
    }

    @Override
    public LogDTO toRemote(LogDLO local) {
        LogDTO item = new LogDTO();
        item.setMessage(local.getMessage());
        item.setName(local.getName());
        item.setDate(local.getDate());
        return item;
    }

}
