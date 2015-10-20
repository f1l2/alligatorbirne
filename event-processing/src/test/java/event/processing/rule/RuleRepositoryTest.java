package event.processing.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import event.processing.Application;
import event.processing.repo.RuleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RuleRepositoryTest {

    @Autowired
    private RuleRepository repo;

    @Autowired
    private RuleFactory qf;

    private Rule rule;

    @Before
    public void before() throws IOException {

        String input = "query TRIGGERS deviceName, domainName, cMName = 1";
        rule = qf.parse(input);

        repo.save("test rule", rule);
    }

    @After
    public void after() {
        repo.delete("test rule");
    }

    @Test
    public void findAllRules() {

        List<Rule> allRules = repo.findAll();

        assertNotNull(allRules);
        assertEquals(1, allRules.size());
        assertEquals(rule.toString(), allRules.get(0).toString());
    }

    @Test
    public void findOneRule() {

        Rule result = repo.findOne("test rule");

        assertNotNull(result);
        assertEquals(rule.toString(), result.toString());
    }

    @Test
    public void deleteRule() {

        repo.delete("test rule");

        List<Rule> allQueries = repo.findAll();

        assertNotNull(allQueries);
        assertEquals(0, allQueries.size());

    }

}
