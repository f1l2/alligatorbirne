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
import event.processing.query.language.RuleParser.CMContext;
import event.processing.query.language.RuleParser.DevInfoContext;
import event.processing.query.language.RuleParser.DomainInfoContext;
import event.processing.rule.model.Reaction;

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

                DevInfoContext devInfoContext = ctx.getChild(RuleParser.DevInfoContext.class, 0);
                DomainInfoContext domainInfoContext = ctx.getChild(RuleParser.DomainInfoContext.class, 0);
                CMContext cMContext = ctx.getChild(RuleParser.CMContext.class, 0);

                Reaction reaction = new Reaction();

                reaction.setDeviceInformation(devInfoContext.getChild(RuleParser.DevInfoNameContext.class, 0).getText());
                reaction.setDomainInformation(domainInfoContext.getChild(RuleParser.DomainInfoNameContext.class, 0).getText());
                reaction.setConfigurationModification(cMContext.getChild(RuleParser.CMNameContext.class, 0).getText());

                rule.getReactions().add(reaction);
            }
        });

        ruleParser.structure();

        return rule;
    }

}
