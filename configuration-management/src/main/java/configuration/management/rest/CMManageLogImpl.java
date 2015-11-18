package configuration.management.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.data.dto.LogDTO;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.transformer.Transformer;
import configuration.management.model.LogDLO;
import configuration.management.repo.LogRepository;
import configuration.management.repo.LogTransformer;

@RestController
public class CMManageLogImpl implements CMManageLog {

    private static final Logger logger = LoggerFactory.getLogger(CMManageLogImpl.class);

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogTransformer logTransformer;

    @Override
    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public List<LogDTO> getAllLogs() {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_GET_ALL_QUERIES));

        LogDLO log = new LogDLO();
        log.setMessage("yeah");
        log.setName("f1l2");

        logRepository.save(log);

        List<LogDLO> locals = Transformer.makeCollection(logRepository.findAll());

        return logTransformer.toRemote(locals);
    }

}
