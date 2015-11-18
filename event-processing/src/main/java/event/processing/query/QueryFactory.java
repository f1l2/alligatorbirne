package event.processing.query;

import java.io.IOException;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.springframework.stereotype.Component;

import common.gen.language.QueryBaseListener;
import common.gen.language.QueryLexer;
import common.gen.language.QueryParser;
import common.gen.language.QueryParser.AggregateConditionContext;
import common.gen.language.QueryParser.AggregateFunctionContext;
import common.gen.language.QueryParser.CompositeConditionContext;
import common.gen.language.QueryParser.CompositeOperationDoubleDigitContext;
import common.gen.language.QueryParser.CompositeOperationSingleDigitContext;
import common.gen.language.QueryParser.EvaluationContext;
import common.gen.language.QueryParser.IntValueContext;
import common.gen.language.QueryParser.OperatorContext;
import common.gen.language.QueryParser.PropertyContext;
import common.gen.language.QueryParser.SingleConditionContext;
import common.gen.language.QueryParser.VariableContext;
import common.gen.language.QueryParser.WindowTypeContext;
import common.utilities.NormalizeString;
import event.processing.query.Query.AGGREGATION_FUNCTION;
import event.processing.query.Query.COMPARE_FUNCTION;
import event.processing.query.Query.KEYWORD;
import event.processing.query.Query.LOGIC_FUNCTION;
import event.processing.query.model.AggregateCondition;
import event.processing.query.model.CompositeCondition;
import event.processing.query.model.Evaluation;
import event.processing.query.model.SingleCondition;
import event.processing.query.model.Window;

@Component
public class QueryFactory {

    public Query parse(String in, String name) throws IOException {

        in = NormalizeString.normalize(in);

        final Query query = new Query();
        query.setName(name);

        final QueryLexer queryLexer = new QueryLexer(new ANTLRInputStream(in));
        final QueryParser queryParser = new QueryParser(new CommonTokenStream(queryLexer));

        queryParser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        queryParser.addParseListener(new QueryBaseListener() {

            HashMap<String, SingleCondition> singleConditions = new HashMap<String, SingleCondition>();

            HashMap<String, CompositeCondition> compositeConditions = new HashMap<String, CompositeCondition>();

            @Override
            public void exitWindow(QueryParser.WindowContext ctx) {

                Window window = new Window();
                window.setType(KEYWORD.findByKeyword(ctx.getChild(WindowTypeContext.class, 0).getText().toLowerCase()));
                window.setValue(ctx.getChild(IntValueContext.class, 0).getText());

                query.setWindow(window);
            }

            @Override
            public void exitCondition(QueryParser.ConditionContext ctx) {

                SingleConditionContext singleConditionContext = ctx.getChild(SingleConditionContext.class, 0);
                CompositeConditionContext compositeConditionContext = ctx.getChild(CompositeConditionContext.class, 0);

                if (null != singleConditionContext) {

                    SingleCondition singleCondition = getSingleCondition(singleConditionContext, singleConditions);

                    query.setCondition(singleCondition);

                } else if (null != compositeConditionContext) {

                    CompositeCondition compositeCondition = getCompositeCondition(compositeConditionContext, compositeConditions);

                    query.setCondition(compositeCondition);

                }
            }

            @Override
            public void exitSingleCondition(QueryParser.SingleConditionContext ctx) {

                SingleCondition sc = new SingleCondition();

                EvaluationContext evaluationContext = ctx.getChild(QueryParser.EvaluationContext.class, 0);

                if (evaluationContext != null) {
                    sc.setEvaluation(getEvaluation(ctx.getChild(QueryParser.EvaluationContext.class, 0)));
                } else {
                    AggregateConditionContext aCtx = ctx.getChild(QueryParser.AggregateConditionContext.class, 0);

                    AggregateCondition aCondition = new AggregateCondition();
                    aCondition.setAggregateCondition(aCtx.getText());

                    OperatorContext operatorContext = aCtx.getChild(QueryParser.OperatorContext.class, 0);
                    aCondition.setOperator(COMPARE_FUNCTION.findByFunction(operatorContext.getText()));

                    AggregateFunctionContext aggregateFunctionContext = aCtx.getChild(QueryParser.AggregateFunctionContext.class, 0);
                    aCondition.setAggregation(AGGREGATION_FUNCTION.findByFunction(aggregateFunctionContext.getText()));

                    VariableContext propertyContext = aCtx.getChild(QueryParser.VariableContext.class, 0);
                    aCondition.setProperty(propertyContext.getText());

                    IntValueContext intContext = aCtx.getChild(QueryParser.IntValueContext.class, 0);
                    aCondition.setValue(intContext.getText());

                    sc.setAggregateCondition(aCondition);
                }

                singleConditions.put(ctx.getText(), sc);
            }

            @Override
            public void exitCompositeCondition(QueryParser.CompositeConditionContext ctx) {

                CompositeCondition cc = new CompositeCondition();

                CompositeOperationSingleDigitContext singleDigitContext = ctx.getChild(QueryParser.CompositeOperationSingleDigitContext.class, 0);
                CompositeOperationDoubleDigitContext doubleDigitContext = ctx.getChild(QueryParser.CompositeOperationDoubleDigitContext.class, 0);
                SingleConditionContext singleConditionContextSecond = ctx.getChild(QueryParser.SingleConditionContext.class, 0);

                if (singleDigitContext != null) {

                    String compositeFunction = singleDigitContext.getChild(QueryParser.CompositeFunctionSingleDigitContext.class, 0).getText();
                    cc.setCompositeFunction(LOGIC_FUNCTION.findByFunction(compositeFunction));

                    CompositeConditionContext second = singleDigitContext.getChild(QueryParser.CompositeConditionContext.class, 0);
                    cc.setCc(getCompositeCondition(second, compositeConditions));

                } else if (doubleDigitContext != null) {

                    String compositeFunction = doubleDigitContext.getChild(QueryParser.CompositeFunctionDoubleDigitContext.class, 0).getText();
                    cc.setCompositeFunction(LOGIC_FUNCTION.findByFunction(compositeFunction));

                    SingleConditionContext first = doubleDigitContext.getChild(QueryParser.SingleConditionContext.class, 0);
                    CompositeConditionContext second = doubleDigitContext.getChild(QueryParser.CompositeConditionContext.class, 0);

                    cc.setSc(getSingleCondition(first, singleConditions));
                    cc.setCc(getCompositeCondition(second, compositeConditions));

                } else if (singleConditionContextSecond != null) {

                    cc.setSc(getSingleCondition(singleConditionContextSecond, singleConditions));
                }

                compositeConditions.put(ctx.getText(), cc);
            }

            @Override
            public void exitDomainName(QueryParser.DomainNameContext ctx) {
                query.getDomains().add(ctx.getText());
            }

        });

        queryParser.query();

        return query;
    }

    private Evaluation getEvaluation(EvaluationContext ctx) {

        OperatorContext operatorContext = ctx.getChild(QueryParser.OperatorContext.class, 0);

        PropertyContext propertyContext1 = ctx.getChild(QueryParser.PropertyContext.class, 0);

        PropertyContext propertyContext2 = ctx.getChild(QueryParser.PropertyContext.class, 1);

        Evaluation evaluation = new Evaluation();
        evaluation.setEvaluation(ctx.getText());
        evaluation.setProperty1(propertyContext1.getText());
        evaluation.setProperty2(propertyContext2.getText());
        evaluation.setOperator(COMPARE_FUNCTION.findByFunction(operatorContext.getText()));

        return evaluation;
    }

    private SingleCondition getSingleCondition(SingleConditionContext ctx, HashMap<String, SingleCondition> map) {

        if (map.containsKey(ctx.getText())) {
            return map.get(ctx.getText());
        }
        return null;
    }

    private CompositeCondition getCompositeCondition(CompositeConditionContext ctx, HashMap<String, CompositeCondition> map) {

        if (map.containsKey(ctx.getText())) {
            return map.get(ctx.getText());
        }
        return null;
    }

}
