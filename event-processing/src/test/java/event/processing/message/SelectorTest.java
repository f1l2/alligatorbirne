package event.processing.message;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.messaging.MessageHandler;
import event.processing.Application;
import event.processing.repo.RuleRepository;
import event.processing.rule.Rule;
import event.processing.rule.model.Reaction;
import event.processing.utilities.Utilities;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)

public class SelectorTest {

    @Autowired
    private RuleRepository rr;

    @Test
    public void getSelectorByReaction() {

        Reaction reaction = new Reaction();
        reaction.setDeviceInformation("device");
        reaction.setDomainInformation("domain");

        assertEquals(MessageHandler.PROPERTY + " = 'device:domain'", Utilities.getSelectorByReaction(reaction));
    }

    @Test
    public void createSelectorForActiveRules1() {
        String selector = Utilities.createSelectorForActiveRules(rr);

        assertEquals(null, selector);
    }

    @Test
    public void createSelectorForActiveRules2() {

        Reaction reaction1 = new Reaction();
        reaction1.setDeviceInformation("device");
        reaction1.setDomainInformation("domain");

        List<Reaction> reactions = new ArrayList<Reaction>();
        reactions.add(reaction1);

        Rule rule = new Rule();
        rule.setIsActivated(true);
        rule.setReactions(reactions);

        rr.save("rule1", rule);

        String selector = Utilities.createSelectorForActiveRules(rr);

        assertEquals(MessageHandler.PROPERTY + " = 'device:domain'", selector);
    }

    @Test
    public void createSelectorForActiveRules3() {

        Reaction reaction1 = new Reaction();
        reaction1.setDeviceInformation("device");
        reaction1.setDomainInformation("domain");

        Reaction reaction2 = new Reaction();
        reaction2.setDeviceInformation("device1");
        reaction2.setDomainInformation("domain1");

        List<Reaction> reactions = new ArrayList<Reaction>();
        reactions.add(reaction1);
        reactions.add(reaction2);

        Rule rule = new Rule();
        rule.setIsActivated(true);
        rule.setReactions(reactions);

        rr.save("rule1", rule);

        String selector = Utilities.createSelectorForActiveRules(rr);

        assertEquals(MessageHandler.PROPERTY + " = 'device1:domain1' OR " + MessageHandler.PROPERTY + " = 'device:domain'", selector);
    }
}
