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
public class RuleLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, VARIABLE=8, STRING=9, 
		INT=10, COMMA=11, WS=12, NL=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "VARIABLE", "STRING", 
		"INT", "COMMA", "WS", "NL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'TRIGGERS'", "';'", "','", "'deviceInformation'", "'='", "'domainInformation'", 
		"'configurationModification'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "VARIABLE", "STRING", 
		"INT", "COMMA", "WS", "NL"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17\u008c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\6\tm\n\t\r\t\16\tn\3\n\3\n\6\ns\n\n\r\n\16\nt\3\n\3\n\3\13\6\13z\n\13"+
		"\r\13\16\13{\3\f\6\f\177\n\f\r\f\16\f\u0080\3\r\6\r\u0084\n\r\r\r\16\r"+
		"\u0085\3\16\5\16\u0089\n\16\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\3\2\6\4\2C\\c|\7\2//\62;C\\aac"+
		"|\5\2\62;C\\c|\4\2\13\13\"\"\u0091\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2"+
		"\2\5&\3\2\2\2\7(\3\2\2\2\t*\3\2\2\2\13<\3\2\2\2\r>\3\2\2\2\17P\3\2\2\2"+
		"\21j\3\2\2\2\23p\3\2\2\2\25y\3\2\2\2\27~\3\2\2\2\31\u0083\3\2\2\2\33\u0088"+
		"\3\2\2\2\35\36\7V\2\2\36\37\7T\2\2\37 \7K\2\2 !\7I\2\2!\"\7I\2\2\"#\7"+
		"G\2\2#$\7T\2\2$%\7U\2\2%\4\3\2\2\2&\'\7=\2\2\'\6\3\2\2\2()\7.\2\2)\b\3"+
		"\2\2\2*+\7f\2\2+,\7g\2\2,-\7x\2\2-.\7k\2\2./\7e\2\2/\60\7g\2\2\60\61\7"+
		"K\2\2\61\62\7p\2\2\62\63\7h\2\2\63\64\7q\2\2\64\65\7t\2\2\65\66\7o\2\2"+
		"\66\67\7c\2\2\678\7v\2\289\7k\2\29:\7q\2\2:;\7p\2\2;\n\3\2\2\2<=\7?\2"+
		"\2=\f\3\2\2\2>?\7f\2\2?@\7q\2\2@A\7o\2\2AB\7c\2\2BC\7k\2\2CD\7p\2\2DE"+
		"\7K\2\2EF\7p\2\2FG\7h\2\2GH\7q\2\2HI\7t\2\2IJ\7o\2\2JK\7c\2\2KL\7v\2\2"+
		"LM\7k\2\2MN\7q\2\2NO\7p\2\2O\16\3\2\2\2PQ\7e\2\2QR\7q\2\2RS\7p\2\2ST\7"+
		"h\2\2TU\7k\2\2UV\7i\2\2VW\7w\2\2WX\7t\2\2XY\7c\2\2YZ\7v\2\2Z[\7k\2\2["+
		"\\\7q\2\2\\]\7p\2\2]^\7O\2\2^_\7q\2\2_`\7f\2\2`a\7k\2\2ab\7h\2\2bc\7k"+
		"\2\2cd\7e\2\2de\7c\2\2ef\7v\2\2fg\7k\2\2gh\7q\2\2hi\7p\2\2i\20\3\2\2\2"+
		"jl\t\2\2\2km\t\3\2\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2o\22\3\2\2"+
		"\2pr\7)\2\2qs\t\4\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2uv\3\2\2"+
		"\2vw\7)\2\2w\24\3\2\2\2xz\4\62;\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2"+
		"\2\2|\26\3\2\2\2}\177\7.\2\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2"+
		"\2\u0080\u0081\3\2\2\2\u0081\30\3\2\2\2\u0082\u0084\t\5\2\2\u0083\u0082"+
		"\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086"+
		"\32\3\2\2\2\u0087\u0089\7\17\2\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2"+
		"\2\u0089\u008a\3\2\2\2\u008a\u008b\7\f\2\2\u008b\34\3\2\2\2\t\2nt{\u0080"+
		"\u0085\u0088\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}