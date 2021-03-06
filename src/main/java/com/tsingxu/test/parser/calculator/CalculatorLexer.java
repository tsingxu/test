package com.tsingxu.test.parser.calculator;// Generated from D:\antlr\Calculator.g4 by ANTLR 4.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculatorLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, EQ=3, NAME=4, ADD=5, MINUS=6, MUL=7, DIV=8, NUMBER=9, 
		WS=10;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"')'", "'('", "'='", "NAME", "'+'", "'-'", "'*'", "'/'", "NUMBER", "WS"
	};
	public static final String[] ruleNames = {
		"T__1", "T__0", "EQ", "NAME", "ADD", "MINUS", "MUL", "DIV", "NUMBER", 
		"WS"
	};


	public CalculatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Calculator.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 9: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = HIDDEN;  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\f;\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\3\3\3\3\4\3\4\3\5\6\5\37\n\5\r\5\16\5 \3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\6\n,\n\n\r\n\16\n-\3\n\3\n\6\n\62\n\n\r\n\16\n\63\5"+
		"\n\66\n\n\3\13\3\13\3\13\3\13\2\f\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1"+
		"\17\t\1\21\n\1\23\13\1\25\f\2\3\2\5\5\2C\\aac|\3\2\62;\4\2\13\13\"\">"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2"+
		"\5\31\3\2\2\2\7\33\3\2\2\2\t\36\3\2\2\2\13\"\3\2\2\2\r$\3\2\2\2\17&\3"+
		"\2\2\2\21(\3\2\2\2\23+\3\2\2\2\25\67\3\2\2\2\27\30\7+\2\2\30\4\3\2\2\2"+
		"\31\32\7*\2\2\32\6\3\2\2\2\33\34\7?\2\2\34\b\3\2\2\2\35\37\t\2\2\2\36"+
		"\35\3\2\2\2\37 \3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\n\3\2\2\2\"#\7-\2\2#\f"+
		"\3\2\2\2$%\7/\2\2%\16\3\2\2\2&\'\7,\2\2\'\20\3\2\2\2()\7\61\2\2)\22\3"+
		"\2\2\2*,\t\3\2\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\65\3\2\2\2/"+
		"\61\7\60\2\2\60\62\t\3\2\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63"+
		"\64\3\2\2\2\64\66\3\2\2\2\65/\3\2\2\2\65\66\3\2\2\2\66\24\3\2\2\2\678"+
		"\t\4\2\289\3\2\2\29:\b\13\2\2:\26\3\2\2\2\7\2 -\63\65";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}