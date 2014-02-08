package com.tsingxu.test.parser.calculator;// Generated from D:\antlr\Calculator.g4 by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculatorParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, EQ=3, NAME=4, ADD=5, MINUS=6, MUL=7, DIV=8, NUMBER=9, 
		WS=10;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "'('", "'='", "NAME", "'+'", "'-'", "'*'", "'/'", 
		"NUMBER", "WS"
	};
	public static final int
		RULE_start = 0, RULE_assign = 1, RULE_cal = 2, RULE_minus_e = 3, RULE_mul_e = 4, 
		RULE_div_e = 5, RULE_unit = 6;
	public static final String[] ruleNames = {
		"start", "assign", "cal", "minus_e", "mul_e", "div_e", "unit"
	};

	@Override
	public String getGrammarFileName() { return "Calculator.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public CalculatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CalculatorParser.EOF, 0); }
		public CalContext cal() {
			return getRuleContext(CalContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(14); assign();
				}
				break;

			case 2:
				{
				setState(15); cal();
				}
				break;
			}
			setState(18); match(EOF);
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

	public static class AssignContext extends ParserRuleContext {
		public CalContext cal() {
			return getRuleContext(CalContext.class,0);
		}
		public TerminalNode NAME() { return getToken(CalculatorParser.NAME, 0); }
		public TerminalNode EQ() { return getToken(CalculatorParser.EQ, 0); }
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20); match(NAME);
			setState(21); match(EQ);
			setState(22); cal();
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

	public static class CalContext extends ParserRuleContext {
		public List<Minus_eContext> minus_e() {
			return getRuleContexts(Minus_eContext.class);
		}
		public Minus_eContext minus_e(int i) {
			return getRuleContext(Minus_eContext.class,i);
		}
		public TerminalNode ADD(int i) {
			return getToken(CalculatorParser.ADD, i);
		}
		public List<TerminalNode> ADD() { return getTokens(CalculatorParser.ADD); }
		public CalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cal; }
	}

	public final CalContext cal() throws RecognitionException {
		CalContext _localctx = new CalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_cal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24); minus_e();
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD) {
				{
				{
				setState(25); match(ADD);
				setState(26); minus_e();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Minus_eContext extends ParserRuleContext {
		public List<Mul_eContext> mul_e() {
			return getRuleContexts(Mul_eContext.class);
		}
		public TerminalNode MINUS(int i) {
			return getToken(CalculatorParser.MINUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(CalculatorParser.MINUS); }
		public Mul_eContext mul_e(int i) {
			return getRuleContext(Mul_eContext.class,i);
		}
		public Minus_eContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minus_e; }
	}

	public final Minus_eContext minus_e() throws RecognitionException {
		Minus_eContext _localctx = new Minus_eContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_minus_e);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32); mul_e();
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MINUS) {
				{
				{
				setState(33); match(MINUS);
				setState(34); mul_e();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Mul_eContext extends ParserRuleContext {
		public List<TerminalNode> MUL() { return getTokens(CalculatorParser.MUL); }
		public List<Div_eContext> div_e() {
			return getRuleContexts(Div_eContext.class);
		}
		public Div_eContext div_e(int i) {
			return getRuleContext(Div_eContext.class,i);
		}
		public TerminalNode MUL(int i) {
			return getToken(CalculatorParser.MUL, i);
		}
		public Mul_eContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul_e; }
	}

	public final Mul_eContext mul_e() throws RecognitionException {
		Mul_eContext _localctx = new Mul_eContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_mul_e);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); div_e();
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MUL) {
				{
				{
				setState(41); match(MUL);
				setState(42); div_e();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Div_eContext extends ParserRuleContext {
		public List<TerminalNode> DIV() { return getTokens(CalculatorParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(CalculatorParser.DIV, i);
		}
		public List<UnitContext> unit() {
			return getRuleContexts(UnitContext.class);
		}
		public UnitContext unit(int i) {
			return getRuleContext(UnitContext.class,i);
		}
		public Div_eContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_div_e; }
	}

	public final Div_eContext div_e() throws RecognitionException {
		Div_eContext _localctx = new Div_eContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_div_e);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); unit();
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DIV) {
				{
				{
				setState(49); match(DIV);
				setState(50); unit();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class UnitContext extends ParserRuleContext {
		public CalContext cal() {
			return getRuleContext(CalContext.class,0);
		}
		public TerminalNode NAME() { return getToken(CalculatorParser.NAME, 0); }
		public TerminalNode NUMBER() { return getToken(CalculatorParser.NUMBER, 0); }
		public UnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit; }
	}

	public final UnitContext unit() throws RecognitionException {
		UnitContext _localctx = new UnitContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_unit);
		try {
			setState(62);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(56); match(NUMBER);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(57); match(NAME);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(58); match(2);
				setState(59); cal();
				setState(60); match(1);
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

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\fC\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\5\2\23\n\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4\36\n\4\f\4\16\4!\13\4\3\5\3\5\3\5\7\5"+
		"&\n\5\f\5\16\5)\13\5\3\6\3\6\3\6\7\6.\n\6\f\6\16\6\61\13\6\3\7\3\7\3\7"+
		"\7\7\66\n\7\f\7\16\79\13\7\3\b\3\b\3\b\3\b\3\b\3\b\5\bA\n\b\3\b\2\t\2"+
		"\4\6\b\n\f\16\2\2B\2\22\3\2\2\2\4\26\3\2\2\2\6\32\3\2\2\2\b\"\3\2\2\2"+
		"\n*\3\2\2\2\f\62\3\2\2\2\16@\3\2\2\2\20\23\5\4\3\2\21\23\5\6\4\2\22\20"+
		"\3\2\2\2\22\21\3\2\2\2\23\24\3\2\2\2\24\25\7\2\2\3\25\3\3\2\2\2\26\27"+
		"\7\6\2\2\27\30\7\5\2\2\30\31\5\6\4\2\31\5\3\2\2\2\32\37\5\b\5\2\33\34"+
		"\7\7\2\2\34\36\5\b\5\2\35\33\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2"+
		"\2\2 \7\3\2\2\2!\37\3\2\2\2\"\'\5\n\6\2#$\7\b\2\2$&\5\n\6\2%#\3\2\2\2"+
		"&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\t\3\2\2\2)\'\3\2\2\2*/\5\f\7\2+,\7\t"+
		"\2\2,.\5\f\7\2-+\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\13\3\2\2"+
		"\2\61/\3\2\2\2\62\67\5\16\b\2\63\64\7\n\2\2\64\66\5\16\b\2\65\63\3\2\2"+
		"\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\r\3\2\2\29\67\3\2\2\2:A\7\13"+
		"\2\2;A\7\6\2\2<=\7\4\2\2=>\5\6\4\2>?\7\3\2\2?A\3\2\2\2@:\3\2\2\2@;\3\2"+
		"\2\2@<\3\2\2\2A\17\3\2\2\2\b\22\37\'/\67@";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}