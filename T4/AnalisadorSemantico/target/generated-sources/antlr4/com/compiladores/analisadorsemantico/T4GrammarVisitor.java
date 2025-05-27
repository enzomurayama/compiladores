// Generated from com/compiladores/analisadorsemantico/T4Grammar.g4 by ANTLR 4.10.1
package com.compiladores.analisadorsemantico;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link T4GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface T4GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(T4GrammarParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#declaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes(T4GrammarParser.DeclaracoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#decl_local_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_local_global(T4GrammarParser.Decl_local_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_local(T4GrammarParser.Declaracao_localContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariavel(T4GrammarParser.VariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#identificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificador(T4GrammarParser.IdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#dimensao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensao(T4GrammarParser.DimensaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(T4GrammarParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#tipo_basico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico(T4GrammarParser.Tipo_basicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico_ident(T4GrammarParser.Tipo_basico_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#tipo_estendido}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_estendido(T4GrammarParser.Tipo_estendidoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#valor_constante}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_constante(T4GrammarParser.Valor_constanteContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#registro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistro(T4GrammarParser.RegistroContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#declaracao_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_global(T4GrammarParser.Declaracao_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(T4GrammarParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(T4GrammarParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#corpo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCorpo(T4GrammarParser.CorpoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(T4GrammarParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdLeia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdLeia(T4GrammarParser.CmdLeiaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdEscreva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEscreva(T4GrammarParser.CmdEscrevaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdSe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdSe(T4GrammarParser.CmdSeContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdCaso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdCaso(T4GrammarParser.CmdCasoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPara(T4GrammarParser.CmdParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEnquanto(T4GrammarParser.CmdEnquantoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdFaca}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdFaca(T4GrammarParser.CmdFacaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdAtribuicao(T4GrammarParser.CmdAtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdChamada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdChamada(T4GrammarParser.CmdChamadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#cmdRetorne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRetorne(T4GrammarParser.CmdRetorneContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelecao(T4GrammarParser.SelecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#item_selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem_selecao(T4GrammarParser.Item_selecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantes(T4GrammarParser.ConstantesContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#numero_intervalo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumero_intervalo(T4GrammarParser.Numero_intervaloContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#op_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_unario(T4GrammarParser.Op_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_aritmetica(T4GrammarParser.Exp_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(T4GrammarParser.TermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#fator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator(T4GrammarParser.FatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#op1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp1(T4GrammarParser.Op1Context ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#op2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp2(T4GrammarParser.Op2Context ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#op3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp3(T4GrammarParser.Op3Context ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#parcela}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela(T4GrammarParser.ParcelaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unario(T4GrammarParser.Parcela_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_nao_unario(T4GrammarParser.Parcela_nao_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#exp_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_relacional(T4GrammarParser.Exp_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#op_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_relacional(T4GrammarParser.Op_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(T4GrammarParser.ExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#termo_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo_logico(T4GrammarParser.Termo_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#fator_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator_logico(T4GrammarParser.Fator_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#parcela_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_logica(T4GrammarParser.Parcela_logicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#op_logico_1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_1(T4GrammarParser.Op_logico_1Context ctx);
	/**
	 * Visit a parse tree produced by {@link T4GrammarParser#op_logico_2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_2(T4GrammarParser.Op_logico_2Context ctx);
}