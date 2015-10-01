// Generated from Rule.g4 by ANTLR 4.5.1

	package event.processing.query.language;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RuleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, VARIABLE=4, STRING=5, INT=6, COMMA=7, WS=8, NL=9;
	public static final int
		RULE_structure = 0, RULE_query = 1, RULE_reactions = 2, RULE_reaction = 3, 
		RULE_devInfo = 4, RULE_devInfoName = 5, RULE_domainInfo = 6, RULE_domainInfoName = 7, 
		RULE_cM = 8, RULE_cMName = 9;
	public static final String[] ruleNames = {
		"structure", "query", "reactions", "reaction", "devInfo", "devInfoName", 
		"domainInfo", "domainInfoName", "cM", "cMName"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'TRIGGERS'", "';'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "VARIABLE", "STRING", "INT", "COMMA", "WS", "NL"
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

	@Override
	public String getGrammarFileName() { return "Rule.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RuleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StructureContext extends ParserRuleContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public ReactionsContext reactions() {
			return getRuleContext(ReactionsContext.class,0);
		}
		public StructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitStructure(this);
		}
	}

	public final StructureContext structure() throws RecognitionException {
		StructureContext _localctx = new StructureContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_structure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			query();
			setState(21);
			match(WS);
			setState(22);
			match(T__0);
			setState(23);
			match(WS);
			setState(24);
			reactions();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(RuleParser.VARIABLE, 0); }
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReactionsContext extends ParserRuleContext {
		public ReactionContext reaction() {
			return getRuleContext(ReactionContext.class,0);
		}
		public ReactionsContext reactions() {
			return getRuleContext(ReactionsContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public ReactionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reactions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterReactions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitReactions(this);
		}
	}

	public final ReactionsContext reactions() throws RecognitionException {
		ReactionsContext _localctx = new ReactionsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_reactions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(28);
				reaction();
				setState(30);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(29);
					match(WS);
					}
				}

				setState(32);
				match(T__1);
				setState(34);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(33);
					match(WS);
					}
					break;
				}
				setState(36);
				reactions();
				}
				break;
			case 2:
				{
				setState(38);
				reaction();
				setState(40);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(39);
					match(T__1);
					}
				}

				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReactionContext extends ParserRuleContext {
		public DevInfoContext devInfo() {
			return getRuleContext(DevInfoContext.class,0);
		}
		public DomainInfoContext domainInfo() {
			return getRuleContext(DomainInfoContext.class,0);
		}
		public CMContext cM() {
			return getRuleContext(CMContext.class,0);
		}
		public ReactionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reaction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterReaction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitReaction(this);
		}
	}

	public final ReactionContext reaction() throws RecognitionException {
		ReactionContext _localctx = new ReactionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_reaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			devInfo();
			setState(45);
			match(T__2);
			setState(46);
			domainInfo();
			setState(47);
			match(T__2);
			setState(48);
			cM();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DevInfoContext extends ParserRuleContext {
		public DevInfoNameContext devInfoName() {
			return getRuleContext(DevInfoNameContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public DevInfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_devInfo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterDevInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitDevInfo(this);
		}
	}

	public final DevInfoContext devInfo() throws RecognitionException {
		DevInfoContext _localctx = new DevInfoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_devInfo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(50);
				match(WS);
				}
			}

			setState(53);
			devInfoName();
			setState(55);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(54);
				match(WS);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DevInfoNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(RuleParser.VARIABLE, 0); }
		public DevInfoNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_devInfoName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterDevInfoName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitDevInfoName(this);
		}
	}

	public final DevInfoNameContext devInfoName() throws RecognitionException {
		DevInfoNameContext _localctx = new DevInfoNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_devInfoName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainInfoContext extends ParserRuleContext {
		public DomainInfoNameContext domainInfoName() {
			return getRuleContext(DomainInfoNameContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public DomainInfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainInfo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterDomainInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitDomainInfo(this);
		}
	}

	public final DomainInfoContext domainInfo() throws RecognitionException {
		DomainInfoContext _localctx = new DomainInfoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_domainInfo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(59);
				match(WS);
				}
			}

			setState(62);
			domainInfoName();
			setState(64);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(63);
				match(WS);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainInfoNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(RuleParser.VARIABLE, 0); }
		public DomainInfoNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainInfoName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterDomainInfoName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitDomainInfoName(this);
		}
	}

	public final DomainInfoNameContext domainInfoName() throws RecognitionException {
		DomainInfoNameContext _localctx = new DomainInfoNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_domainInfoName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CMContext extends ParserRuleContext {
		public CMNameContext cMName() {
			return getRuleContext(CMNameContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public CMContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cM; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterCM(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitCM(this);
		}
	}

	public final CMContext cM() throws RecognitionException {
		CMContext _localctx = new CMContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cM);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(68);
				match(WS);
				}
			}

			setState(71);
			cMName();
			setState(73);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(72);
				match(WS);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CMNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(RuleParser.VARIABLE, 0); }
		public CMNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cMName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterCMName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitCMName(this);
		}
	}

	public final CMNameContext cMName() throws RecognitionException {
		CMNameContext _localctx = new CMNameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cMName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13P\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\5\4!\n\4\3\4\3\4\5\4%\n\4\3\4\3"+
		"\4\3\4\3\4\5\4+\n\4\5\4-\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\5\6\66\n\6\3"+
		"\6\3\6\5\6:\n\6\3\7\3\7\3\b\5\b?\n\b\3\b\3\b\5\bC\n\b\3\t\3\t\3\n\5\n"+
		"H\n\n\3\n\3\n\5\nL\n\n\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2"+
		"\2O\2\26\3\2\2\2\4\34\3\2\2\2\6,\3\2\2\2\b.\3\2\2\2\n\65\3\2\2\2\f;\3"+
		"\2\2\2\16>\3\2\2\2\20D\3\2\2\2\22G\3\2\2\2\24M\3\2\2\2\26\27\5\4\3\2\27"+
		"\30\7\n\2\2\30\31\7\3\2\2\31\32\7\n\2\2\32\33\5\6\4\2\33\3\3\2\2\2\34"+
		"\35\7\6\2\2\35\5\3\2\2\2\36 \5\b\5\2\37!\7\n\2\2 \37\3\2\2\2 !\3\2\2\2"+
		"!\"\3\2\2\2\"$\7\4\2\2#%\7\n\2\2$#\3\2\2\2$%\3\2\2\2%&\3\2\2\2&\'\5\6"+
		"\4\2\'-\3\2\2\2(*\5\b\5\2)+\7\4\2\2*)\3\2\2\2*+\3\2\2\2+-\3\2\2\2,\36"+
		"\3\2\2\2,(\3\2\2\2-\7\3\2\2\2./\5\n\6\2/\60\7\5\2\2\60\61\5\16\b\2\61"+
		"\62\7\5\2\2\62\63\5\22\n\2\63\t\3\2\2\2\64\66\7\n\2\2\65\64\3\2\2\2\65"+
		"\66\3\2\2\2\66\67\3\2\2\2\679\5\f\7\28:\7\n\2\298\3\2\2\29:\3\2\2\2:\13"+
		"\3\2\2\2;<\7\6\2\2<\r\3\2\2\2=?\7\n\2\2>=\3\2\2\2>?\3\2\2\2?@\3\2\2\2"+
		"@B\5\20\t\2AC\7\n\2\2BA\3\2\2\2BC\3\2\2\2C\17\3\2\2\2DE\7\6\2\2E\21\3"+
		"\2\2\2FH\7\n\2\2GF\3\2\2\2GH\3\2\2\2HI\3\2\2\2IK\5\24\13\2JL\7\n\2\2K"+
		"J\3\2\2\2KL\3\2\2\2L\23\3\2\2\2MN\7\6\2\2N\25\3\2\2\2\f $*,\659>BGK";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}