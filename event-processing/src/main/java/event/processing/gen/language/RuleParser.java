// Generated from Rule.g4 by ANTLR 4.5.1

	package event.processing.gen.language;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, VARIABLE=6, STRING=7, INT=8, COMMA=9, 
		WS=10, NL=11;
	public static final int
		RULE_structure = 0, RULE_query = 1, RULE_reactions = 2, RULE_reaction = 3, 
		RULE_devInfo = 4, RULE_devInfoName = 5, RULE_domainInfo = 6, RULE_domainInfoName = 7, 
		RULE_cM = 8, RULE_cMProperty = 9, RULE_cMValue = 10, RULE_cMKey = 11;
	public static final String[] ruleNames = {
		"structure", "query", "reactions", "reaction", "devInfo", "devInfoName", 
		"domainInfo", "domainInfoName", "cM", "cMProperty", "cMValue", "cMKey"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'TRIGGERS'", "'triggers'", "';'", "','", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "VARIABLE", "STRING", "INT", "COMMA", 
		"WS", "NL"
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			query();
			setState(25);
			match(WS);
			setState(26);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(27);
			match(WS);
			setState(28);
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
			setState(30);
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
			setState(46);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(32);
				reaction();
				setState(34);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(33);
					match(WS);
					}
				}

				setState(36);
				match(T__2);
				setState(38);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(37);
					match(WS);
					}
					break;
				}
				setState(40);
				reactions();
				}
				break;
			case 2:
				{
				setState(42);
				reaction();
				setState(44);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(43);
					match(T__2);
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
			setState(48);
			devInfo();
			setState(49);
			match(T__3);
			setState(50);
			domainInfo();
			setState(51);
			match(T__3);
			setState(52);
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
			setState(55);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(54);
				match(WS);
				}
			}

			setState(57);
			devInfoName();
			setState(59);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(58);
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
			setState(61);
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
			setState(64);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(63);
				match(WS);
				}
			}

			setState(66);
			domainInfoName();
			setState(68);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(67);
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
			setState(70);
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
		public CMPropertyContext cMProperty() {
			return getRuleContext(CMPropertyContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			cMProperty();
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

	public static class CMPropertyContext extends ParserRuleContext {
		public CMKeyContext cMKey() {
			return getRuleContext(CMKeyContext.class,0);
		}
		public CMValueContext cMValue() {
			return getRuleContext(CMValueContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public CMPropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cMProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterCMProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitCMProperty(this);
		}
	}

	public final CMPropertyContext cMProperty() throws RecognitionException {
		CMPropertyContext _localctx = new CMPropertyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cMProperty);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(74);
				match(WS);
				}
			}

			setState(77);
			cMKey();
			setState(79);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(78);
				match(WS);
				}
			}

			setState(81);
			match(T__4);
			setState(83);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(82);
				match(WS);
				}
			}

			setState(85);
			cMValue();
			setState(87);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(86);
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

	public static class CMValueContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(RuleParser.INT, 0); }
		public CMValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cMValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterCMValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitCMValue(this);
		}
	}

	public final CMValueContext cMValue() throws RecognitionException {
		CMValueContext _localctx = new CMValueContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cMValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(INT);
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

	public static class CMKeyContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(RuleParser.VARIABLE, 0); }
		public CMKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cMKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterCMKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitCMKey(this);
		}
	}

	public final CMKeyContext cMKey() throws RecognitionException {
		CMKeyContext _localctx = new CMKeyContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cMKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\r`\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\5\4%\n\4\3\4\3"+
		"\4\5\4)\n\4\3\4\3\4\3\4\3\4\5\4/\n\4\5\4\61\n\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\5\6:\n\6\3\6\3\6\5\6>\n\6\3\7\3\7\3\b\5\bC\n\b\3\b\3\b\5\bG\n\b"+
		"\3\t\3\t\3\n\3\n\3\13\5\13N\n\13\3\13\3\13\5\13R\n\13\3\13\3\13\5\13V"+
		"\n\13\3\13\3\13\5\13Z\n\13\3\f\3\f\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\2\3\3\2\3\4_\2\32\3\2\2\2\4 \3\2\2\2\6\60\3\2\2\2\b\62\3"+
		"\2\2\2\n9\3\2\2\2\f?\3\2\2\2\16B\3\2\2\2\20H\3\2\2\2\22J\3\2\2\2\24M\3"+
		"\2\2\2\26[\3\2\2\2\30]\3\2\2\2\32\33\5\4\3\2\33\34\7\f\2\2\34\35\t\2\2"+
		"\2\35\36\7\f\2\2\36\37\5\6\4\2\37\3\3\2\2\2 !\7\b\2\2!\5\3\2\2\2\"$\5"+
		"\b\5\2#%\7\f\2\2$#\3\2\2\2$%\3\2\2\2%&\3\2\2\2&(\7\5\2\2\')\7\f\2\2(\'"+
		"\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\5\6\4\2+\61\3\2\2\2,.\5\b\5\2-/\7\5\2\2"+
		".-\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60\"\3\2\2\2\60,\3\2\2\2\61\7\3\2\2\2"+
		"\62\63\5\n\6\2\63\64\7\6\2\2\64\65\5\16\b\2\65\66\7\6\2\2\66\67\5\22\n"+
		"\2\67\t\3\2\2\28:\7\f\2\298\3\2\2\29:\3\2\2\2:;\3\2\2\2;=\5\f\7\2<>\7"+
		"\f\2\2=<\3\2\2\2=>\3\2\2\2>\13\3\2\2\2?@\7\b\2\2@\r\3\2\2\2AC\7\f\2\2"+
		"BA\3\2\2\2BC\3\2\2\2CD\3\2\2\2DF\5\20\t\2EG\7\f\2\2FE\3\2\2\2FG\3\2\2"+
		"\2G\17\3\2\2\2HI\7\b\2\2I\21\3\2\2\2JK\5\24\13\2K\23\3\2\2\2LN\7\f\2\2"+
		"ML\3\2\2\2MN\3\2\2\2NO\3\2\2\2OQ\5\30\r\2PR\7\f\2\2QP\3\2\2\2QR\3\2\2"+
		"\2RS\3\2\2\2SU\7\7\2\2TV\7\f\2\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WY\5\26"+
		"\f\2XZ\7\f\2\2YX\3\2\2\2YZ\3\2\2\2Z\25\3\2\2\2[\\\7\n\2\2\\\27\3\2\2\2"+
		"]^\7\b\2\2^\31\3\2\2\2\16$(.\609=BFMQUY";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}