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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, VARIABLE=15, STRING=16, 
		INT=17, COMMA=18, WS=19, NL=20;
	public static final int
		RULE_structure = 0, RULE_window = 1, RULE_querySequence = 2, RULE_querySingle = 3, 
		RULE_query = 4, RULE_negation = 5, RULE_reactions = 6, RULE_reaction = 7, 
		RULE_devInfo = 8, RULE_devInfoName = 9, RULE_domainInfo = 10, RULE_domainInfoName = 11, 
		RULE_cM = 12, RULE_cMProperty = 13, RULE_cMValue = 14, RULE_cMKey = 15, 
		RULE_windowType = 16, RULE_intValue = 17;
	public static final String[] ruleNames = {
		"structure", "window", "querySequence", "querySingle", "query", "negation", 
		"reactions", "reaction", "devInfo", "devInfoName", "domainInfo", "domainInfoName", 
		"cM", "cMProperty", "cMValue", "cMKey", "windowType", "intValue"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'TRIGGERS'", "'triggers'", "'('", "')'", "'->'", "'NOT'", "'not'", 
		"';'", "','", "'='", "'WIN:TIME'", "'win:time'", "'WIN:LENGTH'", "'win:length'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "VARIABLE", "STRING", "INT", "COMMA", "WS", "NL"
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
			setState(36);
			querySequence();
			setState(37);
			match(WS);
			setState(38);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(39);
			match(WS);
			setState(40);
			reactions();
			setState(42);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(41);
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
			setState(68);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				match(WS);
				setState(45);
				windowType();
				setState(46);
				match(T__2);
				setState(48);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(47);
					match(WS);
					}
				}

				setState(50);
				intValue();
				setState(52);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(51);
					match(WS);
					}
				}

				setState(54);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				match(WS);
				setState(57);
				windowType();
				setState(58);
				match(T__2);
				setState(60);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(59);
					match(WS);
					}
				}

				setState(62);
				intValue();
				setState(64);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(63);
					match(WS);
					}
				}

				setState(66);
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
		public QuerySingleContext querySingle() {
			return getRuleContext(QuerySingleContext.class,0);
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
			setState(81);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(70);
				querySingle();
				setState(72);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(71);
					match(WS);
					}
				}

				setState(74);
				match(T__4);
				setState(76);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(75);
					match(WS);
					}
				}

				setState(78);
				querySequence();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				querySingle();
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

	public static class QuerySingleContext extends ParserRuleContext {
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public TerminalNode WS() { return getToken(RuleParser.WS, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public QuerySingleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_querySingle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterQuerySingle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitQuerySingle(this);
		}
	}

	public final QuerySingleContext querySingle() throws RecognitionException {
		QuerySingleContext _localctx = new QuerySingleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_querySingle);
		try {
			setState(88);
			switch (_input.LA(1)) {
			case T__5:
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				negation();
				setState(84);
				match(WS);
				setState(85);
				query();
				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				query();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		enterRule(_localctx, 8, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
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

	public static class NegationContext extends ParserRuleContext {
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitNegation(this);
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_negation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
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
		enterRule(_localctx, 12, RULE_reactions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(94);
				reaction();
				setState(96);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(95);
					match(WS);
					}
				}

				setState(98);
				match(T__7);
				setState(100);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(99);
					match(WS);
					}
					break;
				}
				setState(102);
				reactions();
				}
				break;
			case 2:
				{
				setState(104);
				reaction();
				setState(106);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(105);
					match(T__7);
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
		enterRule(_localctx, 14, RULE_reaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			devInfo();
			setState(111);
			match(T__8);
			setState(112);
			domainInfo();
			setState(113);
			match(T__8);
			setState(114);
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
		enterRule(_localctx, 16, RULE_devInfo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(116);
				match(WS);
				}
			}

			setState(119);
			devInfoName();
			setState(121);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(120);
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
		enterRule(_localctx, 18, RULE_devInfoName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
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
		enterRule(_localctx, 20, RULE_domainInfo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(125);
				match(WS);
				}
			}

			setState(128);
			domainInfoName();
			setState(130);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(129);
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
		enterRule(_localctx, 22, RULE_domainInfoName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
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
		enterRule(_localctx, 24, RULE_cM);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
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
		enterRule(_localctx, 26, RULE_cMProperty);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(136);
				match(WS);
				}
			}

			setState(139);
			cMKey();
			setState(141);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(140);
				match(WS);
				}
			}

			setState(143);
			match(T__9);
			setState(145);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(144);
				match(WS);
				}
			}

			setState(147);
			cMValue();
			setState(149);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(148);
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
		enterRule(_localctx, 28, RULE_cMValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
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
		enterRule(_localctx, 30, RULE_cMKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
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
		enterRule(_localctx, 32, RULE_windowType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
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
		enterRule(_localctx, 34, RULE_intValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\26\u00a2\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\5\2-\n\2\3\3\3\3\3\3\3\3\5\3\63\n\3"+
		"\3\3\3\3\5\3\67\n\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3?\n\3\3\3\3\3\5\3C\n\3"+
		"\3\3\3\3\5\3G\n\3\3\4\3\4\5\4K\n\4\3\4\3\4\5\4O\n\4\3\4\3\4\3\4\5\4T\n"+
		"\4\3\5\3\5\3\5\3\5\3\5\5\5[\n\5\3\6\3\6\3\7\3\7\3\b\3\b\5\bc\n\b\3\b\3"+
		"\b\5\bg\n\b\3\b\3\b\3\b\3\b\5\bm\n\b\5\bo\n\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\5\nx\n\n\3\n\3\n\5\n|\n\n\3\13\3\13\3\f\5\f\u0081\n\f\3\f\3\f\5\f"+
		"\u0085\n\f\3\r\3\r\3\16\3\16\3\17\5\17\u008c\n\17\3\17\3\17\5\17\u0090"+
		"\n\17\3\17\3\17\5\17\u0094\n\17\3\17\3\17\5\17\u0098\n\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$\2\5\3\2\3\4\3\2\b\t\3\2\r\20\u00a5\2&\3\2\2\2\4F\3\2\2\2"+
		"\6S\3\2\2\2\bZ\3\2\2\2\n\\\3\2\2\2\f^\3\2\2\2\16n\3\2\2\2\20p\3\2\2\2"+
		"\22w\3\2\2\2\24}\3\2\2\2\26\u0080\3\2\2\2\30\u0086\3\2\2\2\32\u0088\3"+
		"\2\2\2\34\u008b\3\2\2\2\36\u0099\3\2\2\2 \u009b\3\2\2\2\"\u009d\3\2\2"+
		"\2$\u009f\3\2\2\2&\'\5\6\4\2\'(\7\25\2\2()\t\2\2\2)*\7\25\2\2*,\5\16\b"+
		"\2+-\5\4\3\2,+\3\2\2\2,-\3\2\2\2-\3\3\2\2\2./\7\25\2\2/\60\5\"\22\2\60"+
		"\62\7\5\2\2\61\63\7\25\2\2\62\61\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64"+
		"\66\5$\23\2\65\67\7\25\2\2\66\65\3\2\2\2\66\67\3\2\2\2\678\3\2\2\289\7"+
		"\6\2\29G\3\2\2\2:;\7\25\2\2;<\5\"\22\2<>\7\5\2\2=?\7\25\2\2>=\3\2\2\2"+
		">?\3\2\2\2?@\3\2\2\2@B\5$\23\2AC\7\25\2\2BA\3\2\2\2BC\3\2\2\2CD\3\2\2"+
		"\2DE\7\6\2\2EG\3\2\2\2F.\3\2\2\2F:\3\2\2\2G\5\3\2\2\2HJ\5\b\5\2IK\7\25"+
		"\2\2JI\3\2\2\2JK\3\2\2\2KL\3\2\2\2LN\7\7\2\2MO\7\25\2\2NM\3\2\2\2NO\3"+
		"\2\2\2OP\3\2\2\2PQ\5\6\4\2QT\3\2\2\2RT\5\b\5\2SH\3\2\2\2SR\3\2\2\2T\7"+
		"\3\2\2\2UV\5\f\7\2VW\7\25\2\2WX\5\n\6\2X[\3\2\2\2Y[\5\n\6\2ZU\3\2\2\2"+
		"ZY\3\2\2\2[\t\3\2\2\2\\]\7\21\2\2]\13\3\2\2\2^_\t\3\2\2_\r\3\2\2\2`b\5"+
		"\20\t\2ac\7\25\2\2ba\3\2\2\2bc\3\2\2\2cd\3\2\2\2df\7\n\2\2eg\7\25\2\2"+
		"fe\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\5\16\b\2io\3\2\2\2jl\5\20\t\2km\7\n\2"+
		"\2lk\3\2\2\2lm\3\2\2\2mo\3\2\2\2n`\3\2\2\2nj\3\2\2\2o\17\3\2\2\2pq\5\22"+
		"\n\2qr\7\13\2\2rs\5\26\f\2st\7\13\2\2tu\5\32\16\2u\21\3\2\2\2vx\7\25\2"+
		"\2wv\3\2\2\2wx\3\2\2\2xy\3\2\2\2y{\5\24\13\2z|\7\25\2\2{z\3\2\2\2{|\3"+
		"\2\2\2|\23\3\2\2\2}~\7\21\2\2~\25\3\2\2\2\177\u0081\7\25\2\2\u0080\177"+
		"\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\5\30\r\2"+
		"\u0083\u0085\7\25\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\27"+
		"\3\2\2\2\u0086\u0087\7\21\2\2\u0087\31\3\2\2\2\u0088\u0089\5\34\17\2\u0089"+
		"\33\3\2\2\2\u008a\u008c\7\25\2\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2"+
		"\2\u008c\u008d\3\2\2\2\u008d\u008f\5 \21\2\u008e\u0090\7\25\2\2\u008f"+
		"\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\7\f"+
		"\2\2\u0092\u0094\7\25\2\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0097\5\36\20\2\u0096\u0098\7\25\2\2\u0097\u0096"+
		"\3\2\2\2\u0097\u0098\3\2\2\2\u0098\35\3\2\2\2\u0099\u009a\7\23\2\2\u009a"+
		"\37\3\2\2\2\u009b\u009c\7\21\2\2\u009c!\3\2\2\2\u009d\u009e\t\4\2\2\u009e"+
		"#\3\2\2\2\u009f\u00a0\7\23\2\2\u00a0%\3\2\2\2\30,\62\66>BFJNSZbflnw{\u0080"+
		"\u0084\u008b\u008f\u0093\u0097";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}