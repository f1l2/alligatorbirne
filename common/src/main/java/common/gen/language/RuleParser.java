// Generated from Rule.g4 by ANTLR 4.5.1

	package common.gen.language;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, VARIABLE=13, STRING=14, INT=15, COMMA=16, 
		WS=17, NL=18;
	public static final int
		RULE_structure = 0, RULE_window = 1, RULE_querySequence = 2, RULE_query = 3, 
		RULE_reactions = 4, RULE_reaction = 5, RULE_devInfo = 6, RULE_devInfoName = 7, 
		RULE_domainInfo = 8, RULE_domainInfoName = 9, RULE_cM = 10, RULE_cMProperty = 11, 
		RULE_cMValue = 12, RULE_cMKey = 13, RULE_windowType = 14, RULE_intValue = 15;
	public static final String[] ruleNames = {
		"structure", "window", "querySequence", "query", "reactions", "reaction", 
		"devInfo", "devInfoName", "domainInfo", "domainInfoName", "cM", "cMProperty", 
		"cMValue", "cMKey", "windowType", "intValue"
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
		public QuerySequenceContext querySequence() {
			return getRuleContext(QuerySequenceContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public ReactionsContext reactions() {
			return getRuleContext(ReactionsContext.class,0);
		}
		public WindowContext window() {
			return getRuleContext(WindowContext.class,0);
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
			setState(32);
			querySequence();
			setState(33);
			match(WS);
			setState(34);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(35);
			match(WS);
			setState(36);
			reactions();
			setState(38);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(37);
				window();
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

	public static class WindowContext extends ParserRuleContext {
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public WindowTypeContext windowType() {
			return getRuleContext(WindowTypeContext.class,0);
		}
		public IntValueContext intValue() {
			return getRuleContext(IntValueContext.class,0);
		}
		public WindowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_window; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterWindow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitWindow(this);
		}
	}

	public final WindowContext window() throws RecognitionException {
		WindowContext _localctx = new WindowContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_window);
		int _la;
		try {
			setState(64);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				match(WS);
				setState(41);
				windowType();
				setState(42);
				match(T__2);
				setState(44);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(43);
					match(WS);
					}
				}

				setState(46);
				intValue();
				setState(48);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(47);
					match(WS);
					}
				}

				setState(50);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(WS);
				setState(53);
				windowType();
				setState(54);
				match(T__2);
				setState(56);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(55);
					match(WS);
					}
				}

				setState(58);
				intValue();
				setState(60);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(59);
					match(WS);
					}
				}

				setState(62);
				match(T__3);
				}
				break;
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

	public static class QuerySequenceContext extends ParserRuleContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public QuerySequenceContext querySequence() {
			return getRuleContext(QuerySequenceContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public QuerySequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_querySequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterQuerySequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitQuerySequence(this);
		}
	}

	public final QuerySequenceContext querySequence() throws RecognitionException {
		QuerySequenceContext _localctx = new QuerySequenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_querySequence);
		int _la;
		try {
			setState(77);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(66);
				query();
				setState(68);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(67);
					match(WS);
					}
				}

				setState(70);
				match(T__4);
				setState(72);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(71);
					match(WS);
					}
				}

				setState(74);
				querySequence();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				query();
				}
				break;
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
		enterRule(_localctx, 6, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
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
		enterRule(_localctx, 8, RULE_reactions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(81);
				reaction();
				setState(83);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(82);
					match(WS);
					}
				}

				setState(85);
				match(T__5);
				setState(87);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(86);
					match(WS);
					}
					break;
				}
				setState(89);
				reactions();
				}
				break;
			case 2:
				{
				setState(91);
				reaction();
				setState(93);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(92);
					match(T__5);
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
		enterRule(_localctx, 10, RULE_reaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			devInfo();
			setState(98);
			match(T__6);
			setState(99);
			domainInfo();
			setState(100);
			match(T__6);
			setState(101);
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
		enterRule(_localctx, 12, RULE_devInfo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(103);
				match(WS);
				}
			}

			setState(106);
			devInfoName();
			setState(108);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(107);
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
		enterRule(_localctx, 14, RULE_devInfoName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
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
		enterRule(_localctx, 16, RULE_domainInfo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(112);
				match(WS);
				}
			}

			setState(115);
			domainInfoName();
			setState(117);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(116);
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
		enterRule(_localctx, 18, RULE_domainInfoName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
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
		enterRule(_localctx, 20, RULE_cM);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
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
		enterRule(_localctx, 22, RULE_cMProperty);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(123);
				match(WS);
				}
			}

			setState(126);
			cMKey();
			setState(128);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(127);
				match(WS);
				}
			}

			setState(130);
			match(T__7);
			setState(132);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(131);
				match(WS);
				}
			}

			setState(134);
			cMValue();
			setState(136);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(135);
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
		enterRule(_localctx, 24, RULE_cMValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
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
		enterRule(_localctx, 26, RULE_cMKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
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

	public static class WindowTypeContext extends ParserRuleContext {
		public WindowTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_windowType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterWindowType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitWindowType(this);
		}
	}

	public final WindowTypeContext windowType() throws RecognitionException {
		WindowTypeContext _localctx = new WindowTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_windowType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class IntValueContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(RuleParser.INT, 0); }
		public IntValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterIntValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitIntValue(this);
		}
	}

	public final IntValueContext intValue() throws RecognitionException {
		IntValueContext _localctx = new IntValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_intValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\24\u0095\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\5\2)\n\2\3\3\3\3\3\3\3\3\5\3/\n\3\3\3\3\3\5\3\63\n\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\5\3;\n\3\3\3\3\3\5\3?\n\3\3\3\3\3\5\3C\n\3\3"+
		"\4\3\4\5\4G\n\4\3\4\3\4\5\4K\n\4\3\4\3\4\3\4\5\4P\n\4\3\5\3\5\3\6\3\6"+
		"\5\6V\n\6\3\6\3\6\5\6Z\n\6\3\6\3\6\3\6\3\6\5\6`\n\6\5\6b\n\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\5\bk\n\b\3\b\3\b\5\bo\n\b\3\t\3\t\3\n\5\nt\n\n\3\n"+
		"\3\n\5\nx\n\n\3\13\3\13\3\f\3\f\3\r\5\r\177\n\r\3\r\3\r\5\r\u0083\n\r"+
		"\3\r\3\r\5\r\u0087\n\r\3\r\3\r\5\r\u008b\n\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2"+
		"\4\3\2\3\4\3\2\13\16\u0099\2\"\3\2\2\2\4B\3\2\2\2\6O\3\2\2\2\bQ\3\2\2"+
		"\2\na\3\2\2\2\fc\3\2\2\2\16j\3\2\2\2\20p\3\2\2\2\22s\3\2\2\2\24y\3\2\2"+
		"\2\26{\3\2\2\2\30~\3\2\2\2\32\u008c\3\2\2\2\34\u008e\3\2\2\2\36\u0090"+
		"\3\2\2\2 \u0092\3\2\2\2\"#\5\6\4\2#$\7\23\2\2$%\t\2\2\2%&\7\23\2\2&(\5"+
		"\n\6\2\')\5\4\3\2(\'\3\2\2\2()\3\2\2\2)\3\3\2\2\2*+\7\23\2\2+,\5\36\20"+
		"\2,.\7\5\2\2-/\7\23\2\2.-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\62\5 \21\2"+
		"\61\63\7\23\2\2\62\61\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\7\6\2"+
		"\2\65C\3\2\2\2\66\67\7\23\2\2\678\5\36\20\28:\7\5\2\29;\7\23\2\2:9\3\2"+
		"\2\2:;\3\2\2\2;<\3\2\2\2<>\5 \21\2=?\7\23\2\2>=\3\2\2\2>?\3\2\2\2?@\3"+
		"\2\2\2@A\7\6\2\2AC\3\2\2\2B*\3\2\2\2B\66\3\2\2\2C\5\3\2\2\2DF\5\b\5\2"+
		"EG\7\23\2\2FE\3\2\2\2FG\3\2\2\2GH\3\2\2\2HJ\7\7\2\2IK\7\23\2\2JI\3\2\2"+
		"\2JK\3\2\2\2KL\3\2\2\2LM\5\6\4\2MP\3\2\2\2NP\5\b\5\2OD\3\2\2\2ON\3\2\2"+
		"\2P\7\3\2\2\2QR\7\17\2\2R\t\3\2\2\2SU\5\f\7\2TV\7\23\2\2UT\3\2\2\2UV\3"+
		"\2\2\2VW\3\2\2\2WY\7\b\2\2XZ\7\23\2\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\"+
		"\5\n\6\2\\b\3\2\2\2]_\5\f\7\2^`\7\b\2\2_^\3\2\2\2_`\3\2\2\2`b\3\2\2\2"+
		"aS\3\2\2\2a]\3\2\2\2b\13\3\2\2\2cd\5\16\b\2de\7\t\2\2ef\5\22\n\2fg\7\t"+
		"\2\2gh\5\26\f\2h\r\3\2\2\2ik\7\23\2\2ji\3\2\2\2jk\3\2\2\2kl\3\2\2\2ln"+
		"\5\20\t\2mo\7\23\2\2nm\3\2\2\2no\3\2\2\2o\17\3\2\2\2pq\7\17\2\2q\21\3"+
		"\2\2\2rt\7\23\2\2sr\3\2\2\2st\3\2\2\2tu\3\2\2\2uw\5\24\13\2vx\7\23\2\2"+
		"wv\3\2\2\2wx\3\2\2\2x\23\3\2\2\2yz\7\17\2\2z\25\3\2\2\2{|\5\30\r\2|\27"+
		"\3\2\2\2}\177\7\23\2\2~}\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u0082\5\34\17\2\u0081\u0083\7\23\2\2\u0082\u0081\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\7\n\2\2\u0085\u0087\7\23\2\2"+
		"\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a"+
		"\5\32\16\2\u0089\u008b\7\23\2\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2"+
		"\2\u008b\31\3\2\2\2\u008c\u008d\7\21\2\2\u008d\33\3\2\2\2\u008e\u008f"+
		"\7\17\2\2\u008f\35\3\2\2\2\u0090\u0091\t\3\2\2\u0091\37\3\2\2\2\u0092"+
		"\u0093\7\21\2\2\u0093!\3\2\2\2\27(.\62:>BFJOUY_ajnsw~\u0082\u0086\u008a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}