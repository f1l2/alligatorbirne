package configuration.management.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.transformer.Transformer;
import configuration.management.Application;
import configuration.management.model.QueryDLO;
import configuration.management.model.RuleDLO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RuleRepositoryTest {

    @Autowired
    private RuleRepository repo;

    @Autowired
    private QueryRepository qRepo;

    private RuleDLO rule;

    private QueryDLO query;

    @Before
    public void before() throws IOException {

        repo.deleteAll();
        qRepo.deleteAll();

        String ruleStr = "query TRIGGERS deviceName, domainName, cMName = 1";
        String nameStr = "rule1";

        rule = new RuleDLO();
        rule.setRule(ruleStr);
        rule.setName(nameStr);

        String queryStr = "CONDITION property = 21 AND abc = 21 FROM Domain";
        String nameQueryStr = "query1";

        query = new QueryDLO();
        query.setName(nameQueryStr);
        query.setQuery(queryStr);

        repo.save(rule);
        qRepo.save(query);
    }

    @Test
    public void findAllRuleDLOs() {

        List<RuleDLO> allRuleDLOs = Transformer.makeCollection(repo.findAll());

        assertNotNull(allRuleDLOs);
        assertEquals(1, allRuleDLOs.size());
        assertEquals(rule.getName(), allRuleDLOs.get(0).getName());
    }

    @Test
    public void findOneRuleDLO() {

        RuleDLO result = repo.findByName(rule.getName());

        assertNotNull(result);
        assertEquals(rule.getName(), result.getName());
    }

    @Test
    public void deleteRuleDLO() {

        RuleDLO result = repo.findByName(rule.getName());

        repo.delete(result);

        List<RuleDLO> allQueries = Transformer.makeCollection(repo.findAll());

        assertNotNull(allQueries);
        assertEquals(0, allQueries.size());

    }

    @Test
    public void saveQuery() {

        QueryDLO qResult = qRepo.findByName(query.getName());

        Set<QueryDLO> queries = new HashSet<QueryDLO>();
        queries.add(qResult);

        RuleDLO result = repo.findByName(rule.getName());
        result.setQueries(queries);

        repo.save(result);

        List<RuleDLO> rules = repo.findByQuery(query.getName());

        assertNotNull(rules);
        assertEquals(1, rules.size());

        rules = repo.findByQuery("abc");

        assertNotNull(rules);
        assertEquals(0, rules.size());

    }

}
