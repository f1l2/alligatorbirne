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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, Name=15, Int=16, COMMA=17, 
		WS=18, NL=19, OPERATOR=20;
	public static final int
		RULE_query = 0, RULE_window = 1, RULE_domainlist = 2, RULE_domain = 3, 
		RULE_condition = 4, RULE_comparelogic = 5, RULE_compare = 6, RULE_aggregate = 7, 
		RULE_property = 8;
	public static final String[] ruleNames = {
		"query", "window", "domainlist", "domain", "condition", "comparelogic", 
		"compare", "aggregate", "property"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'FROM'", "'WIN.TIME('", "')'", "'WIN.LENGTH('", "'CONDITION'", 
		"'AND'", "'NOT'", "'SUM'", "'('", "'AVG'", "'COUNT'", "'MAX'", "'MIN'", 
		"'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "Name", "Int", "COMMA", "WS", "NL", "OPERATOR"
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
		public DomainlistContext domainlist() {
			return getRuleContext(DomainlistContext.class,0);
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
			setState(18);
			condition();
			setState(19);
			match(WS);
			setState(20);
			match(T__0);
			setState(22);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(21);
				domainlist();
				}
				break;
			}
			setState(25);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(24);
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
		public TerminalNode Int() { return getToken(QueryParser.Int, 0); }
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
			setState(47);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				match(WS);
				setState(28);
				match(T__1);
				setState(30);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(29);
					match(WS);
					}
				}

				setState(32);
				match(Int);
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
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				match(WS);
				setState(38);
				match(T__3);
				setState(40);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(39);
					match(WS);
					}
				}

				setState(42);
				match(Int);
				setState(44);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(43);
					match(WS);
					}
				}

				setState(46);
				match(T__2);
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

	public static class DomainlistContext extends ParserRuleContext {
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public DomainContext domain() {
			return getRuleContext(DomainContext.class,0);
		}
		public List<DomainlistContext> domainlist() {
			return getRuleContexts(DomainlistContext.class);
		}
		public DomainlistContext domainlist(int i) {
			return getRuleContext(DomainlistContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(QueryParser.COMMA, 0); }
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
		DomainlistContext _localctx = new DomainlistContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_domainlist);
		int _la;
		try {
			setState(62);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				match(WS);
				setState(50);
				domain();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				match(WS);
				setState(52);
				domainlist();
				setState(54);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(53);
					match(WS);
					}
				}

				setState(56);
				match(COMMA);
				setState(58);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(57);
					match(WS);
					}
					break;
				}
				setState(60);
				domainlist();
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
		public TerminalNode Name() { return getToken(QueryParser.Name, 0); }
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
		enterRule(_localctx, 6, RULE_domain);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(Name);
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
		public ComparelogicContext comparelogic() {
			return getRuleContext(ComparelogicContext.class,0);
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
		enterRule(_localctx, 8, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(T__4);
			setState(67);
			match(WS);
			setState(68);
			comparelogic(0);
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

	public static class ComparelogicContext extends ParserRuleContext {
		public List<ComparelogicContext> comparelogic() {
			return getRuleContexts(ComparelogicContext.class);
		}
		public ComparelogicContext comparelogic(int i) {
			return getRuleContext(ComparelogicContext.class,i);
		}
		public CompareContext compare() {
			return getRuleContext(CompareContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(QueryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(QueryParser.WS, i);
		}
		public ComparelogicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparelogic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterComparelogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitComparelogic(this);
		}
	}

	public final ComparelogicContext comparelogic() throws RecognitionException {
		return comparelogic(0);
	}

	private ComparelogicContext comparelogic(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ComparelogicContext _localctx = new ComparelogicContext(_ctx, _parentState);
		ComparelogicContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_comparelogic, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			switch (_input.LA(1)) {
			case T__6:
				{
				setState(71);
				match(T__6);
				setState(72);
				comparelogic(2);
				}
				break;
			case T__7:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case Name:
			case Int:
				{
				setState(73);
				compare();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(88);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(86);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ComparelogicContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_comparelogic);
						setState(76);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(77);
						match(WS);
						setState(78);
						match(T__5);
						setState(79);
						match(WS);
						setState(80);
						comparelogic(5);
						}
						break;
					case 2:
						{
						_localctx = new ComparelogicContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_comparelogic);
						setState(81);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(82);
						match(WS);
						setState(83);
						match(T__5);
						setState(84);
						match(WS);
						setState(85);
						comparelogic(4);
						}
						break;
					}
					} 
				}
				setState(90);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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

	public static class CompareContext extends ParserRuleContext {
		public List<AggregateContext> aggregate() {
			return getRuleContexts(AggregateContext.class);
		}
		public AggregateContext aggregate(int i) {
			return getRuleContext(AggregateContext.class,i);
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
			setState(91);
			aggregate();
			setState(93);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(92);
				match(WS);
				}
			}

			setState(95);
			match(OPERATOR);
			setState(97);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(96);
				match(WS);
				}
			}

			setState(99);
			aggregate();
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
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
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
		enterRule(_localctx, 14, RULE_aggregate);
		int _la;
		try {
			setState(172);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				match(T__7);
				setState(103);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(102);
					match(WS);
					}
				}

				setState(105);
				match(T__8);
				setState(107);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(106);
					match(WS);
					}
				}

				setState(109);
				property();
				setState(111);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(110);
					match(WS);
					}
				}

				setState(113);
				match(T__2);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				match(T__9);
				setState(117);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(116);
					match(WS);
					}
				}

				setState(119);
				match(T__8);
				setState(121);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(120);
					match(WS);
					}
				}

				setState(123);
				property();
				setState(125);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(124);
					match(WS);
					}
				}

				setState(127);
				match(T__2);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				setState(129);
				match(T__10);
				setState(131);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(130);
					match(WS);
					}
				}

				setState(133);
				match(T__8);
				setState(135);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(134);
					match(WS);
					}
				}

				setState(137);
				property();
				setState(139);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(138);
					match(WS);
					}
				}

				setState(141);
				match(T__2);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(143);
				match(T__11);
				setState(145);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(144);
					match(WS);
					}
				}

				setState(147);
				match(T__8);
				setState(149);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(148);
					match(WS);
					}
				}

				setState(151);
				property();
				setState(153);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(152);
					match(WS);
					}
				}

				setState(155);
				match(T__2);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(157);
				match(T__12);
				setState(159);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(158);
					match(WS);
					}
				}

				setState(161);
				match(T__8);
				setState(163);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(162);
					match(WS);
					}
				}

				setState(165);
				property();
				setState(167);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(166);
					match(WS);
					}
				}

				setState(169);
				match(T__2);
				}
				break;
			case Name:
			case Int:
				enterOuterAlt(_localctx, 6);
				{
				setState(171);
				property();
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
		public List<TerminalNode> Name() { return getTokens(QueryParser.Name); }
		public TerminalNode Name(int i) {
			return getToken(QueryParser.Name, i);
		}
		public TerminalNode Int() { return getToken(QueryParser.Int, 0); }
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
		enterRule(_localctx, 16, RULE_property);
		try {
			setState(178);
			switch (_input.LA(1)) {
			case Name:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				match(Name);
				setState(175);
				match(T__13);
				setState(176);
				match(Name);
				}
				break;
			case Int:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				match(Int);
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
		case 5:
			return comparelogic_sempred((ComparelogicContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean comparelogic_sempred(ComparelogicContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\26\u00b7\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3"+
		"\2\3\2\3\2\5\2\31\n\2\3\2\5\2\34\n\2\3\3\3\3\3\3\5\3!\n\3\3\3\3\3\5\3"+
		"%\n\3\3\3\3\3\3\3\3\3\5\3+\n\3\3\3\3\3\5\3/\n\3\3\3\5\3\62\n\3\3\4\3\4"+
		"\3\4\3\4\3\4\5\49\n\4\3\4\3\4\5\4=\n\4\3\4\3\4\5\4A\n\4\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7M\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\7\7Y\n\7\f\7\16\7\\\13\7\3\b\3\b\5\b`\n\b\3\b\3\b\5\bd\n\b\3\b"+
		"\3\b\3\t\3\t\5\tj\n\t\3\t\3\t\5\tn\n\t\3\t\3\t\5\tr\n\t\3\t\3\t\3\t\3"+
		"\t\5\tx\n\t\3\t\3\t\5\t|\n\t\3\t\3\t\5\t\u0080\n\t\3\t\3\t\3\t\3\t\5\t"+
		"\u0086\n\t\3\t\3\t\5\t\u008a\n\t\3\t\3\t\5\t\u008e\n\t\3\t\3\t\3\t\3\t"+
		"\5\t\u0094\n\t\3\t\3\t\5\t\u0098\n\t\3\t\3\t\5\t\u009c\n\t\3\t\3\t\3\t"+
		"\3\t\5\t\u00a2\n\t\3\t\3\t\5\t\u00a6\n\t\3\t\3\t\5\t\u00aa\n\t\3\t\3\t"+
		"\3\t\5\t\u00af\n\t\3\n\3\n\3\n\3\n\5\n\u00b5\n\n\3\n\2\3\f\13\2\4\6\b"+
		"\n\f\16\20\22\2\2\u00d1\2\24\3\2\2\2\4\61\3\2\2\2\6@\3\2\2\2\bB\3\2\2"+
		"\2\nD\3\2\2\2\fL\3\2\2\2\16]\3\2\2\2\20\u00ae\3\2\2\2\22\u00b4\3\2\2\2"+
		"\24\25\5\n\6\2\25\26\7\24\2\2\26\30\7\3\2\2\27\31\5\6\4\2\30\27\3\2\2"+
		"\2\30\31\3\2\2\2\31\33\3\2\2\2\32\34\5\4\3\2\33\32\3\2\2\2\33\34\3\2\2"+
		"\2\34\3\3\2\2\2\35\36\7\24\2\2\36 \7\4\2\2\37!\7\24\2\2 \37\3\2\2\2 !"+
		"\3\2\2\2!\"\3\2\2\2\"$\7\22\2\2#%\7\24\2\2$#\3\2\2\2$%\3\2\2\2%&\3\2\2"+
		"\2&\62\7\5\2\2\'(\7\24\2\2(*\7\6\2\2)+\7\24\2\2*)\3\2\2\2*+\3\2\2\2+,"+
		"\3\2\2\2,.\7\22\2\2-/\7\24\2\2.-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\62\7"+
		"\5\2\2\61\35\3\2\2\2\61\'\3\2\2\2\62\5\3\2\2\2\63\64\7\24\2\2\64A\5\b"+
		"\5\2\65\66\7\24\2\2\668\5\6\4\2\679\7\24\2\28\67\3\2\2\289\3\2\2\29:\3"+
		"\2\2\2:<\7\23\2\2;=\7\24\2\2<;\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\5\6\4\2?"+
		"A\3\2\2\2@\63\3\2\2\2@\65\3\2\2\2A\7\3\2\2\2BC\7\21\2\2C\t\3\2\2\2DE\7"+
		"\7\2\2EF\7\24\2\2FG\5\f\7\2G\13\3\2\2\2HI\b\7\1\2IJ\7\t\2\2JM\5\f\7\4"+
		"KM\5\16\b\2LH\3\2\2\2LK\3\2\2\2MZ\3\2\2\2NO\f\6\2\2OP\7\24\2\2PQ\7\b\2"+
		"\2QR\7\24\2\2RY\5\f\7\7ST\f\5\2\2TU\7\24\2\2UV\7\b\2\2VW\7\24\2\2WY\5"+
		"\f\7\6XN\3\2\2\2XS\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\r\3\2\2\2\\"+
		"Z\3\2\2\2]_\5\20\t\2^`\7\24\2\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ac\7\26\2"+
		"\2bd\7\24\2\2cb\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\5\20\t\2f\17\3\2\2\2gi\7"+
		"\n\2\2hj\7\24\2\2ih\3\2\2\2ij\3\2\2\2jk\3\2\2\2km\7\13\2\2ln\7\24\2\2"+
		"ml\3\2\2\2mn\3\2\2\2no\3\2\2\2oq\5\22\n\2pr\7\24\2\2qp\3\2\2\2qr\3\2\2"+
		"\2rs\3\2\2\2st\7\5\2\2t\u00af\3\2\2\2uw\7\f\2\2vx\7\24\2\2wv\3\2\2\2w"+
		"x\3\2\2\2xy\3\2\2\2y{\7\13\2\2z|\7\24\2\2{z\3\2\2\2{|\3\2\2\2|}\3\2\2"+
		"\2}\177\5\22\n\2~\u0080\7\24\2\2\177~\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\7\5\2\2\u0082\u00af\3\2\2\2\u0083\u0085\7\r"+
		"\2\2\u0084\u0086\7\24\2\2\u0085\u0084\3\2\2\2\u0085\u0086\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0089\7\13\2\2\u0088\u008a\7\24\2\2\u0089\u0088\3"+
		"\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\5\22\n\2\u008c"+
		"\u008e\7\24\2\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3"+
		"\2\2\2\u008f\u0090\7\5\2\2\u0090\u00af\3\2\2\2\u0091\u0093\7\16\2\2\u0092"+
		"\u0094\7\24\2\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3"+
		"\2\2\2\u0095\u0097\7\13\2\2\u0096\u0098\7\24\2\2\u0097\u0096\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\5\22\n\2\u009a\u009c\7"+
		"\24\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u009e\7\5\2\2\u009e\u00af\3\2\2\2\u009f\u00a1\7\17\2\2\u00a0\u00a2\7"+
		"\24\2\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a5\7\13\2\2\u00a4\u00a6\7\24\2\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3"+
		"\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\5\22\n\2\u00a8\u00aa\7\24\2\2\u00a9"+
		"\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\7\5"+
		"\2\2\u00ac\u00af\3\2\2\2\u00ad\u00af\5\22\n\2\u00aeg\3\2\2\2\u00aeu\3"+
		"\2\2\2\u00ae\u0083\3\2\2\2\u00ae\u0091\3\2\2\2\u00ae\u009f\3\2\2\2\u00ae"+
		"\u00ad\3\2\2\2\u00af\21\3\2\2\2\u00b0\u00b1\7\21\2\2\u00b1\u00b2\7\20"+
		"\2\2\u00b2\u00b5\7\21\2\2\u00b3\u00b5\7\22\2\2\u00b4\u00b0\3\2\2\2\u00b4"+
		"\u00b3\3\2\2\2\u00b5\23\3\2\2\2\"\30\33 $*.\618<@LXZ_cimqw{\177\u0085"+
		"\u0089\u008d\u0093\u0097\u009b\u00a1\u00a5\u00a9\u00ae\u00b4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}