package event.processing.query;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.springframework.stereotype.Component;

import event.processing.query.language.QueryBaseListener;
import event.processing.query.language.QueryLexer;
import event.processing.query.language.QueryParser;

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
                query.setWindow(ctx.getText());

                String windowValue = ctx.getText().replace(Query.KEYWORD.WIN.getKeyword(), "");
                windowValue = windowValue.replace(Query.KEYWORD.TIME.getKeyword(), "");
                windowValue = windowValue.replace(Query.KEYWORD.LENGTH.getKeyword(), "");
                windowValue = windowValue.trim();

                query.setWindowValue(windowValue);
            }

            @Override
            public void enterDomainName(QueryParser.DomainNameContext ctx) {
                query.getDomains().add(ctx.getText());
            }

            @Override
            public void enterCompare(QueryParser.CompareContext ctx) {

            }

            @Override
            public void exitCompare(QueryParser.CompareContext ctx) {

            }

            @Override
            public void enterProperty(QueryParser.PropertyContext ctx) {

            }

            @Override
            public void exitProperty(QueryParser.PropertyContext ctx) {
                query.getProperty().add(ctx.getText());
            }

            @Override
            public void enterLogicLink(QueryParser.LogicLinkContext ctx) {

            }

            @Override
            public void exitLogicLink(QueryParser.LogicLinkContext ctx) {

            }

            @Override
            public void enterAggregateCompare(QueryParser.AggregateCompareContext ctx) {
            }

            @Override
            public void exitAggregateCompare(QueryParser.AggregateCompareContext ctx) {
                query.getAggregateCompares().add(ctx.getText());
            }

            @Override
            public void enterAggregate(QueryParser.AggregateContext ctx) {
            }

            @Override
            public void exitAggregate(QueryParser.AggregateContext ctx) {
                query.getAggregates().add(ctx.getText());
            }

        });

        queryParser.query();

        return query;
    }
}
