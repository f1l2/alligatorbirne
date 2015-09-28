// Generated from Query.g4 by ANTLR 4.5.1

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
public class QueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, VARIABLE=15, STRING=16, 
		INT=17, COMMA=18, WS=19, NL=20, OPERATOR=21;
	public static final int
		RULE_query = 0, RULE_window = 1, RULE_domain = 2, RULE_domainlist = 3, 
		RULE_domainName = 4, RULE_condition = 5, RULE_compare = 6, RULE_logicLink = 7, 
		RULE_aggregateCompare = 8, RULE_property = 9, RULE_aggregate = 10;
	public static final String[] ruleNames = {
		"query", "window", "domain", "domainlist", "domainName", "condition", 
		"compare", "logicLink", "aggregateCompare", "property", "aggregate"
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

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode WS() { return getToken(QueryParser.WS, 0); }
		public DomainContext domain() {
			return getRuleContext(DomainContext.class,0);
		}
		public WindowContext window() {
			return getRuleContext(WindowContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			condition();
			setState(23);
			match(WS);
			setState(25);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(24);
				domain();
				}
			}

			setState(28);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(27);
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
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public TerminalNode INT() { return getToken(QueryParser.INT, 0); }
		public WindowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_window; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterWindow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitWindow(this);
		}
	}

	public final WindowContext window() throws RecognitionException {
		WindowContext _localctx = new WindowContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_window);
		int _la;
		try {
			setState(50);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				match(WS);
				setState(31);
				match(T__0);
				setState(33);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(32);
					match(WS);
					}
				}

				setState(35);
				match(INT);
				setState(37);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(36);
					match(WS);
					}
				}

				setState(39);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				match(WS);
				setState(41);
				match(T__2);
				setState(43);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(42);
					match(WS);
					}
				}

				setState(45);
				match(INT);
				setState(47);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(46);
					match(WS);
					}
				}

				setState(49);
				match(T__1);
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

	public static class DomainContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(QueryParser.WS, 0); }
		public DomainlistContext domainlist() {
			return getRuleContext(DomainlistContext.class,0);
		}
		public DomainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterDomain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitDomain(this);
		}
	}

	public final DomainContext domain() throws RecognitionException {
		DomainContext _localctx = new DomainContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_domain);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(52);
			match(T__3);
			setState(53);
			match(WS);
			setState(54);
			domainlist(0);
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

	public static class DomainlistContext extends ParserRuleContext {
		public DomainNameContext domainName() {
			return getRuleContext(DomainNameContext.class,0);
		}
		public List<DomainlistContext> domainlist() {
			return getRuleContexts(DomainlistContext.class);
		}
		public DomainlistContext domainlist(int i) {
			return getRuleContext(DomainlistContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(QueryParser.COMMA, 0); }
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public DomainlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterDomainlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitDomainlist(this);
		}
	}

	public final DomainlistContext domainlist() throws RecognitionException {
		return domainlist(0);
	}

	private DomainlistContext domainlist(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DomainlistContext _localctx = new DomainlistContext(_ctx, _parentState);
		DomainlistContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_domainlist, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(57);
			domainName();
			}
			_ctx.stop = _input.LT(-1);
			setState(70);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DomainlistContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_domainlist);
					setState(59);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(61);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(60);
						match(WS);
						}
					}

					setState(63);
					match(COMMA);
					setState(65);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(64);
						match(WS);
						}
					}

					setState(67);
					domainlist(2);
					}
					} 
				}
				setState(72);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DomainNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(QueryParser.VARIABLE, 0); }
		public DomainNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterDomainName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitDomainName(this);
		}
	}

	public final DomainNameContext domainName() throws RecognitionException {
		DomainNameContext _localctx = new DomainNameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_domainName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
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

	public static class ConditionContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(QueryParser.WS, 0); }
		public CompareContext compare() {
			return getRuleContext(CompareContext.class,0);
		}
		public LogicLinkContext logicLink() {
			return getRuleContext(LogicLinkContext.class,0);
		}
		public AggregateCompareContext aggregateCompare() {
			return getRuleContext(AggregateCompareContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_condition);
		try {
			setState(84);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				match(T__4);
				setState(76);
				match(WS);
				setState(77);
				compare();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				match(T__4);
				setState(79);
				match(WS);
				setState(80);
				logicLink();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(T__4);
				setState(82);
				match(WS);
				setState(83);
				aggregateCompare();
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

	public static class CompareContext extends ParserRuleContext {
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public TerminalNode OPERATOR() { return getToken(QueryParser.OPERATOR, 0); }
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public CompareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCompare(this);
		}
	}

	public final CompareContext compare() throws RecognitionException {
		CompareContext _localctx = new CompareContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_compare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			property();
			setState(88);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(87);
				match(WS);
				}
			}

			setState(90);
			match(OPERATOR);
			setState(92);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(91);
				match(WS);
				}
			}

			setState(94);
			property();
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

	public static class LogicLinkContext extends ParserRuleContext {
		public List<CompareContext> compare() {
			return getRuleContexts(CompareContext.class);
		}
		public CompareContext compare(int i) {
			return getRuleContext(CompareContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public LogicLinkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicLink; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterLogicLink(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitLogicLink(this);
		}
	}

	public final LogicLinkContext logicLink() throws RecognitionException {
		LogicLinkContext _localctx = new LogicLinkContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_logicLink);
		try {
			setState(110);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				compare();
				setState(97);
				match(WS);
				setState(98);
				match(T__5);
				setState(99);
				match(WS);
				setState(100);
				compare();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				compare();
				setState(103);
				match(WS);
				setState(104);
				match(T__6);
				setState(105);
				match(WS);
				setState(106);
				compare();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				match(T__7);
				setState(109);
				compare();
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

	public static class AggregateCompareContext extends ParserRuleContext {
		public AggregateContext aggregate() {
			return getRuleContext(AggregateContext.class,0);
		}
		public TerminalNode OPERATOR() { return getToken(QueryParser.OPERATOR, 0); }
		public TerminalNode INT() { return getToken(QueryParser.INT, 0); }
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public AggregateCompareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregateCompare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAggregateCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAggregateCompare(this);
		}
	}

	public final AggregateCompareContext aggregateCompare() throws RecognitionException {
		AggregateCompareContext _localctx = new AggregateCompareContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_aggregateCompare);
		int _la;
		try {
			setState(131);
			switch (_input.LA(1)) {
			case T__8:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				aggregate();
				setState(114);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(113);
					match(WS);
					}
				}

				setState(116);
				match(OPERATOR);
				setState(118);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(117);
					match(WS);
					}
				}

				setState(120);
				match(INT);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				match(INT);
				setState(124);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(123);
					match(WS);
					}
				}

				setState(126);
				match(OPERATOR);
				setState(128);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(127);
					match(WS);
					}
				}

				setState(130);
				aggregate();
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

	public static class PropertyContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(QueryParser.VARIABLE, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public TerminalNode INT() { return getToken(QueryParser.INT, 0); }
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitProperty(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_property);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARIABLE) | (1L << STRING) | (1L << INT))) != 0)) ) {
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

	public static class AggregateContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(QueryParser.VARIABLE, 0); }
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public AggregateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAggregate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAggregate(this);
		}
	}

	public final AggregateContext aggregate() throws RecognitionException {
		AggregateContext _localctx = new AggregateContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_aggregate);
		int _la;
		try {
			setState(200);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				match(T__8);
				setState(137);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(136);
					match(WS);
					}
				}

				setState(139);
				match(T__9);
				setState(141);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(140);
					match(WS);
					}
				}

				setState(143);
				match(VARIABLE);
				setState(145);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(144);
					match(WS);
					}
				}

				setState(147);
				match(T__1);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				match(T__10);
				setState(150);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(149);
					match(WS);
					}
				}

				setState(152);
				match(T__9);
				setState(154);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(153);
					match(WS);
					}
				}

				setState(156);
				match(VARIABLE);
				setState(158);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(157);
					match(WS);
					}
				}

				setState(160);
				match(T__1);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 3);
				{
				setState(161);
				match(T__11);
				setState(163);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(162);
					match(WS);
					}
				}

				setState(165);
				match(T__9);
				setState(167);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(166);
					match(WS);
					}
				}

				setState(169);
				match(VARIABLE);
				setState(171);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(170);
					match(WS);
					}
				}

				setState(173);
				match(T__1);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 4);
				{
				setState(174);
				match(T__12);
				setState(176);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(175);
					match(WS);
					}
				}

				setState(178);
				match(T__9);
				setState(180);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(179);
					match(WS);
					}
				}

				setState(182);
				match(VARIABLE);
				setState(184);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(183);
					match(WS);
					}
				}

				setState(186);
				match(T__1);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 5);
				{
				setState(187);
				match(T__13);
				setState(189);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(188);
					match(WS);
					}
				}

				setState(191);
				match(T__9);
				setState(193);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(192);
					match(WS);
					}
				}

				setState(195);
				match(VARIABLE);
				setState(197);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(196);
					match(WS);
					}
				}

				setState(199);
				match(T__1);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return domainlist_sempred((DomainlistContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean domainlist_sempred(DomainlistContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\27\u00cd\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\2\5\2\34\n\2\3\2\5\2\37\n\2\3\3\3\3\3\3\5\3$\n"+
		"\3\3\3\3\3\5\3(\n\3\3\3\3\3\3\3\3\3\5\3.\n\3\3\3\3\3\5\3\62\n\3\3\3\5"+
		"\3\65\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\5\5@\n\5\3\5\3\5\5\5D\n"+
		"\5\3\5\7\5G\n\5\f\5\16\5J\13\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\7W\n\7\3\b\3\b\5\b[\n\b\3\b\3\b\5\b_\n\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tq\n\t\3\n\3\n\5\nu\n\n\3"+
		"\n\3\n\5\ny\n\n\3\n\3\n\3\n\3\n\5\n\177\n\n\3\n\3\n\5\n\u0083\n\n\3\n"+
		"\5\n\u0086\n\n\3\13\3\13\3\f\3\f\5\f\u008c\n\f\3\f\3\f\5\f\u0090\n\f\3"+
		"\f\3\f\5\f\u0094\n\f\3\f\3\f\3\f\5\f\u0099\n\f\3\f\3\f\5\f\u009d\n\f\3"+
		"\f\3\f\5\f\u00a1\n\f\3\f\3\f\3\f\5\f\u00a6\n\f\3\f\3\f\5\f\u00aa\n\f\3"+
		"\f\3\f\5\f\u00ae\n\f\3\f\3\f\3\f\5\f\u00b3\n\f\3\f\3\f\5\f\u00b7\n\f\3"+
		"\f\3\f\5\f\u00bb\n\f\3\f\3\f\3\f\5\f\u00c0\n\f\3\f\3\f\5\f\u00c4\n\f\3"+
		"\f\3\f\5\f\u00c8\n\f\3\f\5\f\u00cb\n\f\3\f\2\3\b\r\2\4\6\b\n\f\16\20\22"+
		"\24\26\2\3\3\2\21\23\u00e9\2\30\3\2\2\2\4\64\3\2\2\2\6\66\3\2\2\2\b:\3"+
		"\2\2\2\nK\3\2\2\2\fV\3\2\2\2\16X\3\2\2\2\20p\3\2\2\2\22\u0085\3\2\2\2"+
		"\24\u0087\3\2\2\2\26\u00ca\3\2\2\2\30\31\5\f\7\2\31\33\7\25\2\2\32\34"+
		"\5\6\4\2\33\32\3\2\2\2\33\34\3\2\2\2\34\36\3\2\2\2\35\37\5\4\3\2\36\35"+
		"\3\2\2\2\36\37\3\2\2\2\37\3\3\2\2\2 !\7\25\2\2!#\7\3\2\2\"$\7\25\2\2#"+
		"\"\3\2\2\2#$\3\2\2\2$%\3\2\2\2%\'\7\23\2\2&(\7\25\2\2\'&\3\2\2\2\'(\3"+
		"\2\2\2()\3\2\2\2)\65\7\4\2\2*+\7\25\2\2+-\7\5\2\2,.\7\25\2\2-,\3\2\2\2"+
		"-.\3\2\2\2./\3\2\2\2/\61\7\23\2\2\60\62\7\25\2\2\61\60\3\2\2\2\61\62\3"+
		"\2\2\2\62\63\3\2\2\2\63\65\7\4\2\2\64 \3\2\2\2\64*\3\2\2\2\65\5\3\2\2"+
		"\2\66\67\7\6\2\2\678\7\25\2\289\5\b\5\29\7\3\2\2\2:;\b\5\1\2;<\5\n\6\2"+
		"<H\3\2\2\2=?\f\3\2\2>@\7\25\2\2?>\3\2\2\2?@\3\2\2\2@A\3\2\2\2AC\7\24\2"+
		"\2BD\7\25\2\2CB\3\2\2\2CD\3\2\2\2DE\3\2\2\2EG\5\b\5\4F=\3\2\2\2GJ\3\2"+
		"\2\2HF\3\2\2\2HI\3\2\2\2I\t\3\2\2\2JH\3\2\2\2KL\7\21\2\2L\13\3\2\2\2M"+
		"N\7\7\2\2NO\7\25\2\2OW\5\16\b\2PQ\7\7\2\2QR\7\25\2\2RW\5\20\t\2ST\7\7"+
		"\2\2TU\7\25\2\2UW\5\22\n\2VM\3\2\2\2VP\3\2\2\2VS\3\2\2\2W\r\3\2\2\2XZ"+
		"\5\24\13\2Y[\7\25\2\2ZY\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\^\7\27\2\2]_\7\25"+
		"\2\2^]\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\5\24\13\2a\17\3\2\2\2bc\5\16\b\2"+
		"cd\7\25\2\2de\7\b\2\2ef\7\25\2\2fg\5\16\b\2gq\3\2\2\2hi\5\16\b\2ij\7\25"+
		"\2\2jk\7\t\2\2kl\7\25\2\2lm\5\16\b\2mq\3\2\2\2no\7\n\2\2oq\5\16\b\2pb"+
		"\3\2\2\2ph\3\2\2\2pn\3\2\2\2q\21\3\2\2\2rt\5\26\f\2su\7\25\2\2ts\3\2\2"+
		"\2tu\3\2\2\2uv\3\2\2\2vx\7\27\2\2wy\7\25\2\2xw\3\2\2\2xy\3\2\2\2yz\3\2"+
		"\2\2z{\7\23\2\2{\u0086\3\2\2\2|~\7\23\2\2}\177\7\25\2\2~}\3\2\2\2~\177"+
		"\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\7\27\2\2\u0081\u0083\7\25\2\2\u0082"+
		"\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\5\26"+
		"\f\2\u0085r\3\2\2\2\u0085|\3\2\2\2\u0086\23\3\2\2\2\u0087\u0088\t\2\2"+
		"\2\u0088\25\3\2\2\2\u0089\u008b\7\13\2\2\u008a\u008c\7\25\2\2\u008b\u008a"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008f\7\f\2\2\u008e"+
		"\u0090\7\25\2\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3"+
		"\2\2\2\u0091\u0093\7\21\2\2\u0092\u0094\7\25\2\2\u0093\u0092\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u00cb\7\4\2\2\u0096\u0098\7\r"+
		"\2\2\u0097\u0099\7\25\2\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u009c\7\f\2\2\u009b\u009d\7\25\2\2\u009c\u009b\3"+
		"\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a0\7\21\2\2\u009f"+
		"\u00a1\7\25\2\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3"+
		"\2\2\2\u00a2\u00cb\7\4\2\2\u00a3\u00a5\7\16\2\2\u00a4\u00a6\7\25\2\2\u00a5"+
		"\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\7\f"+
		"\2\2\u00a8\u00aa\7\25\2\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ab\3\2\2\2\u00ab\u00ad\7\21\2\2\u00ac\u00ae\7\25\2\2\u00ad\u00ac\3"+
		"\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00cb\7\4\2\2\u00b0"+
		"\u00b2\7\17\2\2\u00b1\u00b3\7\25\2\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3"+
		"\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b6\7\f\2\2\u00b5\u00b7\7\25\2\2\u00b6"+
		"\u00b5\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\7\21"+
		"\2\2\u00b9\u00bb\7\25\2\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00cb\7\4\2\2\u00bd\u00bf\7\20\2\2\u00be\u00c0\7"+
		"\25\2\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c3\7\f\2\2\u00c2\u00c4\7\25\2\2\u00c3\u00c2\3\2\2\2\u00c3\u00c4\3"+
		"\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\7\21\2\2\u00c6\u00c8\7\25\2\2\u00c7"+
		"\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cb\7\4"+
		"\2\2\u00ca\u0089\3\2\2\2\u00ca\u0096\3\2\2\2\u00ca\u00a3\3\2\2\2\u00ca"+
		"\u00b0\3\2\2\2\u00ca\u00bd\3\2\2\2\u00cb\27\3\2\2\2%\33\36#\'-\61\64?"+
		"CHVZ^ptx~\u0082\u0085\u008b\u008f\u0093\u0098\u009c\u00a0\u00a5\u00a9"+
		"\u00ad\u00b2\u00b6\u00ba\u00bf\u00c3\u00c7\u00ca";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}