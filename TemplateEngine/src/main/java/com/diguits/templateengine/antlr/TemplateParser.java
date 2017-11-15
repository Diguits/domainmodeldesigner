// Generated from Template.g4 by ANTLR 4.5.1
package com.diguits.templateengine.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TemplateParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SPACE=1, ENTER=2, TAB=3, RETURN=4, ATSCAPE=5, OPENIC=6, OPENTF=7, CLOSETF=8, 
		OPENTI=9, CLOSETI=10, OPENEV=11, CLOSEEV=12, OPENCS=13, CLOSECS=14, ANYCHAR=15;
	public static final int
		RULE_template = 0, RULE_templeteImportDeclaration = 1, RULE_importDeclarations = 2, 
		RULE_templateFunctions = 3, RULE_functions = 4, RULE_templateStatements = 5, 
		RULE_templateStatement = 6, RULE_constantStatement = 7, RULE_constantStatementParts = 8, 
		RULE_constantStatementPart = 9, RULE_constantExpression = 10, RULE_codeStatement = 11, 
		RULE_codeStatementParts = 12, RULE_codeStatementPart = 13, RULE_codeExpression = 14, 
		RULE_inlineConstant = 15, RULE_inlineConstantStatement = 16, RULE_inlineConstantStatementParts = 17, 
		RULE_inlineConstantStatementPart = 18, RULE_inlineConstantExpression = 19, 
		RULE_evalValue = 20, RULE_evalExpression = 21, RULE_anything = 22, RULE_anychar = 23, 
		RULE_emptyCharSecuence = 24, RULE_emptyChar = 25, RULE_inlineAnything = 26, 
		RULE_inlineAnychar = 27;
	public static final String[] ruleNames = {
		"template", "templeteImportDeclaration", "importDeclarations", "templateFunctions", 
		"functions", "templateStatements", "templateStatement", "constantStatement", 
		"constantStatementParts", "constantStatementPart", "constantExpression", 
		"codeStatement", "codeStatementParts", "codeStatementPart", "codeExpression", 
		"inlineConstant", "inlineConstantStatement", "inlineConstantStatementParts", 
		"inlineConstantStatementPart", "inlineConstantExpression", "evalValue", 
		"evalExpression", "anything", "anychar", "emptyCharSecuence", "emptyChar", 
		"inlineAnything", "inlineAnychar"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "' '", "'\n'", "'\t'", "'\r'", "'(@)'", "'@:'", "'@#'", "'#@'", 
		"'@['", "']@'", "'@('", "')@'", "'@{'", "'}@'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SPACE", "ENTER", "TAB", "RETURN", "ATSCAPE", "OPENIC", "OPENTF", 
		"CLOSETF", "OPENTI", "CLOSETI", "OPENEV", "CLOSEEV", "OPENCS", "CLOSECS", 
		"ANYCHAR"
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
	public String getGrammarFileName() { return "Template.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TemplateParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TemplateContext extends ParserRuleContext {
		public TemplateStatementsContext templateStatements() {
			return getRuleContext(TemplateStatementsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TemplateParser.EOF, 0); }
		public List<EmptyCharSecuenceContext> emptyCharSecuence() {
			return getRuleContexts(EmptyCharSecuenceContext.class);
		}
		public EmptyCharSecuenceContext emptyCharSecuence(int i) {
			return getRuleContext(EmptyCharSecuenceContext.class,i);
		}
		public TempleteImportDeclarationContext templeteImportDeclaration() {
			return getRuleContext(TempleteImportDeclarationContext.class,0);
		}
		public TemplateFunctionsContext templateFunctions() {
			return getRuleContext(TemplateFunctionsContext.class,0);
		}
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(56);
				emptyCharSecuence();
				}
				break;
			}
			setState(60);
			_la = _input.LA(1);
			if (_la==OPENTI) {
				{
				setState(59);
				templeteImportDeclaration();
				}
			}

			setState(63);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(62);
				emptyCharSecuence();
				}
				break;
			}
			setState(66);
			_la = _input.LA(1);
			if (_la==OPENTF) {
				{
				setState(65);
				templateFunctions();
				}
			}

			setState(68);
			templateStatements();
			setState(69);
			match(EOF);
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

	public static class TempleteImportDeclarationContext extends ParserRuleContext {
		public TerminalNode OPENTI() { return getToken(TemplateParser.OPENTI, 0); }
		public ImportDeclarationsContext importDeclarations() {
			return getRuleContext(ImportDeclarationsContext.class,0);
		}
		public TerminalNode CLOSETI() { return getToken(TemplateParser.CLOSETI, 0); }
		public TempleteImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templeteImportDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitTempleteImportDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TempleteImportDeclarationContext templeteImportDeclaration() throws RecognitionException {
		TempleteImportDeclarationContext _localctx = new TempleteImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_templeteImportDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(OPENTI);
			setState(72);
			importDeclarations();
			setState(73);
			match(CLOSETI);
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

	public static class ImportDeclarationsContext extends ParserRuleContext {
		public AnythingContext anything() {
			return getRuleContext(AnythingContext.class,0);
		}
		public ImportDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclarations; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitImportDeclarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclarationsContext importDeclarations() throws RecognitionException {
		ImportDeclarationsContext _localctx = new ImportDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importDeclarations);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			anything();
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

	public static class TemplateFunctionsContext extends ParserRuleContext {
		public TerminalNode OPENTF() { return getToken(TemplateParser.OPENTF, 0); }
		public FunctionsContext functions() {
			return getRuleContext(FunctionsContext.class,0);
		}
		public TerminalNode CLOSETF() { return getToken(TemplateParser.CLOSETF, 0); }
		public TemplateFunctionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateFunctions; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitTemplateFunctions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateFunctionsContext templateFunctions() throws RecognitionException {
		TemplateFunctionsContext _localctx = new TemplateFunctionsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_templateFunctions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(OPENTF);
			setState(78);
			functions();
			setState(79);
			match(CLOSETF);
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

	public static class FunctionsContext extends ParserRuleContext {
		public TemplateStatementsContext templateStatements() {
			return getRuleContext(TemplateStatementsContext.class,0);
		}
		public FunctionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functions; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitFunctions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionsContext functions() throws RecognitionException {
		FunctionsContext _localctx = new FunctionsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			templateStatements();
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

	public static class TemplateStatementsContext extends ParserRuleContext {
		public List<TemplateStatementContext> templateStatement() {
			return getRuleContexts(TemplateStatementContext.class);
		}
		public TemplateStatementContext templateStatement(int i) {
			return getRuleContext(TemplateStatementContext.class,i);
		}
		public TemplateStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateStatements; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitTemplateStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateStatementsContext templateStatements() throws RecognitionException {
		TemplateStatementsContext _localctx = new TemplateStatementsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_templateStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83);
				templateStatement();
				}
				}
				setState(86); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPACE) | (1L << ENTER) | (1L << TAB) | (1L << RETURN) | (1L << ATSCAPE) | (1L << OPENEV) | (1L << OPENCS) | (1L << ANYCHAR))) != 0) );
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

	public static class TemplateStatementContext extends ParserRuleContext {
		public ConstantStatementContext constantStatement() {
			return getRuleContext(ConstantStatementContext.class,0);
		}
		public CodeStatementContext codeStatement() {
			return getRuleContext(CodeStatementContext.class,0);
		}
		public TemplateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitTemplateStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateStatementContext templateStatement() throws RecognitionException {
		TemplateStatementContext _localctx = new TemplateStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_templateStatement);
		try {
			setState(90);
			switch (_input.LA(1)) {
			case SPACE:
			case ENTER:
			case TAB:
			case RETURN:
			case ATSCAPE:
			case OPENEV:
			case ANYCHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				constantStatement();
				}
				break;
			case OPENCS:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				codeStatement();
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

	public static class ConstantStatementContext extends ParserRuleContext {
		public ConstantStatementPartsContext constantStatementParts() {
			return getRuleContext(ConstantStatementPartsContext.class,0);
		}
		public ConstantStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitConstantStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantStatementContext constantStatement() throws RecognitionException {
		ConstantStatementContext _localctx = new ConstantStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constantStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			constantStatementParts();
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

	public static class ConstantStatementPartsContext extends ParserRuleContext {
		public List<ConstantStatementPartContext> constantStatementPart() {
			return getRuleContexts(ConstantStatementPartContext.class);
		}
		public ConstantStatementPartContext constantStatementPart(int i) {
			return getRuleContext(ConstantStatementPartContext.class,i);
		}
		public ConstantStatementPartsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantStatementParts; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitConstantStatementParts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantStatementPartsContext constantStatementParts() throws RecognitionException {
		ConstantStatementPartsContext _localctx = new ConstantStatementPartsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_constantStatementParts);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(95); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(94);
					constantStatementPart();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(97); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ConstantStatementPartContext extends ParserRuleContext {
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public EvalValueContext evalValue() {
			return getRuleContext(EvalValueContext.class,0);
		}
		public ConstantStatementPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantStatementPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitConstantStatementPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantStatementPartContext constantStatementPart() throws RecognitionException {
		ConstantStatementPartContext _localctx = new ConstantStatementPartContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_constantStatementPart);
		try {
			setState(101);
			switch (_input.LA(1)) {
			case SPACE:
			case ENTER:
			case TAB:
			case RETURN:
			case ATSCAPE:
			case ANYCHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				constantExpression();
				}
				break;
			case OPENEV:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				evalValue();
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

	public static class ConstantExpressionContext extends ParserRuleContext {
		public AnythingContext anything() {
			return getRuleContext(AnythingContext.class,0);
		}
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitConstantExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_constantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			anything();
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

	public static class CodeStatementContext extends ParserRuleContext {
		public TerminalNode OPENCS() { return getToken(TemplateParser.OPENCS, 0); }
		public CodeStatementPartsContext codeStatementParts() {
			return getRuleContext(CodeStatementPartsContext.class,0);
		}
		public TerminalNode CLOSECS() { return getToken(TemplateParser.CLOSECS, 0); }
		public CodeStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitCodeStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeStatementContext codeStatement() throws RecognitionException {
		CodeStatementContext _localctx = new CodeStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_codeStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(OPENCS);
			setState(106);
			codeStatementParts();
			setState(107);
			match(CLOSECS);
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

	public static class CodeStatementPartsContext extends ParserRuleContext {
		public List<CodeStatementPartContext> codeStatementPart() {
			return getRuleContexts(CodeStatementPartContext.class);
		}
		public CodeStatementPartContext codeStatementPart(int i) {
			return getRuleContext(CodeStatementPartContext.class,i);
		}
		public CodeStatementPartsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeStatementParts; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitCodeStatementParts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeStatementPartsContext codeStatementParts() throws RecognitionException {
		CodeStatementPartsContext _localctx = new CodeStatementPartsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_codeStatementParts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(109);
				codeStatementPart();
				}
				}
				setState(112); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPACE) | (1L << ENTER) | (1L << TAB) | (1L << RETURN) | (1L << ATSCAPE) | (1L << OPENIC) | (1L << OPENEV) | (1L << ANYCHAR))) != 0) );
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

	public static class CodeStatementPartContext extends ParserRuleContext {
		public CodeExpressionContext codeExpression() {
			return getRuleContext(CodeExpressionContext.class,0);
		}
		public InlineConstantContext inlineConstant() {
			return getRuleContext(InlineConstantContext.class,0);
		}
		public EvalValueContext evalValue() {
			return getRuleContext(EvalValueContext.class,0);
		}
		public CodeStatementPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeStatementPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitCodeStatementPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeStatementPartContext codeStatementPart() throws RecognitionException {
		CodeStatementPartContext _localctx = new CodeStatementPartContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_codeStatementPart);
		try {
			setState(117);
			switch (_input.LA(1)) {
			case SPACE:
			case ENTER:
			case TAB:
			case RETURN:
			case ATSCAPE:
			case ANYCHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				codeExpression();
				}
				break;
			case OPENIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				inlineConstant();
				}
				break;
			case OPENEV:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				evalValue();
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

	public static class CodeExpressionContext extends ParserRuleContext {
		public AnythingContext anything() {
			return getRuleContext(AnythingContext.class,0);
		}
		public CodeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitCodeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeExpressionContext codeExpression() throws RecognitionException {
		CodeExpressionContext _localctx = new CodeExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_codeExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			anything();
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

	public static class InlineConstantContext extends ParserRuleContext {
		public TerminalNode OPENIC() { return getToken(TemplateParser.OPENIC, 0); }
		public InlineConstantStatementContext inlineConstantStatement() {
			return getRuleContext(InlineConstantStatementContext.class,0);
		}
		public TerminalNode ENTER() { return getToken(TemplateParser.ENTER, 0); }
		public InlineConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineConstant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitInlineConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineConstantContext inlineConstant() throws RecognitionException {
		InlineConstantContext _localctx = new InlineConstantContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_inlineConstant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(OPENIC);
			setState(122);
			inlineConstantStatement();
			setState(123);
			match(ENTER);
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

	public static class InlineConstantStatementContext extends ParserRuleContext {
		public InlineConstantStatementPartsContext inlineConstantStatementParts() {
			return getRuleContext(InlineConstantStatementPartsContext.class,0);
		}
		public InlineConstantStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineConstantStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitInlineConstantStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineConstantStatementContext inlineConstantStatement() throws RecognitionException {
		InlineConstantStatementContext _localctx = new InlineConstantStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_inlineConstantStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			inlineConstantStatementParts();
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

	public static class InlineConstantStatementPartsContext extends ParserRuleContext {
		public List<InlineConstantStatementPartContext> inlineConstantStatementPart() {
			return getRuleContexts(InlineConstantStatementPartContext.class);
		}
		public InlineConstantStatementPartContext inlineConstantStatementPart(int i) {
			return getRuleContext(InlineConstantStatementPartContext.class,i);
		}
		public InlineConstantStatementPartsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineConstantStatementParts; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitInlineConstantStatementParts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineConstantStatementPartsContext inlineConstantStatementParts() throws RecognitionException {
		InlineConstantStatementPartsContext _localctx = new InlineConstantStatementPartsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_inlineConstantStatementParts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(127);
				inlineConstantStatementPart();
				}
				}
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPACE) | (1L << TAB) | (1L << ATSCAPE) | (1L << OPENEV) | (1L << ANYCHAR))) != 0) );
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

	public static class InlineConstantStatementPartContext extends ParserRuleContext {
		public InlineConstantExpressionContext inlineConstantExpression() {
			return getRuleContext(InlineConstantExpressionContext.class,0);
		}
		public EvalValueContext evalValue() {
			return getRuleContext(EvalValueContext.class,0);
		}
		public InlineConstantStatementPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineConstantStatementPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitInlineConstantStatementPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineConstantStatementPartContext inlineConstantStatementPart() throws RecognitionException {
		InlineConstantStatementPartContext _localctx = new InlineConstantStatementPartContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_inlineConstantStatementPart);
		try {
			setState(134);
			switch (_input.LA(1)) {
			case SPACE:
			case TAB:
			case ATSCAPE:
			case ANYCHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				inlineConstantExpression();
				}
				break;
			case OPENEV:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				evalValue();
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

	public static class InlineConstantExpressionContext extends ParserRuleContext {
		public InlineAnythingContext inlineAnything() {
			return getRuleContext(InlineAnythingContext.class,0);
		}
		public InlineConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineConstantExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitInlineConstantExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineConstantExpressionContext inlineConstantExpression() throws RecognitionException {
		InlineConstantExpressionContext _localctx = new InlineConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_inlineConstantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			inlineAnything();
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

	public static class EvalValueContext extends ParserRuleContext {
		public TerminalNode OPENEV() { return getToken(TemplateParser.OPENEV, 0); }
		public EvalExpressionContext evalExpression() {
			return getRuleContext(EvalExpressionContext.class,0);
		}
		public TerminalNode CLOSEEV() { return getToken(TemplateParser.CLOSEEV, 0); }
		public EvalValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evalValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitEvalValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EvalValueContext evalValue() throws RecognitionException {
		EvalValueContext _localctx = new EvalValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_evalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(OPENEV);
			setState(139);
			evalExpression();
			setState(140);
			match(CLOSEEV);
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

	public static class EvalExpressionContext extends ParserRuleContext {
		public AnythingContext anything() {
			return getRuleContext(AnythingContext.class,0);
		}
		public EvalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evalExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitEvalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EvalExpressionContext evalExpression() throws RecognitionException {
		EvalExpressionContext _localctx = new EvalExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_evalExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			anything();
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

	public static class AnythingContext extends ParserRuleContext {
		public List<AnycharContext> anychar() {
			return getRuleContexts(AnycharContext.class);
		}
		public AnycharContext anychar(int i) {
			return getRuleContext(AnycharContext.class,i);
		}
		public AnythingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anything; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitAnything(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnythingContext anything() throws RecognitionException {
		AnythingContext _localctx = new AnythingContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_anything);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(145); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(144);
					anychar();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(147); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class AnycharContext extends ParserRuleContext {
		public EmptyCharContext emptyChar() {
			return getRuleContext(EmptyCharContext.class,0);
		}
		public TerminalNode ATSCAPE() { return getToken(TemplateParser.ATSCAPE, 0); }
		public TerminalNode ANYCHAR() { return getToken(TemplateParser.ANYCHAR, 0); }
		public AnycharContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anychar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitAnychar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnycharContext anychar() throws RecognitionException {
		AnycharContext _localctx = new AnycharContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_anychar);
		try {
			setState(152);
			switch (_input.LA(1)) {
			case SPACE:
			case ENTER:
			case TAB:
			case RETURN:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				emptyChar();
				}
				break;
			case ATSCAPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				match(ATSCAPE);
				}
				break;
			case ANYCHAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(151);
				match(ANYCHAR);
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

	public static class EmptyCharSecuenceContext extends ParserRuleContext {
		public List<EmptyCharContext> emptyChar() {
			return getRuleContexts(EmptyCharContext.class);
		}
		public EmptyCharContext emptyChar(int i) {
			return getRuleContext(EmptyCharContext.class,i);
		}
		public EmptyCharSecuenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyCharSecuence; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitEmptyCharSecuence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyCharSecuenceContext emptyCharSecuence() throws RecognitionException {
		EmptyCharSecuenceContext _localctx = new EmptyCharSecuenceContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_emptyCharSecuence);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(155); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(154);
					emptyChar();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(157); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class EmptyCharContext extends ParserRuleContext {
		public TerminalNode SPACE() { return getToken(TemplateParser.SPACE, 0); }
		public TerminalNode TAB() { return getToken(TemplateParser.TAB, 0); }
		public TerminalNode RETURN() { return getToken(TemplateParser.RETURN, 0); }
		public TerminalNode ENTER() { return getToken(TemplateParser.ENTER, 0); }
		public EmptyCharContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyChar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitEmptyChar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyCharContext emptyChar() throws RecognitionException {
		EmptyCharContext _localctx = new EmptyCharContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_emptyChar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPACE) | (1L << ENTER) | (1L << TAB) | (1L << RETURN))) != 0)) ) {
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

	public static class InlineAnythingContext extends ParserRuleContext {
		public List<InlineAnycharContext> inlineAnychar() {
			return getRuleContexts(InlineAnycharContext.class);
		}
		public InlineAnycharContext inlineAnychar(int i) {
			return getRuleContext(InlineAnycharContext.class,i);
		}
		public InlineAnythingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineAnything; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitInlineAnything(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineAnythingContext inlineAnything() throws RecognitionException {
		InlineAnythingContext _localctx = new InlineAnythingContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_inlineAnything);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(162); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(161);
					inlineAnychar();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(164); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class InlineAnycharContext extends ParserRuleContext {
		public TerminalNode SPACE() { return getToken(TemplateParser.SPACE, 0); }
		public TerminalNode TAB() { return getToken(TemplateParser.TAB, 0); }
		public TerminalNode ATSCAPE() { return getToken(TemplateParser.ATSCAPE, 0); }
		public TerminalNode ANYCHAR() { return getToken(TemplateParser.ANYCHAR, 0); }
		public InlineAnycharContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineAnychar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateVisitor ) return ((TemplateVisitor<? extends T>)visitor).visitInlineAnychar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineAnycharContext inlineAnychar() throws RecognitionException {
		InlineAnycharContext _localctx = new InlineAnycharContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_inlineAnychar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPACE) | (1L << TAB) | (1L << ATSCAPE) | (1L << ANYCHAR))) != 0)) ) {
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\21\u00ab\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\5\2<\n\2\3\2\5\2?\n\2\3\2"+
		"\5\2B\n\2\3\2\5\2E\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\7\6\7W\n\7\r\7\16\7X\3\b\3\b\5\b]\n\b\3\t\3\t\3\n\6\nb"+
		"\n\n\r\n\16\nc\3\13\3\13\5\13h\n\13\3\f\3\f\3\r\3\r\3\r\3\r\3\16\6\16"+
		"q\n\16\r\16\16\16r\3\17\3\17\3\17\5\17x\n\17\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\23\6\23\u0083\n\23\r\23\16\23\u0084\3\24\3\24\5\24\u0089"+
		"\n\24\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\30\6\30\u0094\n\30\r\30"+
		"\16\30\u0095\3\31\3\31\3\31\5\31\u009b\n\31\3\32\6\32\u009e\n\32\r\32"+
		"\16\32\u009f\3\33\3\33\3\34\6\34\u00a5\n\34\r\34\16\34\u00a6\3\35\3\35"+
		"\3\35\2\2\36\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668\2\4\3\2\3\6\6\2\3\3\5\5\7\7\21\21\u00a0\2;\3\2\2\2\4I\3\2\2\2\6M"+
		"\3\2\2\2\bO\3\2\2\2\nS\3\2\2\2\fV\3\2\2\2\16\\\3\2\2\2\20^\3\2\2\2\22"+
		"a\3\2\2\2\24g\3\2\2\2\26i\3\2\2\2\30k\3\2\2\2\32p\3\2\2\2\34w\3\2\2\2"+
		"\36y\3\2\2\2 {\3\2\2\2\"\177\3\2\2\2$\u0082\3\2\2\2&\u0088\3\2\2\2(\u008a"+
		"\3\2\2\2*\u008c\3\2\2\2,\u0090\3\2\2\2.\u0093\3\2\2\2\60\u009a\3\2\2\2"+
		"\62\u009d\3\2\2\2\64\u00a1\3\2\2\2\66\u00a4\3\2\2\28\u00a8\3\2\2\2:<\5"+
		"\62\32\2;:\3\2\2\2;<\3\2\2\2<>\3\2\2\2=?\5\4\3\2>=\3\2\2\2>?\3\2\2\2?"+
		"A\3\2\2\2@B\5\62\32\2A@\3\2\2\2AB\3\2\2\2BD\3\2\2\2CE\5\b\5\2DC\3\2\2"+
		"\2DE\3\2\2\2EF\3\2\2\2FG\5\f\7\2GH\7\2\2\3H\3\3\2\2\2IJ\7\13\2\2JK\5\6"+
		"\4\2KL\7\f\2\2L\5\3\2\2\2MN\5.\30\2N\7\3\2\2\2OP\7\t\2\2PQ\5\n\6\2QR\7"+
		"\n\2\2R\t\3\2\2\2ST\5\f\7\2T\13\3\2\2\2UW\5\16\b\2VU\3\2\2\2WX\3\2\2\2"+
		"XV\3\2\2\2XY\3\2\2\2Y\r\3\2\2\2Z]\5\20\t\2[]\5\30\r\2\\Z\3\2\2\2\\[\3"+
		"\2\2\2]\17\3\2\2\2^_\5\22\n\2_\21\3\2\2\2`b\5\24\13\2a`\3\2\2\2bc\3\2"+
		"\2\2ca\3\2\2\2cd\3\2\2\2d\23\3\2\2\2eh\5\26\f\2fh\5*\26\2ge\3\2\2\2gf"+
		"\3\2\2\2h\25\3\2\2\2ij\5.\30\2j\27\3\2\2\2kl\7\17\2\2lm\5\32\16\2mn\7"+
		"\20\2\2n\31\3\2\2\2oq\5\34\17\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2"+
		"\2s\33\3\2\2\2tx\5\36\20\2ux\5 \21\2vx\5*\26\2wt\3\2\2\2wu\3\2\2\2wv\3"+
		"\2\2\2x\35\3\2\2\2yz\5.\30\2z\37\3\2\2\2{|\7\b\2\2|}\5\"\22\2}~\7\4\2"+
		"\2~!\3\2\2\2\177\u0080\5$\23\2\u0080#\3\2\2\2\u0081\u0083\5&\24\2\u0082"+
		"\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2"+
		"\2\2\u0085%\3\2\2\2\u0086\u0089\5(\25\2\u0087\u0089\5*\26\2\u0088\u0086"+
		"\3\2\2\2\u0088\u0087\3\2\2\2\u0089\'\3\2\2\2\u008a\u008b\5\66\34\2\u008b"+
		")\3\2\2\2\u008c\u008d\7\r\2\2\u008d\u008e\5,\27\2\u008e\u008f\7\16\2\2"+
		"\u008f+\3\2\2\2\u0090\u0091\5.\30\2\u0091-\3\2\2\2\u0092\u0094\5\60\31"+
		"\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096"+
		"\3\2\2\2\u0096/\3\2\2\2\u0097\u009b\5\64\33\2\u0098\u009b\7\7\2\2\u0099"+
		"\u009b\7\21\2\2\u009a\u0097\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u0099\3"+
		"\2\2\2\u009b\61\3\2\2\2\u009c\u009e\5\64\33\2\u009d\u009c\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\63\3\2\2"+
		"\2\u00a1\u00a2\t\2\2\2\u00a2\65\3\2\2\2\u00a3\u00a5\58\35\2\u00a4\u00a3"+
		"\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\67\3\2\2\2\u00a8\u00a9\t\3\2\2\u00a99\3\2\2\2\22;>ADX\\cgrw\u0084\u0088"+
		"\u0095\u009a\u009f\u00a6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}