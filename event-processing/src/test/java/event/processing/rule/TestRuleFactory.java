package event.processing.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void testReaction1() throws Exception {

        String input = "query TRIGGERS deviceInformation, domainInformation, configurationModification";
        Rule rule = test(input);

        assertNotNull(rule);
        assertNotNull(rule.getReactions());
        assertEquals(1, rule.getReactions().size());
        assertEquals("deviceInformation", rule.getReactions().get(0).getDeviceInformation());
        assertEquals("domainInformation", rule.getReactions().get(0).getDomainInformation());

    }

    private Rule test(String rule) throws Exception {
        return rf.parse(rule);
    }
}
