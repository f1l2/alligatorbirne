// Generated from Query.g4 by ANTLR 4.5.1

	package event.processing.query.language;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, VARIABLE=15, STRING=16, 
		INT=17, COMMA=18, WS=19, NL=20, OPERATOR=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "VARIABLE", "STRING", "INT", 
		"COMMA", "WS", "NL", "OPERATOR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'WIN.TIME('", "')'", "'WIN.LENGTH('", "'FROM'", "'CONDITION'", 
		"'AND'", "'OR'", "'NOT'", "'SUM'", "'('", "'AVG'", "'COUNT'", "'MAX'", 
		"'MIN'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "VARIABLE", "STRING", "INT", "COMMA", "WS", "NL", "OPERATOR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u009f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\20\6\20y\n\20\r\20\16\20z\3\21\3\21\6\21\177\n\21\r\21\16\21\u0080"+
		"\3\21\3\21\3\22\6\22\u0086\n\22\r\22\16\22\u0087\3\23\6\23\u008b\n\23"+
		"\r\23\16\23\u008c\3\24\6\24\u0090\n\24\r\24\16\24\u0091\3\25\5\25\u0095"+
		"\n\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\5\26\u009e\n\26\2\2\27\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27\3\2\5\6\2//C\\aac|\4\2C\\c|\4\2\13\13\"\"\u00a6"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5\67\3\2"+
		"\2\2\79\3\2\2\2\tE\3\2\2\2\13J\3\2\2\2\rT\3\2\2\2\17X\3\2\2\2\21[\3\2"+
		"\2\2\23_\3\2\2\2\25c\3\2\2\2\27e\3\2\2\2\31i\3\2\2\2\33o\3\2\2\2\35s\3"+
		"\2\2\2\37x\3\2\2\2!|\3\2\2\2#\u0085\3\2\2\2%\u008a\3\2\2\2\'\u008f\3\2"+
		"\2\2)\u0094\3\2\2\2+\u009d\3\2\2\2-.\7Y\2\2./\7K\2\2/\60\7P\2\2\60\61"+
		"\7\60\2\2\61\62\7V\2\2\62\63\7K\2\2\63\64\7O\2\2\64\65\7G\2\2\65\66\7"+
		"*\2\2\66\4\3\2\2\2\678\7+\2\28\6\3\2\2\29:\7Y\2\2:;\7K\2\2;<\7P\2\2<="+
		"\7\60\2\2=>\7N\2\2>?\7G\2\2?@\7P\2\2@A\7I\2\2AB\7V\2\2BC\7J\2\2CD\7*\2"+
		"\2D\b\3\2\2\2EF\7H\2\2FG\7T\2\2GH\7Q\2\2HI\7O\2\2I\n\3\2\2\2JK\7E\2\2"+
		"KL\7Q\2\2LM\7P\2\2MN\7F\2\2NO\7K\2\2OP\7V\2\2PQ\7K\2\2QR\7Q\2\2RS\7P\2"+
		"\2S\f\3\2\2\2TU\7C\2\2UV\7P\2\2VW\7F\2\2W\16\3\2\2\2XY\7Q\2\2YZ\7T\2\2"+
		"Z\20\3\2\2\2[\\\7P\2\2\\]\7Q\2\2]^\7V\2\2^\22\3\2\2\2_`\7U\2\2`a\7W\2"+
		"\2ab\7O\2\2b\24\3\2\2\2cd\7*\2\2d\26\3\2\2\2ef\7C\2\2fg\7X\2\2gh\7I\2"+
		"\2h\30\3\2\2\2ij\7E\2\2jk\7Q\2\2kl\7W\2\2lm\7P\2\2mn\7V\2\2n\32\3\2\2"+
		"\2op\7O\2\2pq\7C\2\2qr\7Z\2\2r\34\3\2\2\2st\7O\2\2tu\7K\2\2uv\7P\2\2v"+
		"\36\3\2\2\2wy\t\2\2\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2{ \3\2\2"+
		"\2|~\7)\2\2}\177\t\3\2\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\7)\2\2\u0083\"\3\2\2\2"+
		"\u0084\u0086\4\62;\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085"+
		"\3\2\2\2\u0087\u0088\3\2\2\2\u0088$\3\2\2\2\u0089\u008b\7.\2\2\u008a\u0089"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"&\3\2\2\2\u008e\u0090\t\4\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2"+
		"\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092(\3\2\2\2\u0093\u0095\7"+
		"\17\2\2\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0097\7\f\2\2\u0097*\3\2\2\2\u0098\u009e\4>@\2\u0099\u009a\7>\2\2\u009a"+
		"\u009e\7?\2\2\u009b\u009c\7@\2\2\u009c\u009e\7?\2\2\u009d\u0098\3\2\2"+
		"\2\u009d\u0099\3\2\2\2\u009d\u009b\3\2\2\2\u009e,\3\2\2\2\n\2z\u0080\u0087"+
		"\u008c\u0091\u0094\u009d\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}