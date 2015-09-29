// Generated from Rule.g4 by ANTLR 4.5.1

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, VARIABLE=7, STRING=8, 
		INT=9, COMMA=10, WS=11, NL=12;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "VARIABLE", "STRING", 
		"INT", "COMMA", "WS", "NL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'deviceInformation'", "'='", "'domainInformation'", 
		"'configurationModification'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "VARIABLE", "STRING", "INT", 
		"COMMA", "WS", "NL"
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
	public String getGrammarFileName() { return "Rule.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\16\u0081\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\6\bb\n\b\r\b\16\bc\3\t\3\t\6\th\n\t\r\t\16\t"+
		"i\3\t\3\t\3\n\6\no\n\n\r\n\16\np\3\13\6\13t\n\13\r\13\16\13u\3\f\6\fy"+
		"\n\f\r\f\16\fz\3\r\5\r~\n\r\3\r\3\r\2\2\16\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\3\2\6\4\2C\\c|\7\2//\62;C\\aac|\5\2\62;"+
		"C\\c|\4\2\13\13\"\"\u0086\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\3\33\3\2\2\2\5\35\3\2\2\2\7\37\3"+
		"\2\2\2\t\61\3\2\2\2\13\63\3\2\2\2\rE\3\2\2\2\17_\3\2\2\2\21e\3\2\2\2\23"+
		"n\3\2\2\2\25s\3\2\2\2\27x\3\2\2\2\31}\3\2\2\2\33\34\7=\2\2\34\4\3\2\2"+
		"\2\35\36\7.\2\2\36\6\3\2\2\2\37 \7f\2\2 !\7g\2\2!\"\7x\2\2\"#\7k\2\2#"+
		"$\7e\2\2$%\7g\2\2%&\7K\2\2&\'\7p\2\2\'(\7h\2\2()\7q\2\2)*\7t\2\2*+\7o"+
		"\2\2+,\7c\2\2,-\7v\2\2-.\7k\2\2./\7q\2\2/\60\7p\2\2\60\b\3\2\2\2\61\62"+
		"\7?\2\2\62\n\3\2\2\2\63\64\7f\2\2\64\65\7q\2\2\65\66\7o\2\2\66\67\7c\2"+
		"\2\678\7k\2\289\7p\2\29:\7K\2\2:;\7p\2\2;<\7h\2\2<=\7q\2\2=>\7t\2\2>?"+
		"\7o\2\2?@\7c\2\2@A\7v\2\2AB\7k\2\2BC\7q\2\2CD\7p\2\2D\f\3\2\2\2EF\7e\2"+
		"\2FG\7q\2\2GH\7p\2\2HI\7h\2\2IJ\7k\2\2JK\7i\2\2KL\7w\2\2LM\7t\2\2MN\7"+
		"c\2\2NO\7v\2\2OP\7k\2\2PQ\7q\2\2QR\7p\2\2RS\7O\2\2ST\7q\2\2TU\7f\2\2U"+
		"V\7k\2\2VW\7h\2\2WX\7k\2\2XY\7e\2\2YZ\7c\2\2Z[\7v\2\2[\\\7k\2\2\\]\7q"+
		"\2\2]^\7p\2\2^\16\3\2\2\2_a\t\2\2\2`b\t\3\2\2a`\3\2\2\2bc\3\2\2\2ca\3"+
		"\2\2\2cd\3\2\2\2d\20\3\2\2\2eg\7)\2\2fh\t\4\2\2gf\3\2\2\2hi\3\2\2\2ig"+
		"\3\2\2\2ij\3\2\2\2jk\3\2\2\2kl\7)\2\2l\22\3\2\2\2mo\4\62;\2nm\3\2\2\2"+
		"op\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\24\3\2\2\2rt\7.\2\2sr\3\2\2\2tu\3\2\2"+
		"\2us\3\2\2\2uv\3\2\2\2v\26\3\2\2\2wy\t\5\2\2xw\3\2\2\2yz\3\2\2\2zx\3\2"+
		"\2\2z{\3\2\2\2{\30\3\2\2\2|~\7\17\2\2}|\3\2\2\2}~\3\2\2\2~\177\3\2\2\2"+
		"\177\u0080\7\f\2\2\u0080\32\3\2\2\2\t\2cipuz}\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}