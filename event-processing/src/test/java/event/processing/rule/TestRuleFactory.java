package event.processing.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import event.processing.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestRuleFactory {

    @Autowired
    private RuleFactory rf;

    @Test
    public void test1() throws IOException {

        String input = "query TRIGGERS deviceName, domainName, cMName";
        Rule rule = test(input);

        assertNotNull(rule);
        assertEquals("query", rule.getQuery());
        assertNotNull(rule.getReactions());
        assertEquals(1, rule.getReactions().size());
        assertEquals("deviceName", rule.getReactions().get(0).getDeviceInformation());
        assertEquals("domainName", rule.getReactions().get(0).getDomainInformation());
        assertEquals("cMName", rule.getReactions().get(0).getConfigurationModification());
    }

    @Test
    public void test2() throws IOException {

        String input = "query TRIGGERS deviceName1, domainName1, cMName1; deviceName2, domainName2, cMName2";

        Rule rule = test(input);

        assertNotNull(rule);
        assertEquals("query", rule.getQuery());
        assertNotNull(rule.getReactions());
        assertEquals(2, rule.getReactions().size());
        assertEquals("deviceName1", rule.getReactions().get(0).getDeviceInformation());
        assertEquals("domainName1", rule.getReactions().get(0).getDomainInformation());
        assertEquals("cMName1", rule.getReactions().get(0).getConfigurationModification());

    }

    @Test
    public void test3() throws IOException {
        String input = "query TRIGGERS deviceName1, domainName1, cMName1; deviceName2, domainName2, cMName2; deviceName3, domainName3, cMName3;";

        Rule rule = test(input);

        assertNotNull(rule);
        assertEquals("query", rule.getQuery());
        assertNotNull(rule.getReactions());
        assertEquals(3, rule.getReactions().size());
        assertEquals("deviceName3", rule.getReactions().get(2).getDeviceInformation());
        assertEquals("domainName3", rule.getReactions().get(2).getDomainInformation());
        assertEquals("cMName3", rule.getReactions().get(2).getConfigurationModification());
    }

    private Rule test(String rule) throws IOException {
        return rf.parse(rule);
    }
}
