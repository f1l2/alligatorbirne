// Generated from Rule.g4 by ANTLR 4.5.1

	package event.processing.gen.language;

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
		null, "'TRIGGERS'", "'triggers'", "'->'", "';'", "','", "'='"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\16X\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\6\b"+
		"9\n\b\r\b\16\b:\3\t\3\t\6\t?\n\t\r\t\16\t@\3\t\3\t\3\n\6\nF\n\n\r\n\16"+
		"\nG\3\13\6\13K\n\13\r\13\16\13L\3\f\6\fP\n\f\r\f\16\fQ\3\r\5\rU\n\r\3"+
		"\r\3\r\2\2\16\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\3\2\6\4\2C\\c|\7\2//\62;C\\aac|\5\2\62;C\\c|\4\2\13\13\"\"]\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\3\33\3\2\2\2\5$\3\2\2\2\7-\3\2\2\2\t\60\3\2\2\2\13\62\3\2\2\2\r\64"+
		"\3\2\2\2\17\66\3\2\2\2\21<\3\2\2\2\23E\3\2\2\2\25J\3\2\2\2\27O\3\2\2\2"+
		"\31T\3\2\2\2\33\34\7V\2\2\34\35\7T\2\2\35\36\7K\2\2\36\37\7I\2\2\37 \7"+
		"I\2\2 !\7G\2\2!\"\7T\2\2\"#\7U\2\2#\4\3\2\2\2$%\7v\2\2%&\7t\2\2&\'\7k"+
		"\2\2\'(\7i\2\2()\7i\2\2)*\7g\2\2*+\7t\2\2+,\7u\2\2,\6\3\2\2\2-.\7/\2\2"+
		"./\7@\2\2/\b\3\2\2\2\60\61\7=\2\2\61\n\3\2\2\2\62\63\7.\2\2\63\f\3\2\2"+
		"\2\64\65\7?\2\2\65\16\3\2\2\2\668\t\2\2\2\679\t\3\2\28\67\3\2\2\29:\3"+
		"\2\2\2:8\3\2\2\2:;\3\2\2\2;\20\3\2\2\2<>\7)\2\2=?\t\4\2\2>=\3\2\2\2?@"+
		"\3\2\2\2@>\3\2\2\2@A\3\2\2\2AB\3\2\2\2BC\7)\2\2C\22\3\2\2\2DF\4\62;\2"+
		"ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2H\24\3\2\2\2IK\7.\2\2JI\3\2\2"+
		"\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\26\3\2\2\2NP\t\5\2\2ON\3\2\2\2PQ\3\2"+
		"\2\2QO\3\2\2\2QR\3\2\2\2R\30\3\2\2\2SU\7\17\2\2TS\3\2\2\2TU\3\2\2\2UV"+
		"\3\2\2\2VW\7\f\2\2W\32\3\2\2\2\t\2:@GLQT\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}