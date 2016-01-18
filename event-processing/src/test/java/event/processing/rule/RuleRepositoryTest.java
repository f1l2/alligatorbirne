package event.processing.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.lang.rule.RuleLang;
import event.processing.Application;
import event.processing.repo.RuleRepository;
import event.processing.statement.RuleLangFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RuleRepositoryTest {

    @Autowired
    private RuleRepository repo;

    @Autowired
    private RuleLangFactory qf;

    private RuleLang rule;

    @Before
    public void before() throws IOException {

        repo.reset();

        String input = "query TRIGGERS deviceName, domainName, cMName = 1";
        rule = qf.parse(input);

        repo.save("test rule", rule);
    }

    @Test
    public void findAllRules() {

        List<RuleLang> allRules = repo.findAll();

        assertNotNull(allRules);
        assertEquals(1, allRules.size());
        assertEquals(rule.toString(), allRules.get(0).toString());
    }

    @Test
    public void findOneRule() {

        RuleLang result = repo.findOne("test rule");

        assertNotNull(result);
        assertEquals(rule.toString(), result.toString());
    }

    @Test
    public void deleteRule() {

        repo.delete("test rule");

        List<RuleLang> allQueries = repo.findAll();

        assertNotNull(allQueries);
        assertEquals(0, allQueries.size());

    }

}
