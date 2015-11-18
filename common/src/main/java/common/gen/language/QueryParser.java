// Generated from Query.g4 by ANTLR 4.5.1

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
public class QueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		VARIABLE=32, STRING=33, INT=34, COMMA=35, WS=36, NL=37;
	public static final int
		RULE_query = 0, RULE_window = 1, RULE_domains = 2, RULE_domain = 3, RULE_domainName = 4, 
		RULE_conditions = 5, RULE_condition = 6, RULE_singleCondition = 7, RULE_compositeCondition = 8, 
		RULE_compositeOperationSingleDigit = 9, RULE_compositeFunctionSingleDigit = 10, 
		RULE_compositeOperationDoubleDigit = 11, RULE_compositeFunctionDoubleDigit = 12, 
		RULE_aggregateCondition = 13, RULE_aggregateFunction = 14, RULE_evaluation = 15, 
		RULE_property = 16, RULE_variable = 17, RULE_intValue = 18, RULE_operator = 19, 
		RULE_windowType = 20;
	public static final String[] ruleNames = {
		"query", "window", "domains", "domain", "domainName", "conditions", "condition", 
		"singleCondition", "compositeCondition", "compositeOperationSingleDigit", 
		"compositeFunctionSingleDigit", "compositeOperationDoubleDigit", "compositeFunctionDoubleDigit", 
		"aggregateCondition", "aggregateFunction", "evaluation", "property", "variable", 
		"intValue", "operator", "windowType"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'FROM'", "'from'", "'CONDITION'", "'condition'", 
		"'NOT'", "'not'", "'AND'", "'and'", "'OR'", "'or'", "'SUM'", "'sum'", 
		"'AVG'", "'avg'", "'COUNT'", "'count'", "'MAX'", "'max'", "'MIN'", "'min'", 
		"'='", "'<'", "'>'", "'<='", "'>='", "'WIN:TIME'", "'win:time'", "'WIN:LENGTH'", 
		"'win:length'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
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
			setState(42);
			conditions();
			setState(44);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(43);
				domains();
				}
				break;
			}
			setState(47);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(46);
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
			setState(73);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				match(WS);
				setState(50);
				windowType();
				setState(51);
				match(T__0);
				setState(53);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(52);
					match(WS);
					}
				}

				setState(55);
				intValue();
				setState(57);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(56);
					match(WS);
					}
				}

				setState(59);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				match(WS);
				setState(62);
				windowType();
				setState(63);
				match(T__0);
				setState(65);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(64);
					match(WS);
					}
				}

				setState(67);
				intValue();
				setState(69);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(68);
					match(WS);
					}
				}

				setState(71);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(75);
			match(WS);
			setState(76);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__3) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(77);
			match(WS);
			setState(78);
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
			setState(81);
			domainName();
			}
			_ctx.stop = _input.LT(-1);
			setState(94);
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
					setState(83);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(85);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(84);
						match(WS);
						}
					}

					setState(87);
					match(COMMA);
					setState(89);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(88);
						match(WS);
						}
					}

					setState(91);
					domain(2);
					}
					} 
				}
				setState(96);
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
			setState(97);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__5) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(100);
			match(WS);
			setState(101);
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
			setState(105);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				singleCondition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				compositeCondition();
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
		public AggregateConditionContext aggregateCondition() {
			return getRuleContext(AggregateConditionContext.class,0);
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
			setState(109);
			switch (_input.LA(1)) {
			case VARIABLE:
			case STRING:
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				evaluation();
				}
				break;
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				aggregateCondition();
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

	public static class CompositeConditionContext extends ParserRuleContext {
		public CompositeOperationSingleDigitContext compositeOperationSingleDigit() {
			return getRuleContext(CompositeOperationSingleDigitContext.class,0);
		}
		public CompositeOperationDoubleDigitContext compositeOperationDoubleDigit() {
			return getRuleContext(CompositeOperationDoubleDigitContext.class,0);
		}
		public SingleConditionContext singleCondition() {
			return getRuleContext(SingleConditionContext.class,0);
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
			setState(114);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				compositeOperationSingleDigit();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				compositeOperationDoubleDigit();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
				singleCondition();
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

	public static class CompositeOperationSingleDigitContext extends ParserRuleContext {
		public CompositeFunctionSingleDigitContext compositeFunctionSingleDigit() {
			return getRuleContext(CompositeFunctionSingleDigitContext.class,0);
		}
		public TerminalNode WS() { return getToken(QueryParser.WS, 0); }
		public CompositeConditionContext compositeCondition() {
			return getRuleContext(CompositeConditionContext.class,0);
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
			setState(116);
			compositeFunctionSingleDigit();
			setState(117);
			match(WS);
			setState(118);
			compositeCondition();
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
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

	public static class CompositeOperationDoubleDigitContext extends ParserRuleContext {
		public SingleConditionContext singleCondition() {
			return getRuleContext(SingleConditionContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public CompositeFunctionDoubleDigitContext compositeFunctionDoubleDigit() {
			return getRuleContext(CompositeFunctionDoubleDigitContext.class,0);
		}
		public CompositeConditionContext compositeCondition() {
			return getRuleContext(CompositeConditionContext.class,0);
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
			setState(122);
			singleCondition();
			setState(123);
			match(WS);
			setState(124);
			compositeFunctionDoubleDigit();
			setState(125);
			match(WS);
			setState(126);
			compositeCondition();
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
			setState(128);
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

	public static class AggregateConditionContext extends ParserRuleContext {
		public AggregateFunctionContext aggregateFunction() {
			return getRuleContext(AggregateFunctionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public IntValueContext intValue() {
			return getRuleContext(IntValueContext.class,0);
		}
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
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			aggregateFunction();
			setState(132);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(131);
				match(WS);
				}
			}

			setState(134);
			match(T__0);
			setState(136);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(135);
				match(WS);
				}
			}

			setState(138);
			variable();
			setState(140);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(139);
				match(WS);
				}
			}

			setState(142);
			match(T__1);
			setState(144);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(143);
				match(WS);
				}
			}

			setState(146);
			operator();
			setState(148);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(147);
				match(WS);
				}
			}

			setState(150);
			intValue();
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
		enterRule(_localctx, 28, RULE_aggregateFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21))) != 0)) ) {
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
		enterRule(_localctx, 30, RULE_evaluation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			property();
			setState(156);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(155);
				match(WS);
				}
			}

			setState(158);
			operator();
			setState(160);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(159);
				match(WS);
				}
			}

			setState(162);
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
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public IntValueContext intValue() {
			return getRuleContext(IntValueContext.class,0);
		}
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
		enterRule(_localctx, 32, RULE_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(164);
				variable();
				}
				break;
			case STRING:
				{
				setState(165);
				match(STRING);
				}
				break;
			case INT:
				{
				setState(166);
				intValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(QueryParser.VARIABLE, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
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

	public static class IntValueContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(QueryParser.INT, 0); }
		public IntValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterIntValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitIntValue(this);
		}
	}

	public final IntValueContext intValue() throws RecognitionException {
		IntValueContext _localctx = new IntValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_intValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
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
		enterRule(_localctx, 38, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) ) {
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

	public static class WindowTypeContext extends ParserRuleContext {
		public WindowTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_windowType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterWindowType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitWindowType(this);
		}
	}

	public final WindowTypeContext windowType() throws RecognitionException {
		WindowTypeContext _localctx = new WindowTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_windowType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30))) != 0)) ) {
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u00b4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\5\2/\n\2\3\2\5\2\62\n"+
		"\2\3\3\3\3\3\3\3\3\5\38\n\3\3\3\3\3\5\3<\n\3\3\3\3\3\3\3\3\3\3\3\3\3\5"+
		"\3D\n\3\3\3\3\3\5\3H\n\3\3\3\3\3\5\3L\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\5\5X\n\5\3\5\3\5\5\5\\\n\5\3\5\7\5_\n\5\f\5\16\5b\13\5\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\5\bl\n\b\3\t\3\t\5\tp\n\t\3\n\3\n\3\n\5"+
		"\nu\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\5\17\u0087\n\17\3\17\3\17\5\17\u008b\n\17\3\17\3\17\5\17\u008f"+
		"\n\17\3\17\3\17\5\17\u0093\n\17\3\17\3\17\5\17\u0097\n\17\3\17\3\17\3"+
		"\20\3\20\3\21\3\21\5\21\u009f\n\21\3\21\3\21\5\21\u00a3\n\21\3\21\3\21"+
		"\3\22\3\22\3\22\5\22\u00aa\n\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26"+
		"\3\26\2\3\b\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\t\3\2\5"+
		"\6\3\2\7\b\3\2\t\n\3\2\13\16\3\2\17\30\3\2\31\35\3\2\36!\u00b5\2,\3\2"+
		"\2\2\4K\3\2\2\2\6M\3\2\2\2\bR\3\2\2\2\nc\3\2\2\2\fe\3\2\2\2\16k\3\2\2"+
		"\2\20o\3\2\2\2\22t\3\2\2\2\24v\3\2\2\2\26z\3\2\2\2\30|\3\2\2\2\32\u0082"+
		"\3\2\2\2\34\u0084\3\2\2\2\36\u009a\3\2\2\2 \u009c\3\2\2\2\"\u00a9\3\2"+
		"\2\2$\u00ab\3\2\2\2&\u00ad\3\2\2\2(\u00af\3\2\2\2*\u00b1\3\2\2\2,.\5\f"+
		"\7\2-/\5\6\4\2.-\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60\62\5\4\3\2\61\60\3\2"+
		"\2\2\61\62\3\2\2\2\62\3\3\2\2\2\63\64\7&\2\2\64\65\5*\26\2\65\67\7\3\2"+
		"\2\668\7&\2\2\67\66\3\2\2\2\678\3\2\2\289\3\2\2\29;\5&\24\2:<\7&\2\2;"+
		":\3\2\2\2;<\3\2\2\2<=\3\2\2\2=>\7\4\2\2>L\3\2\2\2?@\7&\2\2@A\5*\26\2A"+
		"C\7\3\2\2BD\7&\2\2CB\3\2\2\2CD\3\2\2\2DE\3\2\2\2EG\5&\24\2FH\7&\2\2GF"+
		"\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\7\4\2\2JL\3\2\2\2K\63\3\2\2\2K?\3\2\2\2"+
		"L\5\3\2\2\2MN\7&\2\2NO\t\2\2\2OP\7&\2\2PQ\5\b\5\2Q\7\3\2\2\2RS\b\5\1\2"+
		"ST\5\n\6\2T`\3\2\2\2UW\f\3\2\2VX\7&\2\2WV\3\2\2\2WX\3\2\2\2XY\3\2\2\2"+
		"Y[\7%\2\2Z\\\7&\2\2[Z\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]_\5\b\5\4^U\3\2\2"+
		"\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\t\3\2\2\2b`\3\2\2\2cd\7\"\2\2d\13\3"+
		"\2\2\2ef\t\3\2\2fg\7&\2\2gh\5\16\b\2h\r\3\2\2\2il\5\20\t\2jl\5\22\n\2"+
		"ki\3\2\2\2kj\3\2\2\2l\17\3\2\2\2mp\5 \21\2np\5\34\17\2om\3\2\2\2on\3\2"+
		"\2\2p\21\3\2\2\2qu\5\24\13\2ru\5\30\r\2su\5\20\t\2tq\3\2\2\2tr\3\2\2\2"+
		"ts\3\2\2\2u\23\3\2\2\2vw\5\26\f\2wx\7&\2\2xy\5\22\n\2y\25\3\2\2\2z{\t"+
		"\4\2\2{\27\3\2\2\2|}\5\20\t\2}~\7&\2\2~\177\5\32\16\2\177\u0080\7&\2\2"+
		"\u0080\u0081\5\22\n\2\u0081\31\3\2\2\2\u0082\u0083\t\5\2\2\u0083\33\3"+
		"\2\2\2\u0084\u0086\5\36\20\2\u0085\u0087\7&\2\2\u0086\u0085\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\7\3\2\2\u0089\u008b\7&"+
		"\2\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008e\5$\23\2\u008d\u008f\7&\2\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u0090\3\2\2\2\u0090\u0092\7\4\2\2\u0091\u0093\7&\2\2\u0092"+
		"\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0096\5("+
		"\25\2\u0095\u0097\7&\2\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u0099\5&\24\2\u0099\35\3\2\2\2\u009a\u009b\t\6\2"+
		"\2\u009b\37\3\2\2\2\u009c\u009e\5\"\22\2\u009d\u009f\7&\2\2\u009e\u009d"+
		"\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\5(\25\2\u00a1"+
		"\u00a3\7&\2\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2"+
		"\2\2\u00a4\u00a5\5\"\22\2\u00a5!\3\2\2\2\u00a6\u00aa\5$\23\2\u00a7\u00aa"+
		"\7#\2\2\u00a8\u00aa\5&\24\2\u00a9\u00a6\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9"+
		"\u00a8\3\2\2\2\u00aa#\3\2\2\2\u00ab\u00ac\7\"\2\2\u00ac%\3\2\2\2\u00ad"+
		"\u00ae\7$\2\2\u00ae\'\3\2\2\2\u00af\u00b0\t\7\2\2\u00b0)\3\2\2\2\u00b1"+
		"\u00b2\t\b\2\2\u00b2+\3\2\2\2\27.\61\67;CGKW[`kot\u0086\u008a\u008e\u0092"+
		"\u0096\u009e\u00a2\u00a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}