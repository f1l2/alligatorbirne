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
	
	 public Query createQuery(String in) throws IOException {
	        QueryLexer l = new QueryLexer(new ANTLRInputStream(in));
	        QueryParser p = new QueryParser(new CommonTokenStream(l));
	        p.addErrorListener(new BaseErrorListener() {
	            @Override
	            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
	                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
	            }
	        });


	        final String[] condition = new String[1];

	        p.addParseListener(new QueryBaseListener() {
	        	@Override public void exitCondition(QueryParser.ConditionContext ctx) {
	        		condition[0] = ctx.compare().getText();
	        	}
	        });
	        p.query();

	        return new Query(condition[0], null);
	    }
	
}