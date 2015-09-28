package event.processing.query;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.springframework.stereotype.Component;

import event.processing.query.Query.AGGREGATOR;
import event.processing.query.Query.LOGIC_SYMBOL;
import event.processing.query.Query.OPERATOR;
import event.processing.query.language.QueryBaseListener;
import event.processing.query.language.QueryLexer;
import event.processing.query.language.QueryParser;
import event.processing.query.language.QueryParser.AggregateFunctionContext;
import event.processing.query.language.QueryParser.AggregateOperationContext;
import event.processing.query.language.QueryParser.EvaluationContext;
import event.processing.query.language.QueryParser.OperatorContext;
import event.processing.query.language.QueryParser.PropertyContext;

@Component
public class QueryFactory {

    public Query parse(String in) throws IOException {

        final Query query = new Query();

        final QueryLexer queryLexer = new QueryLexer(new ANTLRInputStream(in));
        final QueryParser queryParser = new QueryParser(new CommonTokenStream(queryLexer));

        queryParser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        queryParser.addParseListener(new QueryBaseListener() {

            @Override
            public void exitWindow(QueryParser.WindowContext ctx) {
                // query.setWindow(ctx.getText());
                //
                // String windowValue = ctx.getText().replace(Query.KEYWORD.WIN.getKeyword(), "");
                // windowValue = windowValue.replace(Query.KEYWORD.TIME.getKeyword(), "");
                // windowValue = windowValue.replace(Query.KEYWORD.LENGTH.getKeyword(), "");
                // windowValue = windowValue.trim();
                //
                // query.setWindowValue(windowValue);
            }

            @Override
            public void exitSingleCondition(QueryParser.SingleConditionContext ctx) {

                SingleCondition singleCondition = new SingleCondition();
                singleCondition.setEvaluation(getEvaluation(ctx.getChild(QueryParser.EvaluationContext.class, 0)));

                query.setCondition(singleCondition);
            }

            @Override
            public void exitCompositeCondition(QueryParser.CompositeConditionContext ctx) {

            }

            @Override
            public void exitCompositeOperationSingleDigit(QueryParser.CompositeOperationSingleDigitContext ctx) {

                CompositeCondition condition = new CompositeCondition();
                condition.setEvaluation1(getEvaluation(ctx.getChild(QueryParser.EvaluationContext.class, 0)));

                String compositeFunction = ctx.getChild(QueryParser.CompositeFunctionDoubleDigitContext.class, 0).getText();
                condition.setCompositeFunction(LOGIC_SYMBOL.findBySign(compositeFunction.toUpperCase()));

                query.setCondition(condition);
            }

            @Override
            public void exitCompositeOperationDoubleDigit(QueryParser.CompositeOperationDoubleDigitContext ctx) {

                CompositeCondition condition = new CompositeCondition();
                condition.setEvaluation1(getEvaluation(ctx.getChild(QueryParser.EvaluationContext.class, 0)));
                condition.setEvaluation2(getEvaluation(ctx.getChild(QueryParser.EvaluationContext.class, 1)));

                String compositeFunction = ctx.getChild(QueryParser.CompositeFunctionDoubleDigitContext.class, 0).getText();
                condition.setCompositeFunction(LOGIC_SYMBOL.findBySign(compositeFunction.toUpperCase()));

                query.setCondition(condition);
            }

            @Override
            public void exitAggregateCondition(QueryParser.AggregateConditionContext ctx) {

                AggregateCondition condition = new AggregateCondition();
                condition.setAggregateCondition(ctx.getText());

                AggregateOperationContext aggregateOperationContext = ctx.getChild(QueryParser.AggregateOperationContext.class, 0);
                condition.setAggregateOperation(aggregateOperationContext.getText());

                OperatorContext operatorContext = aggregateOperationContext.getChild(QueryParser.OperatorContext.class, 0);
                condition.setOperator(OPERATOR.findBySign(operatorContext.getText()));

                AggregateFunctionContext aggregateFunctionContext = aggregateOperationContext.getChild(QueryParser.AggregateFunctionContext.class, 0);
                condition.setAggregator(AGGREGATOR.findBySign(aggregateFunctionContext.getText().toUpperCase()));

                query.setCondition(condition);
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
        evaluation.setOperator(OPERATOR.findBySign(operatorContext.getText()));

        return evaluation;
    }
}
