package configuration.management.rest;

import java.io.IOException;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;

import configuration.management.model.QueryDLO;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.QueryRepository;
import configuration.management.repo.RuleRepository;

public abstract class AbstractTestRestCM {

    @Autowired
    protected RuleRepository ruleRepo;

    @Autowired
    protected QueryRepository queryRepo;

    @Autowired
    protected EventProcessingRepository eventProcessingRepository;

    protected QueryDLO query;

    protected final String queryStr = "CONDITION property = 21 AND abc = 21 FROM Domain";

    @Value("${local.server.port}")
    protected int port;

    protected String queryName, ruleName;

    protected static int count = 0;

    @Before
    public void before() throws IOException {

        ruleRepo.deleteAll();

        queryRepo.deleteAll();

        RestAssured.port = port;

        queryName = getUniqueName();

        ruleName = getUniqueName();

        query = new QueryDLO();
        query.setName(queryName);
        query.setQuery(queryStr);

    }

    private String getUniqueName() {
        count++;
        return "alligatorbirne" + Integer.toString(count);
    }
}
