package common.lang.rule;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import common.gen.language.RuleBaseListener;
import common.gen.language.RuleLexer;
import common.gen.language.RuleParser;
import common.gen.language.RuleParser.CMContext;
import common.gen.language.RuleParser.CMKeyContext;
import common.gen.language.RuleParser.CMPropertyContext;
import common.gen.language.RuleParser.CMValueContext;
import common.gen.language.RuleParser.DevInfoContext;
import common.gen.language.RuleParser.DomainInfoContext;
import common.gen.language.RuleParser.IntValueContext;
import common.gen.language.RuleParser.WindowTypeContext;
import common.lang.rule.RuleLang.KEYWORD;
import common.lang.rule.model.Reaction;
import common.lang.rule.model.Window;
import common.utilities.NormalizeString;

public abstract class AbstractRuleLangFactory {

    public RuleLang parse(String in) throws IOException {

        in = NormalizeString.normalize(in);

        final RuleLang rule = new RuleLang();

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
            public void exitWindow(RuleParser.WindowContext ctx) {

                Window window = new Window();
                window.setType(KEYWORD.findByKeyword(ctx.getChild(WindowTypeContext.class, 0).getText().toLowerCase()));
                window.setValue(ctx.getChild(IntValueContext.class, 0).getText());

                rule.setWindow(window);
            }

            @Override
            public void exitQuery(RuleParser.QueryContext ctx) {
                rule.addQueryName(ctx.getText());
            }

            @Override
            public void exitReaction(RuleParser.ReactionContext ctx) {

                DevInfoContext devInfoContext = ctx.getChild(RuleParser.DevInfoContext.class, 0);
                DomainInfoContext domainInfoContext = ctx.getChild(RuleParser.DomainInfoContext.class, 0);
                CMContext cMContext = ctx.getChild(RuleParser.CMContext.class, 0);

                Reaction reaction = new Reaction();

                reaction.setDeviceInformation(devInfoContext.getChild(RuleParser.DevInfoNameContext.class, 0).getText());
                reaction.setDomainInformation(domainInfoContext.getChild(RuleParser.DomainInfoNameContext.class, 0).getText());

                CMPropertyContext cMPropertyContext = cMContext.getChild(RuleParser.CMPropertyContext.class, 0);
                for (int i = 1; cMPropertyContext != null; i++) {

                    String key = cMPropertyContext.getChild(CMKeyContext.class, 0).getText();
                    String value = cMPropertyContext.getChild(CMValueContext.class, 0).getText();

                    reaction.addConfigurationModification(key, value);

                    cMPropertyContext = cMContext.getChild(RuleParser.CMPropertyContext.class, i);

                }

                rule.getReactions().add(reaction);
            }

        });

        ruleParser.structure();

        return rule;
    }
}
