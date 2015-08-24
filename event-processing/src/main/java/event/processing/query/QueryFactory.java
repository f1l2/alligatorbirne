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

        final String[] condition = new String[1];

        queryParser.addParseListener(new QueryBaseListener() {
            @Override
            public void exitCondition(QueryParser.ConditionContext ctx) {
                condition[0] = ctx.compare().getText();
            }
            
            @Override
            public void exit
        });
        queryParser.query();

        return new Query(condition[0], null);
    }
}
