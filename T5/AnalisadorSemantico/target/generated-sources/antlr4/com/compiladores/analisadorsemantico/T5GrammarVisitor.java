// Generated from com/compiladores/analisadorsemantico/T5Grammar.g4 by ANTLR 4.10.1
package com.compiladores.analisadorsemantico;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link T5GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface T5GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(T5GrammarParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#declaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes(T5GrammarParser.DeclaracoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#decl_local_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_local_global(T5GrammarParser.Decl_local_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_local(T5GrammarParser.Declaracao_localContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariavel(T5GrammarParser.VariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#identificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificador(T5GrammarParser.IdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#dimensao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensao(T5GrammarParser.DimensaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(T5GrammarParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#tipo_basico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico(T5GrammarParser.Tipo_basicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico_ident(T5GrammarParser.Tipo_basico_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#tipo_estendido}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_estendido(T5GrammarParser.Tipo_estendidoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#valor_constante}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_constante(T5GrammarParser.Valor_constanteContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#registro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistro(T5GrammarParser.RegistroContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#declaracao_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_global(T5GrammarParser.Declaracao_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(T5GrammarParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(T5GrammarParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#corpo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCorpo(T5GrammarParser.CorpoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(T5GrammarParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdLeia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdLeia(T5GrammarParser.CmdLeiaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdEscreva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEscreva(T5GrammarParser.CmdEscrevaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdSe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdSe(T5GrammarParser.CmdSeContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdCaso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdCaso(T5GrammarParser.CmdCasoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPara(T5GrammarParser.CmdParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEnquanto(T5GrammarParser.CmdEnquantoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdFaca}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdFaca(T5GrammarParser.CmdFacaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdAtribuicao(T5GrammarParser.CmdAtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdChamada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdChamada(T5GrammarParser.CmdChamadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#cmdRetorne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRetorne(T5GrammarParser.CmdRetorneContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelecao(T5GrammarParser.SelecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#item_selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem_selecao(T5GrammarParser.Item_selecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantes(T5GrammarParser.ConstantesContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#numero_intervalo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumero_intervalo(T5GrammarParser.Numero_intervaloContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#op_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_unario(T5GrammarParser.Op_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_aritmetica(T5GrammarParser.Exp_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(T5GrammarParser.TermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#fator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator(T5GrammarParser.FatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#op1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp1(T5GrammarParser.Op1Context ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#op2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp2(T5GrammarParser.Op2Context ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#op3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp3(T5GrammarParser.Op3Context ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#parcela}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela(T5GrammarParser.ParcelaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unario(T5GrammarParser.Parcela_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_nao_unario(T5GrammarParser.Parcela_nao_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#exp_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_relacional(T5GrammarParser.Exp_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#op_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_relacional(T5GrammarParser.Op_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(T5GrammarParser.ExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#termo_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo_logico(T5GrammarParser.Termo_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#fator_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator_logico(T5GrammarParser.Fator_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#parcela_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_logica(T5GrammarParser.Parcela_logicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#op_logico_1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_1(T5GrammarParser.Op_logico_1Context ctx);
	/**
	 * Visit a parse tree produced by {@link T5GrammarParser#op_logico_2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_2(T5GrammarParser.Op_logico_2Context ctx);
}