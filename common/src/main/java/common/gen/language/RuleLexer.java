// Generated from Rule.g4 by ANTLR 4.5.1

	package common.gen.language;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RuleLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, VARIABLE=13, STRING=14, INT=15, COMMA=16, 
		WS=17, NL=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "VARIABLE", "STRING", "INT", "COMMA", "WS", 
		"NL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'TRIGGERS'", "'triggers'", "'('", "')'", "'->'", "';'", "','", 
		"'='", "'WIN:TIME'", "'win:time'", "'WIN:LENGTH'", "'win:length'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "VARIABLE", "STRING", "INT", "COMMA", "WS", "NL"
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


	public RuleLexer(CharStream input) {
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\24\u0090\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\6\16q\n\16\r\16\16\16r\3\17\3\17"+
		"\6\17w\n\17\r\17\16\17x\3\17\3\17\3\20\6\20~\n\20\r\20\16\20\177\3\21"+
		"\6\21\u0083\n\21\r\21\16\21\u0084\3\22\6\22\u0088\n\22\r\22\16\22\u0089"+
		"\3\23\5\23\u008d\n\23\3\23\3\23\2\2\24\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\3\2\6\4\2C\\c|"+
		"\7\2//\62;C\\aac|\5\2\62;C\\c|\4\2\13\13\"\"\u0095\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3"+
		"\2\2\2\5\60\3\2\2\2\79\3\2\2\2\t;\3\2\2\2\13=\3\2\2\2\r@\3\2\2\2\17B\3"+
		"\2\2\2\21D\3\2\2\2\23F\3\2\2\2\25O\3\2\2\2\27X\3\2\2\2\31c\3\2\2\2\33"+
		"n\3\2\2\2\35t\3\2\2\2\37}\3\2\2\2!\u0082\3\2\2\2#\u0087\3\2\2\2%\u008c"+
		"\3\2\2\2\'(\7V\2\2()\7T\2\2)*\7K\2\2*+\7I\2\2+,\7I\2\2,-\7G\2\2-.\7T\2"+
		"\2./\7U\2\2/\4\3\2\2\2\60\61\7v\2\2\61\62\7t\2\2\62\63\7k\2\2\63\64\7"+
		"i\2\2\64\65\7i\2\2\65\66\7g\2\2\66\67\7t\2\2\678\7u\2\28\6\3\2\2\29:\7"+
		"*\2\2:\b\3\2\2\2;<\7+\2\2<\n\3\2\2\2=>\7/\2\2>?\7@\2\2?\f\3\2\2\2@A\7"+
		"=\2\2A\16\3\2\2\2BC\7.\2\2C\20\3\2\2\2DE\7?\2\2E\22\3\2\2\2FG\7Y\2\2G"+
		"H\7K\2\2HI\7P\2\2IJ\7<\2\2JK\7V\2\2KL\7K\2\2LM\7O\2\2MN\7G\2\2N\24\3\2"+
		"\2\2OP\7y\2\2PQ\7k\2\2QR\7p\2\2RS\7<\2\2ST\7v\2\2TU\7k\2\2UV\7o\2\2VW"+
		"\7g\2\2W\26\3\2\2\2XY\7Y\2\2YZ\7K\2\2Z[\7P\2\2[\\\7<\2\2\\]\7N\2\2]^\7"+
		"G\2\2^_\7P\2\2_`\7I\2\2`a\7V\2\2ab\7J\2\2b\30\3\2\2\2cd\7y\2\2de\7k\2"+
		"\2ef\7p\2\2fg\7<\2\2gh\7n\2\2hi\7g\2\2ij\7p\2\2jk\7i\2\2kl\7v\2\2lm\7"+
		"j\2\2m\32\3\2\2\2np\t\2\2\2oq\t\3\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs"+
		"\3\2\2\2s\34\3\2\2\2tv\7)\2\2uw\t\4\2\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2"+
		"xy\3\2\2\2yz\3\2\2\2z{\7)\2\2{\36\3\2\2\2|~\4\62;\2}|\3\2\2\2~\177\3\2"+
		"\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080 \3\2\2\2\u0081\u0083\7.\2\2"+
		"\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085"+
		"\3\2\2\2\u0085\"\3\2\2\2\u0086\u0088\t\5\2\2\u0087\u0086\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a$\3\2\2\2"+
		"\u008b\u008d\7\17\2\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e"+
		"\3\2\2\2\u008e\u008f\7\f\2\2\u008f&\3\2\2\2\t\2rx\177\u0084\u0089\u008c"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}