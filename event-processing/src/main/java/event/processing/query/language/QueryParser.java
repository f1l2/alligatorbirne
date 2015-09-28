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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, VARIABLE=20, STRING=21, INT=22, COMMA=23, WS=24, NL=25;
	public static final int
		RULE_query = 0, RULE_window = 1, RULE_domains = 2, RULE_domain = 3, RULE_domainName = 4, 
		RULE_conditions = 5, RULE_condition = 6, RULE_singleCondition = 7, RULE_compositeCondition = 8, 
		RULE_compositeOperationSingleDigit = 9, RULE_compositeFunctionSingleDigit = 10, 
		RULE_compositeOperationDoubleDigit = 11, RULE_compositeFunctionDoubleDigit = 12, 
		RULE_aggregateCondition = 13, RULE_aggregateOperation = 14, RULE_aggregateFunction = 15, 
		RULE_evaluation = 16, RULE_property = 17, RULE_operator = 18;
	public static final String[] ruleNames = {
		"query", "window", "domains", "domain", "domainName", "conditions", "condition", 
		"singleCondition", "compositeCondition", "compositeOperationSingleDigit", 
		"compositeFunctionSingleDigit", "compositeOperationDoubleDigit", "compositeFunctionDoubleDigit", 
		"aggregateCondition", "aggregateOperation", "aggregateFunction", "evaluation", 
		"property", "operator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'WIN.TIME('", "')'", "'WIN.LENGTH('", "'FROM'", "'CONDITION'", 
		"'NOT'", "'AND'", "'OR'", "'('", "'SUM'", "'AVG'", "'COUNT'", "'MAX'", 
		"'MIN'", "'='", "'<'", "'>'", "'<='", "'>='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
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
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public DomainsContext domains() {
			return getRuleContext(DomainsContext.class,0);
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
			setState(38);
			conditions();
			setState(40);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(39);
				domains();
				}
				break;
			}
			setState(43);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(42);
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
			setState(65);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				match(WS);
				setState(46);
				match(T__0);
				setState(48);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(47);
					match(WS);
					}
				}

				setState(50);
				match(INT);
				setState(52);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(51);
					match(WS);
					}
				}

				setState(54);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(WS);
				setState(56);
				match(T__2);
				setState(58);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(57);
					match(WS);
					}
				}

				setState(60);
				match(INT);
				setState(62);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(61);
					match(WS);
					}
				}

				setState(64);
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

	public static class DomainsContext extends ParserRuleContext {
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public DomainContext domain() {
			return getRuleContext(DomainContext.class,0);
		}
		public DomainsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domains; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterDomains(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitDomains(this);
		}
	}

	public final DomainsContext domains() throws RecognitionException {
		DomainsContext _localctx = new DomainsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_domains);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(67);
			match(WS);
			setState(68);
			match(T__3);
			setState(69);
			match(WS);
			setState(70);
			domain(0);
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

	public static class DomainContext extends ParserRuleContext {
		public DomainNameContext domainName() {
			return getRuleContext(DomainNameContext.class,0);
		}
		public List<DomainContext> domain() {
			return getRuleContexts(DomainContext.class);
		}
		public DomainContext domain(int i) {
			return getRuleContext(DomainContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(QueryParser.COMMA, 0); }
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
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
		return domain(0);
	}

	private DomainContext domain(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DomainContext _localctx = new DomainContext(_ctx, _parentState);
		DomainContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_domain, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(73);
			domainName();
			}
			_ctx.stop = _input.LT(-1);
			setState(86);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DomainContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_domain);
					setState(75);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(77);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(76);
						match(WS);
						}
					}

					setState(79);
					match(COMMA);
					setState(81);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(80);
						match(WS);
						}
					}

					setState(83);
					domain(2);
					}
					} 
				}
				setState(88);
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
			setState(89);
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

	public static class ConditionsContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(QueryParser.WS, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ConditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterConditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitConditions(this);
		}
	}

	public final ConditionsContext conditions() throws RecognitionException {
		ConditionsContext _localctx = new ConditionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_conditions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(T__4);
			setState(92);
			match(WS);
			setState(93);
			condition();
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
		public SingleConditionContext singleCondition() {
			return getRuleContext(SingleConditionContext.class,0);
		}
		public CompositeConditionContext compositeCondition() {
			return getRuleContext(CompositeConditionContext.class,0);
		}
		public AggregateConditionContext aggregateCondition() {
			return getRuleContext(AggregateConditionContext.class,0);
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
		enterRule(_localctx, 12, RULE_condition);
		try {
			setState(98);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				singleCondition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				compositeCondition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(97);
				aggregateCondition();
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

	public static class SingleConditionContext extends ParserRuleContext {
		public EvaluationContext evaluation() {
			return getRuleContext(EvaluationContext.class,0);
		}
		public SingleConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterSingleCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitSingleCondition(this);
		}
	}

	public final SingleConditionContext singleCondition() throws RecognitionException {
		SingleConditionContext _localctx = new SingleConditionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_singleCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			evaluation();
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

	public static class CompositeConditionContext extends ParserRuleContext {
		public CompositeOperationSingleDigitContext compositeOperationSingleDigit() {
			return getRuleContext(CompositeOperationSingleDigitContext.class,0);
		}
		public CompositeOperationDoubleDigitContext compositeOperationDoubleDigit() {
			return getRuleContext(CompositeOperationDoubleDigitContext.class,0);
		}
		public CompositeConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compositeCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCompositeCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCompositeCondition(this);
		}
	}

	public final CompositeConditionContext compositeCondition() throws RecognitionException {
		CompositeConditionContext _localctx = new CompositeConditionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_compositeCondition);
		try {
			setState(104);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				compositeOperationSingleDigit();
				}
				break;
			case VARIABLE:
			case STRING:
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				compositeOperationDoubleDigit();
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

	public static class CompositeOperationSingleDigitContext extends ParserRuleContext {
		public CompositeFunctionSingleDigitContext compositeFunctionSingleDigit() {
			return getRuleContext(CompositeFunctionSingleDigitContext.class,0);
		}
		public TerminalNode WS() { return getToken(QueryParser.WS, 0); }
		public EvaluationContext evaluation() {
			return getRuleContext(EvaluationContext.class,0);
		}
		public CompositeOperationSingleDigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compositeOperationSingleDigit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCompositeOperationSingleDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCompositeOperationSingleDigit(this);
		}
	}

	public final CompositeOperationSingleDigitContext compositeOperationSingleDigit() throws RecognitionException {
		CompositeOperationSingleDigitContext _localctx = new CompositeOperationSingleDigitContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_compositeOperationSingleDigit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			compositeFunctionSingleDigit();
			setState(107);
			match(WS);
			setState(108);
			evaluation();
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

	public static class CompositeFunctionSingleDigitContext extends ParserRuleContext {
		public CompositeFunctionSingleDigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compositeFunctionSingleDigit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCompositeFunctionSingleDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCompositeFunctionSingleDigit(this);
		}
	}

	public final CompositeFunctionSingleDigitContext compositeFunctionSingleDigit() throws RecognitionException {
		CompositeFunctionSingleDigitContext _localctx = new CompositeFunctionSingleDigitContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_compositeFunctionSingleDigit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__5);
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

	public static class CompositeOperationDoubleDigitContext extends ParserRuleContext {
		public List<EvaluationContext> evaluation() {
			return getRuleContexts(EvaluationContext.class);
		}
		public EvaluationContext evaluation(int i) {
			return getRuleContext(EvaluationContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public CompositeFunctionDoubleDigitContext compositeFunctionDoubleDigit() {
			return getRuleContext(CompositeFunctionDoubleDigitContext.class,0);
		}
		public CompositeOperationDoubleDigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compositeOperationDoubleDigit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCompositeOperationDoubleDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCompositeOperationDoubleDigit(this);
		}
	}

	public final CompositeOperationDoubleDigitContext compositeOperationDoubleDigit() throws RecognitionException {
		CompositeOperationDoubleDigitContext _localctx = new CompositeOperationDoubleDigitContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_compositeOperationDoubleDigit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			evaluation();
			setState(113);
			match(WS);
			setState(114);
			compositeFunctionDoubleDigit();
			setState(115);
			match(WS);
			setState(116);
			evaluation();
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

	public static class CompositeFunctionDoubleDigitContext extends ParserRuleContext {
		public CompositeFunctionDoubleDigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compositeFunctionDoubleDigit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCompositeFunctionDoubleDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCompositeFunctionDoubleDigit(this);
		}
	}

	public final CompositeFunctionDoubleDigitContext compositeFunctionDoubleDigit() throws RecognitionException {
		CompositeFunctionDoubleDigitContext _localctx = new CompositeFunctionDoubleDigitContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_compositeFunctionDoubleDigit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_la = _input.LA(1);
			if ( !(_la==T__6 || _la==T__7) ) {
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

	public static class AggregateConditionContext extends ParserRuleContext {
		public AggregateOperationContext aggregateOperation() {
			return getRuleContext(AggregateOperationContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public TerminalNode INT() { return getToken(QueryParser.INT, 0); }
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public AggregateConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregateCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAggregateCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAggregateCondition(this);
		}
	}

	public final AggregateConditionContext aggregateCondition() throws RecognitionException {
		AggregateConditionContext _localctx = new AggregateConditionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_aggregateCondition);
		int _la;
		try {
			setState(140);
			switch (_input.LA(1)) {
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				aggregateOperation();
				setState(122);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(121);
					match(WS);
					}
				}

				setState(124);
				operator();
				setState(126);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(125);
					match(WS);
					}
				}

				setState(128);
				match(INT);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				match(INT);
				setState(132);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(131);
					match(WS);
					}
				}

				setState(134);
				operator();
				setState(136);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(135);
					match(WS);
					}
				}

				setState(138);
				aggregateOperation();
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

	public static class AggregateOperationContext extends ParserRuleContext {
		public AggregateFunctionContext aggregateFunction() {
			return getRuleContext(AggregateFunctionContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(QueryParser.VARIABLE, 0); }
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public AggregateOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregateOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAggregateOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAggregateOperation(this);
		}
	}

	public final AggregateOperationContext aggregateOperation() throws RecognitionException {
		AggregateOperationContext _localctx = new AggregateOperationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_aggregateOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			aggregateFunction();
			setState(144);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(143);
				match(WS);
				}
			}

			setState(146);
			match(T__8);
			setState(148);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(147);
				match(WS);
				}
			}

			setState(150);
			match(VARIABLE);
			setState(152);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(151);
				match(WS);
				}
			}

			setState(154);
			match(T__1);
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

	public static class AggregateFunctionContext extends ParserRuleContext {
		public AggregateFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregateFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAggregateFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAggregateFunction(this);
		}
	}

	public final AggregateFunctionContext aggregateFunction() throws RecognitionException {
		AggregateFunctionContext _localctx = new AggregateFunctionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_aggregateFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
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

	public static class EvaluationContext extends ParserRuleContext {
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public EvaluationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evaluation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterEvaluation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitEvaluation(this);
		}
	}

	public final EvaluationContext evaluation() throws RecognitionException {
		EvaluationContext _localctx = new EvaluationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_evaluation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			property();
			setState(160);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(159);
				match(WS);
				}
			}

			setState(162);
			operator();
			setState(164);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(163);
				match(WS);
				}
			}

			setState(166);
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
		enterRule(_localctx, 34, RULE_property);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
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

	public static class OperatorContext extends ParserRuleContext {
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitOperator(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return domain_sempred((DomainContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean domain_sempred(DomainContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\33\u00af\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\5\2+\n\2\3\2\5\2.\n\2\3\3\3\3\3\3\5\3\63"+
		"\n\3\3\3\3\3\5\3\67\n\3\3\3\3\3\3\3\3\3\5\3=\n\3\3\3\3\3\5\3A\n\3\3\3"+
		"\5\3D\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\5\5P\n\5\3\5\3\5\5\5"+
		"T\n\5\3\5\7\5W\n\5\f\5\16\5Z\13\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\5\be\n\b\3\t\3\t\3\n\3\n\5\nk\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\5\17}\n\17\3\17\3\17\5\17\u0081\n"+
		"\17\3\17\3\17\3\17\3\17\5\17\u0087\n\17\3\17\3\17\5\17\u008b\n\17\3\17"+
		"\3\17\5\17\u008f\n\17\3\20\3\20\5\20\u0093\n\20\3\20\3\20\5\20\u0097\n"+
		"\20\3\20\3\20\5\20\u009b\n\20\3\20\3\20\3\21\3\21\3\22\3\22\5\22\u00a3"+
		"\n\22\3\22\3\22\5\22\u00a7\n\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\2\3"+
		"\b\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\6\3\2\t\n\3\2\f\20"+
		"\3\2\26\30\3\2\21\25\u00b2\2(\3\2\2\2\4C\3\2\2\2\6E\3\2\2\2\bJ\3\2\2\2"+
		"\n[\3\2\2\2\f]\3\2\2\2\16d\3\2\2\2\20f\3\2\2\2\22j\3\2\2\2\24l\3\2\2\2"+
		"\26p\3\2\2\2\30r\3\2\2\2\32x\3\2\2\2\34\u008e\3\2\2\2\36\u0090\3\2\2\2"+
		" \u009e\3\2\2\2\"\u00a0\3\2\2\2$\u00aa\3\2\2\2&\u00ac\3\2\2\2(*\5\f\7"+
		"\2)+\5\6\4\2*)\3\2\2\2*+\3\2\2\2+-\3\2\2\2,.\5\4\3\2-,\3\2\2\2-.\3\2\2"+
		"\2.\3\3\2\2\2/\60\7\32\2\2\60\62\7\3\2\2\61\63\7\32\2\2\62\61\3\2\2\2"+
		"\62\63\3\2\2\2\63\64\3\2\2\2\64\66\7\30\2\2\65\67\7\32\2\2\66\65\3\2\2"+
		"\2\66\67\3\2\2\2\678\3\2\2\28D\7\4\2\29:\7\32\2\2:<\7\5\2\2;=\7\32\2\2"+
		"<;\3\2\2\2<=\3\2\2\2=>\3\2\2\2>@\7\30\2\2?A\7\32\2\2@?\3\2\2\2@A\3\2\2"+
		"\2AB\3\2\2\2BD\7\4\2\2C/\3\2\2\2C9\3\2\2\2D\5\3\2\2\2EF\7\32\2\2FG\7\6"+
		"\2\2GH\7\32\2\2HI\5\b\5\2I\7\3\2\2\2JK\b\5\1\2KL\5\n\6\2LX\3\2\2\2MO\f"+
		"\3\2\2NP\7\32\2\2ON\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QS\7\31\2\2RT\7\32\2\2"+
		"SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UW\5\b\5\4VM\3\2\2\2WZ\3\2\2\2XV\3\2\2\2"+
		"XY\3\2\2\2Y\t\3\2\2\2ZX\3\2\2\2[\\\7\26\2\2\\\13\3\2\2\2]^\7\7\2\2^_\7"+
		"\32\2\2_`\5\16\b\2`\r\3\2\2\2ae\5\20\t\2be\5\22\n\2ce\5\34\17\2da\3\2"+
		"\2\2db\3\2\2\2dc\3\2\2\2e\17\3\2\2\2fg\5\"\22\2g\21\3\2\2\2hk\5\24\13"+
		"\2ik\5\30\r\2jh\3\2\2\2ji\3\2\2\2k\23\3\2\2\2lm\5\26\f\2mn\7\32\2\2no"+
		"\5\"\22\2o\25\3\2\2\2pq\7\b\2\2q\27\3\2\2\2rs\5\"\22\2st\7\32\2\2tu\5"+
		"\32\16\2uv\7\32\2\2vw\5\"\22\2w\31\3\2\2\2xy\t\2\2\2y\33\3\2\2\2z|\5\36"+
		"\20\2{}\7\32\2\2|{\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\u0080\5&\24\2\177\u0081"+
		"\7\32\2\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0083\7\30\2\2\u0083\u008f\3\2\2\2\u0084\u0086\7\30\2\2\u0085\u0087\7"+
		"\32\2\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\u008a\5&\24\2\u0089\u008b\7\32\2\2\u008a\u0089\3\2\2\2\u008a\u008b\3"+
		"\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\5\36\20\2\u008d\u008f\3\2\2\2\u008e"+
		"z\3\2\2\2\u008e\u0084\3\2\2\2\u008f\35\3\2\2\2\u0090\u0092\5 \21\2\u0091"+
		"\u0093\7\32\2\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3"+
		"\2\2\2\u0094\u0096\7\13\2\2\u0095\u0097\7\32\2\2\u0096\u0095\3\2\2\2\u0096"+
		"\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\7\26\2\2\u0099\u009b\7"+
		"\32\2\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u009d\7\4\2\2\u009d\37\3\2\2\2\u009e\u009f\t\3\2\2\u009f!\3\2\2\2\u00a0"+
		"\u00a2\5$\23\2\u00a1\u00a3\7\32\2\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3"+
		"\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\5&\24\2\u00a5\u00a7\7\32\2\2\u00a6"+
		"\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\5$"+
		"\23\2\u00a9#\3\2\2\2\u00aa\u00ab\t\4\2\2\u00ab%\3\2\2\2\u00ac\u00ad\t"+
		"\5\2\2\u00ad\'\3\2\2\2\30*-\62\66<@COSXdj|\u0080\u0086\u008a\u008e\u0092"+
		"\u0096\u009a\u00a2\u00a6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}