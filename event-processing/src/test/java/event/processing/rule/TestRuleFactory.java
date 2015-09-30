package event.processing.rule;

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
    public void dummyTest() {

    }

    private Rule test(String rule) throws Exception {
        return rf.parse(rule);
    }
}
