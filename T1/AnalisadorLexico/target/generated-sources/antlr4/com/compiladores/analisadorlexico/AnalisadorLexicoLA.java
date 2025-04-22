// Generated from com/compiladores/analisadorlexico/AnalisadorLexicoLA.g4 by ANTLR 4.7.2
package com.compiladores.analisadorlexico;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AnalisadorLexicoLA extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PALAVRA_CHAVE=1, OP_REL=2, OP_ARITH=3, OP_LOGICO=4, NUM_INT=5, NUM_REAL=6, 
		IDENT=7, CADEIA=8, PARENTESES=9, COLCHETES=10, INTERVALO=11, ATTRIBUTE=12, 
		DELIMIT=13, SEPARADOR=14, ATRIBUICAO=15, ENDERECO=16, POINTER=17, COMMENT=18, 
		WHITE_SPACE=19, ERRO_CHAVES_EXTRA=20, ERRO_COMENT=21, ERRO_CADEIA=22, 
		ERRO=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PALAVRA_CHAVE", "OP_REL", "OP_ARITH", "OP_LOGICO", "NUM_INT", "NUM_REAL", 
			"IDENT", "CADEIA", "PARENTESES", "COLCHETES", "INTERVALO", "ATTRIBUTE", 
			"DELIMIT", "SEPARADOR", "ATRIBUICAO", "ENDERECO", "POINTER", "COMMENT", 
			"WHITE_SPACE", "ERRO_CHAVES_EXTRA", "ERRO_COMENT", "ERRO_CADEIA", "ERRO"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "'..'", 
			"'.'", "':'", "','", "'<-'", "'&'", "'^'", null, null, "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PALAVRA_CHAVE", "OP_REL", "OP_ARITH", "OP_LOGICO", "NUM_INT", 
			"NUM_REAL", "IDENT", "CADEIA", "PARENTESES", "COLCHETES", "INTERVALO", 
			"ATTRIBUTE", "DELIMIT", "SEPARADOR", "ATRIBUICAO", "ENDERECO", "POINTER", 
			"COMMENT", "WHITE_SPACE", "ERRO_CHAVES_EXTRA", "ERRO_COMENT", "ERRO_CADEIA", 
			"ERRO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public AnalisadorLexicoLA(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AnalisadorLexicoLA.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u019c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\u0121\n\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3\u012c\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5\u0136\n\5\3\6\6\6\u0139\n\6\r\6\16\6\u013a\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\7\b\u0143\n\b\f\b\16\b\u0146\13\b\3\b\5\b\u0149\n\b\3\b\7\b\u014c\n\b"+
		"\f\b\16\b\u014f\13\b\3\t\3\t\7\t\u0153\n\t\f\t\16\t\u0156\13\t\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\23\3\23\7\23\u0170\n\23\f\23\16\23\u0173\13"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\7\26\u0181"+
		"\n\26\f\26\16\26\u0184\13\26\3\26\5\26\u0187\n\26\3\26\3\26\5\26\u018b"+
		"\n\26\3\27\3\27\7\27\u018f\n\27\f\27\16\27\u0192\13\27\3\27\5\27\u0195"+
		"\n\27\3\27\3\27\5\27\u0199\n\27\3\30\3\30\2\2\31\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\3\2\n\6\2\'\',-//\61\61\3\2\62;\4\2C\\c|\5\2\62;C\\c"+
		"|\5\2\f\f\17\17$$\4\2]]__\6\2\f\f\17\17}}\177\177\5\2\13\f\17\17\"\"\2"+
		"\u01cf\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\3\u0120\3\2\2\2\5\u012b\3\2\2\2\7\u012d\3\2\2\2\t\u0135\3\2"+
		"\2\2\13\u0138\3\2\2\2\r\u013c\3\2\2\2\17\u0140\3\2\2\2\21\u0150\3\2\2"+
		"\2\23\u0159\3\2\2\2\25\u015b\3\2\2\2\27\u015d\3\2\2\2\31\u0160\3\2\2\2"+
		"\33\u0162\3\2\2\2\35\u0164\3\2\2\2\37\u0166\3\2\2\2!\u0169\3\2\2\2#\u016b"+
		"\3\2\2\2%\u016d\3\2\2\2\'\u0178\3\2\2\2)\u017c\3\2\2\2+\u017e\3\2\2\2"+
		"-\u018c\3\2\2\2/\u019a\3\2\2\2\61\62\7c\2\2\62\63\7n\2\2\63\64\7i\2\2"+
		"\64\65\7q\2\2\65\66\7t\2\2\66\67\7k\2\2\678\7v\2\289\7o\2\29\u0121\7q"+
		"\2\2:;\7h\2\2;<\7k\2\2<=\7o\2\2=>\7a\2\2>?\7c\2\2?@\7n\2\2@A\7i\2\2AB"+
		"\7q\2\2BC\7t\2\2CD\7k\2\2DE\7v\2\2EF\7o\2\2F\u0121\7q\2\2GH\7f\2\2HI\7"+
		"g\2\2IJ\7e\2\2JK\7n\2\2KL\7c\2\2LM\7t\2\2M\u0121\7g\2\2NO\7n\2\2OP\7k"+
		"\2\2PQ\7v\2\2QR\7g\2\2RS\7t\2\2ST\7c\2\2T\u0121\7n\2\2UV\7k\2\2VW\7p\2"+
		"\2WX\7v\2\2XY\7g\2\2YZ\7k\2\2Z[\7t\2\2[\u0121\7q\2\2\\]\7n\2\2]^\7g\2"+
		"\2^_\7k\2\2_\u0121\7c\2\2`a\7g\2\2ab\7u\2\2bc\7e\2\2cd\7t\2\2de\7g\2\2"+
		"ef\7x\2\2f\u0121\7c\2\2gh\7t\2\2hi\7g\2\2ij\7c\2\2j\u0121\7n\2\2kl\7n"+
		"\2\2lm\7q\2\2mn\7i\2\2no\7k\2\2op\7e\2\2p\u0121\7q\2\2qr\7g\2\2rs\7p\2"+
		"\2st\7s\2\2tu\7w\2\2uv\7c\2\2vw\7p\2\2wx\7v\2\2x\u0121\7q\2\2yz\7h\2\2"+
		"z{\7k\2\2{|\7o\2\2|}\7a\2\2}~\7g\2\2~\177\7p\2\2\177\u0080\7s\2\2\u0080"+
		"\u0081\7w\2\2\u0081\u0082\7c\2\2\u0082\u0083\7p\2\2\u0083\u0084\7v\2\2"+
		"\u0084\u0121\7q\2\2\u0085\u0086\7u\2\2\u0086\u0121\7g\2\2\u0087\u0088"+
		"\7g\2\2\u0088\u0089\7p\2\2\u0089\u008a\7v\2\2\u008a\u008b\7c\2\2\u008b"+
		"\u0121\7q\2\2\u008c\u008d\7u\2\2\u008d\u008e\7g\2\2\u008e\u008f\7p\2\2"+
		"\u008f\u0090\7c\2\2\u0090\u0121\7q\2\2\u0091\u0092\7h\2\2\u0092\u0093"+
		"\7k\2\2\u0093\u0094\7o\2\2\u0094\u0095\7a\2\2\u0095\u0096\7u\2\2\u0096"+
		"\u0121\7g\2\2\u0097\u0098\7e\2\2\u0098\u0099\7c\2\2\u0099\u009a\7u\2\2"+
		"\u009a\u0121\7q\2\2\u009b\u009c\7u\2\2\u009c\u009d\7g\2\2\u009d\u009e"+
		"\7l\2\2\u009e\u0121\7c\2\2\u009f\u00a0\7h\2\2\u00a0\u00a1\7k\2\2\u00a1"+
		"\u00a2\7o\2\2\u00a2\u00a3\7a\2\2\u00a3\u00a4\7e\2\2\u00a4\u00a5\7c\2\2"+
		"\u00a5\u00a6\7u\2\2\u00a6\u0121\7q\2\2\u00a7\u00a8\7r\2\2\u00a8\u00a9"+
		"\7c\2\2\u00a9\u00aa\7t\2\2\u00aa\u0121\7c\2\2\u00ab\u00ac\7c\2\2\u00ac"+
		"\u00ad\7v\2\2\u00ad\u0121\7g\2\2\u00ae\u00af\7h\2\2\u00af\u00b0\7c\2\2"+
		"\u00b0\u00b1\7e\2\2\u00b1\u0121\7c\2\2\u00b2\u00b3\7h\2\2\u00b3\u00b4"+
		"\7k\2\2\u00b4\u00b5\7o\2\2\u00b5\u00b6\7a\2\2\u00b6\u00b7\7r\2\2\u00b7"+
		"\u00b8\7c\2\2\u00b8\u00b9\7t\2\2\u00b9\u0121\7c\2\2\u00ba\u00bb\7t\2\2"+
		"\u00bb\u00bc\7g\2\2\u00bc\u00bd\7i\2\2\u00bd\u00be\7k\2\2\u00be\u00bf"+
		"\7u\2\2\u00bf\u00c0\7v\2\2\u00c0\u00c1\7t\2\2\u00c1\u0121\7q\2\2\u00c2"+
		"\u00c3\7h\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5\7o\2\2\u00c5\u00c6\7a\2\2"+
		"\u00c6\u00c7\7t\2\2\u00c7\u00c8\7g\2\2\u00c8\u00c9\7i\2\2\u00c9\u00ca"+
		"\7k\2\2\u00ca\u00cb\7u\2\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7t\2\2\u00cd"+
		"\u0121\7q\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7r\2\2"+
		"\u00d1\u0121\7q\2\2\u00d2\u00d3\7r\2\2\u00d3\u00d4\7t\2\2\u00d4\u00d5"+
		"\7q\2\2\u00d5\u00d6\7e\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7f\2\2\u00d8"+
		"\u00d9\7k\2\2\u00d9\u00da\7o\2\2\u00da\u00db\7g\2\2\u00db\u00dc\7p\2\2"+
		"\u00dc\u00dd\7v\2\2\u00dd\u0121\7q\2\2\u00de\u00df\7x\2\2\u00df\u00e0"+
		"\7c\2\2\u00e0\u0121\7t\2\2\u00e1\u00e2\7h\2\2\u00e2\u00e3\7k\2\2\u00e3"+
		"\u00e4\7o\2\2\u00e4\u00e5\7a\2\2\u00e5\u00e6\7r\2\2\u00e6\u00e7\7t\2\2"+
		"\u00e7\u00e8\7q\2\2\u00e8\u00e9\7e\2\2\u00e9\u00ea\7g\2\2\u00ea\u00eb"+
		"\7f\2\2\u00eb\u00ec\7k\2\2\u00ec\u00ed\7o\2\2\u00ed\u00ee\7g\2\2\u00ee"+
		"\u00ef\7p\2\2\u00ef\u00f0\7v\2\2\u00f0\u0121\7q\2\2\u00f1\u00f2\7h\2\2"+
		"\u00f2\u00f3\7w\2\2\u00f3\u00f4\7p\2\2\u00f4\u00f5\7e\2\2\u00f5\u00f6"+
		"\7c\2\2\u00f6\u0121\7q\2\2\u00f7\u00f8\7t\2\2\u00f8\u00f9\7g\2\2\u00f9"+
		"\u00fa\7v\2\2\u00fa\u00fb\7q\2\2\u00fb\u00fc\7t\2\2\u00fc\u00fd\7p\2\2"+
		"\u00fd\u0121\7g\2\2\u00fe\u00ff\7h\2\2\u00ff\u0100\7k\2\2\u0100\u0101"+
		"\7o\2\2\u0101\u0102\7a\2\2\u0102\u0103\7h\2\2\u0103\u0104\7w\2\2\u0104"+
		"\u0105\7p\2\2\u0105\u0106\7e\2\2\u0106\u0107\7c\2\2\u0107\u0121\7q\2\2"+
		"\u0108\u0109\7e\2\2\u0109\u010a\7q\2\2\u010a\u010b\7p\2\2\u010b\u010c"+
		"\7u\2\2\u010c\u010d\7v\2\2\u010d\u010e\7c\2\2\u010e\u010f\7p\2\2\u010f"+
		"\u0110\7v\2\2\u0110\u0121\7g\2\2\u0111\u0112\7h\2\2\u0112\u0113\7c\2\2"+
		"\u0113\u0114\7n\2\2\u0114\u0115\7u\2\2\u0115\u0121\7q\2\2\u0116\u0117"+
		"\7x\2\2\u0117\u0118\7g\2\2\u0118\u0119\7t\2\2\u0119\u011a\7f\2\2\u011a"+
		"\u011b\7c\2\2\u011b\u011c\7f\2\2\u011c\u011d\7g\2\2\u011d\u011e\7k\2\2"+
		"\u011e\u011f\7t\2\2\u011f\u0121\7q\2\2\u0120\61\3\2\2\2\u0120:\3\2\2\2"+
		"\u0120G\3\2\2\2\u0120N\3\2\2\2\u0120U\3\2\2\2\u0120\\\3\2\2\2\u0120`\3"+
		"\2\2\2\u0120g\3\2\2\2\u0120k\3\2\2\2\u0120q\3\2\2\2\u0120y\3\2\2\2\u0120"+
		"\u0085\3\2\2\2\u0120\u0087\3\2\2\2\u0120\u008c\3\2\2\2\u0120\u0091\3\2"+
		"\2\2\u0120\u0097\3\2\2\2\u0120\u009b\3\2\2\2\u0120\u009f\3\2\2\2\u0120"+
		"\u00a7\3\2\2\2\u0120\u00ab\3\2\2\2\u0120\u00ae\3\2\2\2\u0120\u00b2\3\2"+
		"\2\2\u0120\u00ba\3\2\2\2\u0120\u00c2\3\2\2\2\u0120\u00ce\3\2\2\2\u0120"+
		"\u00d2\3\2\2\2\u0120\u00de\3\2\2\2\u0120\u00e1\3\2\2\2\u0120\u00f1\3\2"+
		"\2\2\u0120\u00f7\3\2\2\2\u0120\u00fe\3\2\2\2\u0120\u0108\3\2\2\2\u0120"+
		"\u0111\3\2\2\2\u0120\u0116\3\2\2\2\u0121\4\3\2\2\2\u0122\u012c\7@\2\2"+
		"\u0123\u0124\7@\2\2\u0124\u012c\7?\2\2\u0125\u012c\7>\2\2\u0126\u0127"+
		"\7>\2\2\u0127\u012c\7?\2\2\u0128\u0129\7>\2\2\u0129\u012c\7@\2\2\u012a"+
		"\u012c\7?\2\2\u012b\u0122\3\2\2\2\u012b\u0123\3\2\2\2\u012b\u0125\3\2"+
		"\2\2\u012b\u0126\3\2\2\2\u012b\u0128\3\2\2\2\u012b\u012a\3\2\2\2\u012c"+
		"\6\3\2\2\2\u012d\u012e\t\2\2\2\u012e\b\3\2\2\2\u012f\u0136\7g\2\2\u0130"+
		"\u0131\7q\2\2\u0131\u0136\7w\2\2\u0132\u0133\7p\2\2\u0133\u0134\7c\2\2"+
		"\u0134\u0136\7q\2\2\u0135\u012f\3\2\2\2\u0135\u0130\3\2\2\2\u0135\u0132"+
		"\3\2\2\2\u0136\n\3\2\2\2\u0137\u0139\t\3\2\2\u0138\u0137\3\2\2\2\u0139"+
		"\u013a\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b\f\3\2\2\2"+
		"\u013c\u013d\5\13\6\2\u013d\u013e\7\60\2\2\u013e\u013f\5\13\6\2\u013f"+
		"\16\3\2\2\2\u0140\u0144\t\4\2\2\u0141\u0143\t\5\2\2\u0142\u0141\3\2\2"+
		"\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0148"+
		"\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0149\7a\2\2\u0148\u0147\3\2\2\2\u0148"+
		"\u0149\3\2\2\2\u0149\u014d\3\2\2\2\u014a\u014c\t\5\2\2\u014b\u014a\3\2"+
		"\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e"+
		"\20\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0154\7$\2\2\u0151\u0153\n\6\2\2"+
		"\u0152\u0151\3\2\2\2\u0153\u0156\3\2\2\2\u0154\u0152\3\2\2\2\u0154\u0155"+
		"\3\2\2\2\u0155\u0157\3\2\2\2\u0156\u0154\3\2\2\2\u0157\u0158\7$\2\2\u0158"+
		"\22\3\2\2\2\u0159\u015a\4*+\2\u015a\24\3\2\2\2\u015b\u015c\t\7\2\2\u015c"+
		"\26\3\2\2\2\u015d\u015e\7\60\2\2\u015e\u015f\7\60\2\2\u015f\30\3\2\2\2"+
		"\u0160\u0161\7\60\2\2\u0161\32\3\2\2\2\u0162\u0163\7<\2\2\u0163\34\3\2"+
		"\2\2\u0164\u0165\7.\2\2\u0165\36\3\2\2\2\u0166\u0167\7>\2\2\u0167\u0168"+
		"\7/\2\2\u0168 \3\2\2\2\u0169\u016a\7(\2\2\u016a\"\3\2\2\2\u016b\u016c"+
		"\7`\2\2\u016c$\3\2\2\2\u016d\u0171\7}\2\2\u016e\u0170\n\b\2\2\u016f\u016e"+
		"\3\2\2\2\u0170\u0173\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172"+
		"\u0174\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0175\7\177\2\2\u0175\u0176\3"+
		"\2\2\2\u0176\u0177\b\23\2\2\u0177&\3\2\2\2\u0178\u0179\t\t\2\2\u0179\u017a"+
		"\3\2\2\2\u017a\u017b\b\24\2\2\u017b(\3\2\2\2\u017c\u017d\7\177\2\2\u017d"+
		"*\3\2\2\2\u017e\u0182\7}\2\2\u017f\u0181\n\b\2\2\u0180\u017f\3\2\2\2\u0181"+
		"\u0184\3\2\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u018a\3\2"+
		"\2\2\u0184\u0182\3\2\2\2\u0185\u0187\7\17\2\2\u0186\u0185\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u018b\7\f\2\2\u0189\u018b\7\2"+
		"\2\3\u018a\u0186\3\2\2\2\u018a\u0189\3\2\2\2\u018b,\3\2\2\2\u018c\u0190"+
		"\7$\2\2\u018d\u018f\n\6\2\2\u018e\u018d\3\2\2\2\u018f\u0192\3\2\2\2\u0190"+
		"\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0198\3\2\2\2\u0192\u0190\3\2"+
		"\2\2\u0193\u0195\7\17\2\2\u0194\u0193\3\2\2\2\u0194\u0195\3\2\2\2\u0195"+
		"\u0196\3\2\2\2\u0196\u0199\7\f\2\2\u0197\u0199\7\2\2\3\u0198\u0194\3\2"+
		"\2\2\u0198\u0197\3\2\2\2\u0199.\3\2\2\2\u019a\u019b\13\2\2\2\u019b\60"+
		"\3\2\2\2\24\2\u0120\u012b\u0135\u013a\u0142\u0144\u0148\u014b\u014d\u0154"+
		"\u0171\u0182\u0186\u018a\u0190\u0194\u0198\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}