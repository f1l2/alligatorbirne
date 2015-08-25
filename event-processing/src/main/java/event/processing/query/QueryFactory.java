package event.processing.query;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import event.processing.query.language.QueryBaseListener;
import event.processing.query.language.QueryLexer;
import event.processing.query.language.QueryParser;

public class QueryFactory {

    public Query create(String in) throws IOException {
        QueryLexer queryLexer = new QueryLexer(new ANTLRInputStream(in));
        QueryParser queryParser = new QueryParser(new CommonTokenStream(queryLexer));
        queryParser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        final Query query = new Query();

        queryParser.addParseListener(new QueryBaseListener() {

            @Override
            public void exitQuery(QueryParser.QueryContext ctx) {
            }

            @Override
            public void exitWindow(QueryParser.WindowContext ctx) {
                query.setWindow(ctx.getText());

                String windowValue = ctx.getText().replace(Query.KEYWORD_WIN, "");
                windowValue = windowValue.replace(Query.KEYWORD_TIME, "");
                windowValue = windowValue.replace(Query.KEYWORD_LENGTH, "");
                windowValue = windowValue.trim();

                query.setWindowValue(windowValue);

            }

            @Override
            public void exitDomainlist(QueryParser.DomainlistContext ctx) {
                query.setDomainList(ctx.getText());
            }

            @Override
            public void exitDomain(QueryParser.DomainContext ctx) {
                query.getDomain().add(ctx.getText());
            }

            @Override
            public void exitCondition(QueryParser.ConditionContext ctx) {
                query.setCondition(ctx.getText());
            }

            @Override
            public void exitComparelogic(QueryParser.ComparelogicContext ctx) {
                query.setCompareLogic(ctx.getText());
            }

            @Override
            public void exitCompare(QueryParser.CompareContext ctx) {
                query.getCompare().add(ctx.getText());
            }

            @Override
            public void exitAggregate(QueryParser.AggregateContext ctx) {
                query.getAggregate().add(ctx.getText());
            }

            @Override
            public void exitProperty(QueryParser.PropertyContext ctx) {
                query.getProperty().add(ctx.getText());
            }

        });

        queryParser.query();

        return query;
    }
}
