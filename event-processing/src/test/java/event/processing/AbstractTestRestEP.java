package event.processing;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;

import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.repo.QueryRepository;
import event.processing.status.STATUS_TYPE;
import event.processing.status.Status;

public abstract class AbstractTestRestEP {

    @Autowired
    protected QueryRepository repo;

    @Autowired
    protected QueryFactory qf;

    @Autowired
    protected Status status;

    protected Query query;

    protected final String strQuery = Query.KEYWORD.CONDITION.getKeyword() + " property = 21 AND abc = 21 " + Query.KEYWORD.FROM.getKeyword() + " Domain";

    @Value("${local.server.port}")
    protected int port;

    protected String queryName, ruleName;

    protected static int count = 0;

    @Before
    public void before() throws IOException {

        status.setCurrent(STATUS_TYPE.TEST);

        query = qf.parse(strQuery, "test query");

        repo.save(query);

        RestAssured.port = port;

        queryName = getUniqueName();

        ruleName = getUniqueName();
    }

    @After
    public void after() {
        repo.delete("test query");
    }

    private String getUniqueName() {
        count++;
        return "alligatorbirne" + Integer.toString(count);
    }
}
