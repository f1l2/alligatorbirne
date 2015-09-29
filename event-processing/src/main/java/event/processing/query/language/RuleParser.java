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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, VARIABLE=8, STRING=9, 
		INT=10, COMMA=11, WS=12, NL=13;
	public static final int
		RULE_rule = 0, RULE_query = 1, RULE_reactions = 2, RULE_reaction = 3, 
		RULE_deviceInformation = 4, RULE_deviceInformationName = 5, RULE_domainInformation = 6, 
		RULE_domainInformationName = 7, RULE_configurationModification = 8, RULE_configurationModificationName = 9;
	public static final String[] ruleNames = {
		"rule", "query", "reactions", "reaction", "deviceInformation", "deviceInformationName", 
		"domainInformation", "domainInformationName", "configurationModification", 
		"configurationModificationName"
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
	public static class RuleContext extends ParserRuleContext {
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
		public RuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitRule(this);
		}
	}

	public final RuleContext rule() throws RecognitionException {
		RuleContext _localctx = new RuleContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_rule);
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
			setState(39);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(28);
				reaction();
				}
				break;
			case 2:
				{
				setState(29);
				reaction();
				setState(31);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(30);
					match(WS);
					}
				}

				setState(33);
				match(T__1);
				setState(35);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(34);
					match(WS);
					}
					break;
				}
				setState(37);
				reactions();
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
		public DeviceInformationContext deviceInformation() {
			return getRuleContext(DeviceInformationContext.class,0);
		}
		public DomainInformationContext domainInformation() {
			return getRuleContext(DomainInformationContext.class,0);
		}
		public ConfigurationModificationContext configurationModification() {
			return getRuleContext(ConfigurationModificationContext.class,0);
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
			setState(41);
			deviceInformation();
			setState(42);
			match(T__2);
			setState(43);
			domainInformation();
			setState(44);
			match(T__2);
			setState(45);
			configurationModification();
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

	public static class DeviceInformationContext extends ParserRuleContext {
		public DeviceInformationNameContext deviceInformationName() {
			return getRuleContext(DeviceInformationNameContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public DeviceInformationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deviceInformation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterDeviceInformation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitDeviceInformation(this);
		}
	}

	public final DeviceInformationContext deviceInformation() throws RecognitionException {
		DeviceInformationContext _localctx = new DeviceInformationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_deviceInformation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(47);
				match(WS);
				}
			}

			setState(58);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(50);
				match(T__3);
				setState(52);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(51);
					match(WS);
					}
				}

				setState(54);
				match(T__4);
				setState(56);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(55);
					match(WS);
					}
				}

				}
			}

			setState(60);
			deviceInformationName();
			setState(62);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(61);
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

	public static class DeviceInformationNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(RuleParser.VARIABLE, 0); }
		public DeviceInformationNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deviceInformationName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterDeviceInformationName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitDeviceInformationName(this);
		}
	}

	public final DeviceInformationNameContext deviceInformationName() throws RecognitionException {
		DeviceInformationNameContext _localctx = new DeviceInformationNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_deviceInformationName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
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

	public static class DomainInformationContext extends ParserRuleContext {
		public DomainInformationNameContext domainInformationName() {
			return getRuleContext(DomainInformationNameContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public DomainInformationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainInformation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterDomainInformation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitDomainInformation(this);
		}
	}

	public final DomainInformationContext domainInformation() throws RecognitionException {
		DomainInformationContext _localctx = new DomainInformationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_domainInformation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(66);
				match(WS);
				}
			}

			setState(77);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(69);
				match(T__5);
				setState(71);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(70);
					match(WS);
					}
				}

				setState(73);
				match(T__4);
				setState(75);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(74);
					match(WS);
					}
				}

				}
			}

			setState(79);
			domainInformationName();
			setState(81);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(80);
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

	public static class DomainInformationNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(RuleParser.VARIABLE, 0); }
		public DomainInformationNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainInformationName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterDomainInformationName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitDomainInformationName(this);
		}
	}

	public final DomainInformationNameContext domainInformationName() throws RecognitionException {
		DomainInformationNameContext _localctx = new DomainInformationNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_domainInformationName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
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

	public static class ConfigurationModificationContext extends ParserRuleContext {
		public ConfigurationModificationNameContext configurationModificationName() {
			return getRuleContext(ConfigurationModificationNameContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(RuleParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(RuleParser.WS, i);
		}
		public ConfigurationModificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_configurationModification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterConfigurationModification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitConfigurationModification(this);
		}
	}

	public final ConfigurationModificationContext configurationModification() throws RecognitionException {
		ConfigurationModificationContext _localctx = new ConfigurationModificationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_configurationModification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(85);
				match(WS);
				}
			}

			setState(96);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(88);
				match(T__6);
				setState(90);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(89);
					match(WS);
					}
				}

				setState(92);
				match(T__4);
				setState(94);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(93);
					match(WS);
					}
				}

				}
			}

			setState(98);
			configurationModificationName();
			setState(100);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(99);
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

	public static class ConfigurationModificationNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(RuleParser.VARIABLE, 0); }
		public ConfigurationModificationNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_configurationModificationName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterConfigurationModificationName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitConfigurationModificationName(this);
		}
	}

	public final ConfigurationModificationNameContext configurationModificationName() throws RecognitionException {
		ConfigurationModificationNameContext _localctx = new ConfigurationModificationNameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_configurationModificationName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17k\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\5\4\"\n\4\3\4\3\4\5\4&\n\4"+
		"\3\4\3\4\5\4*\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\5\6\63\n\6\3\6\3\6\5\6\67"+
		"\n\6\3\6\3\6\5\6;\n\6\5\6=\n\6\3\6\3\6\5\6A\n\6\3\7\3\7\3\b\5\bF\n\b\3"+
		"\b\3\b\5\bJ\n\b\3\b\3\b\5\bN\n\b\5\bP\n\b\3\b\3\b\5\bT\n\b\3\t\3\t\3\n"+
		"\5\nY\n\n\3\n\3\n\5\n]\n\n\3\n\3\n\5\na\n\n\5\nc\n\n\3\n\3\n\5\ng\n\n"+
		"\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\2r\2\26\3\2\2\2\4\34\3"+
		"\2\2\2\6)\3\2\2\2\b+\3\2\2\2\n\62\3\2\2\2\fB\3\2\2\2\16E\3\2\2\2\20U\3"+
		"\2\2\2\22X\3\2\2\2\24h\3\2\2\2\26\27\5\4\3\2\27\30\7\16\2\2\30\31\7\3"+
		"\2\2\31\32\7\16\2\2\32\33\5\6\4\2\33\3\3\2\2\2\34\35\7\n\2\2\35\5\3\2"+
		"\2\2\36*\5\b\5\2\37!\5\b\5\2 \"\7\16\2\2! \3\2\2\2!\"\3\2\2\2\"#\3\2\2"+
		"\2#%\7\4\2\2$&\7\16\2\2%$\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\5\6\4\2(*\3"+
		"\2\2\2)\36\3\2\2\2)\37\3\2\2\2*\7\3\2\2\2+,\5\n\6\2,-\7\5\2\2-.\5\16\b"+
		"\2./\7\5\2\2/\60\5\22\n\2\60\t\3\2\2\2\61\63\7\16\2\2\62\61\3\2\2\2\62"+
		"\63\3\2\2\2\63<\3\2\2\2\64\66\7\6\2\2\65\67\7\16\2\2\66\65\3\2\2\2\66"+
		"\67\3\2\2\2\678\3\2\2\28:\7\7\2\29;\7\16\2\2:9\3\2\2\2:;\3\2\2\2;=\3\2"+
		"\2\2<\64\3\2\2\2<=\3\2\2\2=>\3\2\2\2>@\5\f\7\2?A\7\16\2\2@?\3\2\2\2@A"+
		"\3\2\2\2A\13\3\2\2\2BC\7\n\2\2C\r\3\2\2\2DF\7\16\2\2ED\3\2\2\2EF\3\2\2"+
		"\2FO\3\2\2\2GI\7\b\2\2HJ\7\16\2\2IH\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KM\7\7"+
		"\2\2LN\7\16\2\2ML\3\2\2\2MN\3\2\2\2NP\3\2\2\2OG\3\2\2\2OP\3\2\2\2PQ\3"+
		"\2\2\2QS\5\20\t\2RT\7\16\2\2SR\3\2\2\2ST\3\2\2\2T\17\3\2\2\2UV\7\n\2\2"+
		"V\21\3\2\2\2WY\7\16\2\2XW\3\2\2\2XY\3\2\2\2Yb\3\2\2\2Z\\\7\t\2\2[]\7\16"+
		"\2\2\\[\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^`\7\7\2\2_a\7\16\2\2`_\3\2\2\2`a"+
		"\3\2\2\2ac\3\2\2\2bZ\3\2\2\2bc\3\2\2\2cd\3\2\2\2df\5\24\13\2eg\7\16\2"+
		"\2fe\3\2\2\2fg\3\2\2\2g\23\3\2\2\2hi\7\n\2\2i\25\3\2\2\2\24!%)\62\66:"+
		"<@EIMOSX\\`bf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}