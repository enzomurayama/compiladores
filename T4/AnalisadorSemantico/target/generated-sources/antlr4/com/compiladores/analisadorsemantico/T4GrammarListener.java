// Generated from com/compiladores/analisadorsemantico/T4Grammar.g4 by ANTLR 4.10.1
package com.compiladores.analisadorsemantico;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link T4GrammarParser}.
 */
public interface T4GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(T4GrammarParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(T4GrammarParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracoes(T4GrammarParser.DeclaracoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracoes(T4GrammarParser.DeclaracoesContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void enterDecl_local_global(T4GrammarParser.Decl_local_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void exitDecl_local_global(T4GrammarParser.Decl_local_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_local(T4GrammarParser.Declaracao_localContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_local(T4GrammarParser.Declaracao_localContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterVariavel(T4GrammarParser.VariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitVariavel(T4GrammarParser.VariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#identificador}.
	 * @param ctx the parse tree
	 */
	void enterIdentificador(T4GrammarParser.IdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#identificador}.
	 * @param ctx the parse tree
	 */
	void exitIdentificador(T4GrammarParser.IdentificadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void enterDimensao(T4GrammarParser.DimensaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void exitDimensao(T4GrammarParser.DimensaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(T4GrammarParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(T4GrammarParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico(T4GrammarParser.Tipo_basicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico(T4GrammarParser.Tipo_basicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico_ident(T4GrammarParser.Tipo_basico_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico_ident(T4GrammarParser.Tipo_basico_identContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void enterTipo_estendido(T4GrammarParser.Tipo_estendidoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void exitTipo_estendido(T4GrammarParser.Tipo_estendidoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void enterValor_constante(T4GrammarParser.Valor_constanteContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void exitValor_constante(T4GrammarParser.Valor_constanteContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#registro}.
	 * @param ctx the parse tree
	 */
	void enterRegistro(T4GrammarParser.RegistroContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#registro}.
	 * @param ctx the parse tree
	 */
	void exitRegistro(T4GrammarParser.RegistroContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_global(T4GrammarParser.Declaracao_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_global(T4GrammarParser.Declaracao_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(T4GrammarParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(T4GrammarParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(T4GrammarParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(T4GrammarParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#corpo}.
	 * @param ctx the parse tree
	 */
	void enterCorpo(T4GrammarParser.CorpoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#corpo}.
	 * @param ctx the parse tree
	 */
	void exitCorpo(T4GrammarParser.CorpoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(T4GrammarParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(T4GrammarParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeia(T4GrammarParser.CmdLeiaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeia(T4GrammarParser.CmdLeiaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscreva(T4GrammarParser.CmdEscrevaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscreva(T4GrammarParser.CmdEscrevaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void enterCmdSe(T4GrammarParser.CmdSeContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void exitCmdSe(T4GrammarParser.CmdSeContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void enterCmdCaso(T4GrammarParser.CmdCasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void exitCmdCaso(T4GrammarParser.CmdCasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void enterCmdPara(T4GrammarParser.CmdParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void exitCmdPara(T4GrammarParser.CmdParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(T4GrammarParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(T4GrammarParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void enterCmdFaca(T4GrammarParser.CmdFacaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void exitCmdFaca(T4GrammarParser.CmdFacaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void enterCmdAtribuicao(T4GrammarParser.CmdAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void exitCmdAtribuicao(T4GrammarParser.CmdAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void enterCmdChamada(T4GrammarParser.CmdChamadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void exitCmdChamada(T4GrammarParser.CmdChamadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void enterCmdRetorne(T4GrammarParser.CmdRetorneContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void exitCmdRetorne(T4GrammarParser.CmdRetorneContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#selecao}.
	 * @param ctx the parse tree
	 */
	void enterSelecao(T4GrammarParser.SelecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#selecao}.
	 * @param ctx the parse tree
	 */
	void exitSelecao(T4GrammarParser.SelecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void enterItem_selecao(T4GrammarParser.Item_selecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void exitItem_selecao(T4GrammarParser.Item_selecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#constantes}.
	 * @param ctx the parse tree
	 */
	void enterConstantes(T4GrammarParser.ConstantesContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#constantes}.
	 * @param ctx the parse tree
	 */
	void exitConstantes(T4GrammarParser.ConstantesContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void enterNumero_intervalo(T4GrammarParser.Numero_intervaloContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void exitNumero_intervalo(T4GrammarParser.Numero_intervaloContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void enterOp_unario(T4GrammarParser.Op_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void exitOp_unario(T4GrammarParser.Op_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExp_aritmetica(T4GrammarParser.Exp_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExp_aritmetica(T4GrammarParser.Exp_aritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(T4GrammarParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(T4GrammarParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(T4GrammarParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(T4GrammarParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#op1}.
	 * @param ctx the parse tree
	 */
	void enterOp1(T4GrammarParser.Op1Context ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#op1}.
	 * @param ctx the parse tree
	 */
	void exitOp1(T4GrammarParser.Op1Context ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#op2}.
	 * @param ctx the parse tree
	 */
	void enterOp2(T4GrammarParser.Op2Context ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#op2}.
	 * @param ctx the parse tree
	 */
	void exitOp2(T4GrammarParser.Op2Context ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#op3}.
	 * @param ctx the parse tree
	 */
	void enterOp3(T4GrammarParser.Op3Context ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#op3}.
	 * @param ctx the parse tree
	 */
	void exitOp3(T4GrammarParser.Op3Context ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#parcela}.
	 * @param ctx the parse tree
	 */
	void enterParcela(T4GrammarParser.ParcelaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#parcela}.
	 * @param ctx the parse tree
	 */
	void exitParcela(T4GrammarParser.ParcelaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_unario(T4GrammarParser.Parcela_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_unario(T4GrammarParser.Parcela_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_nao_unario(T4GrammarParser.Parcela_nao_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_nao_unario(T4GrammarParser.Parcela_nao_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void enterExp_relacional(T4GrammarParser.Exp_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void exitExp_relacional(T4GrammarParser.Exp_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void enterOp_relacional(T4GrammarParser.Op_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void exitOp_relacional(T4GrammarParser.Op_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressao(T4GrammarParser.ExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressao(T4GrammarParser.ExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void enterTermo_logico(T4GrammarParser.Termo_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void exitTermo_logico(T4GrammarParser.Termo_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void enterFator_logico(T4GrammarParser.Fator_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void exitFator_logico(T4GrammarParser.Fator_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void enterParcela_logica(T4GrammarParser.Parcela_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void exitParcela_logica(T4GrammarParser.Parcela_logicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_1(T4GrammarParser.Op_logico_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_1(T4GrammarParser.Op_logico_1Context ctx);
	/**
	 * Enter a parse tree produced by {@link T4GrammarParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_2(T4GrammarParser.Op_logico_2Context ctx);
	/**
	 * Exit a parse tree produced by {@link T4GrammarParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_2(T4GrammarParser.Op_logico_2Context ctx);
}