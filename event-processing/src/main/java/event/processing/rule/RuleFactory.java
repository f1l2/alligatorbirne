package event.processing.rule;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.springframework.stereotype.Component;

import event.processing.query.language.RuleBaseListener;
import event.processing.query.language.RuleLexer;
import event.processing.query.language.RuleParser;
import event.processing.query.language.RuleParser.ConfigurationModificationContext;
import event.processing.query.language.RuleParser.DeviceInformationContext;
import event.processing.query.language.RuleParser.DomainInformationContext;

@Component
public class RuleFactory {

    public Rule parse(String in) throws IOException {

        final Rule rule = new Rule();

        final RuleLexer ruleLexer = new RuleLexer(new ANTLRInputStream(in));
        final RuleParser ruleParser = new RuleParser(new CommonTokenStream(ruleLexer));

        ruleParser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        ruleParser.addParseListener(new RuleBaseListener() {

            @Override
            public void exitQuery(RuleParser.QueryContext ctx) {
                rule.setQuery(ctx.getText());
            }

            /**
             * {@inheritDoc}
             *
             * <p>
             * The default implementation does nothing.
             * </p>
             */
            @Override
            public void exitReaction(RuleParser.ReactionContext ctx) {

                Reaction reaction = new Reaction();

                DeviceInformationContext deviceInformationContext = ctx.getChild(RuleParser.DeviceInformationContext.class, 0);
                DomainInformationContext domainInformationContext = ctx.getChild(RuleParser.DomainInformationContext.class, 0);
                ConfigurationModificationContext configurationModificationContext = ctx.getChild(RuleParser.ConfigurationModificationContext.class, 0);

                reaction.setDeviceInformation(deviceInformationContext.getChild(RuleParser.DeviceInformationNameContext.class, 0).getText());
                reaction.setDomainInformation(domainInformationContext.getChild(RuleParser.DomainInformationNameContext.class, 0).getText());
                reaction.setConfigurationModification(configurationModificationContext.getChild(RuleParser.ConfigurationModificationContext.class, 0).getText());

                rule.getReactions().add(reaction);
            }
        });

        ruleParser.rule();

        return rule;
    }

}
