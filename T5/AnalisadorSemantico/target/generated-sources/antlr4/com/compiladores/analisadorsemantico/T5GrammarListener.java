// Generated from com/compiladores/analisadorsemantico/T5Grammar.g4 by ANTLR 4.10.1
package com.compiladores.analisadorsemantico;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link T5GrammarParser}.
 */
public interface T5GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(T5GrammarParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(T5GrammarParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracoes(T5GrammarParser.DeclaracoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracoes(T5GrammarParser.DeclaracoesContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void enterDecl_local_global(T5GrammarParser.Decl_local_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void exitDecl_local_global(T5GrammarParser.Decl_local_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_local(T5GrammarParser.Declaracao_localContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_local(T5GrammarParser.Declaracao_localContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterVariavel(T5GrammarParser.VariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitVariavel(T5GrammarParser.VariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#identificador}.
	 * @param ctx the parse tree
	 */
	void enterIdentificador(T5GrammarParser.IdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#identificador}.
	 * @param ctx the parse tree
	 */
	void exitIdentificador(T5GrammarParser.IdentificadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void enterDimensao(T5GrammarParser.DimensaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void exitDimensao(T5GrammarParser.DimensaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(T5GrammarParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(T5GrammarParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico(T5GrammarParser.Tipo_basicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico(T5GrammarParser.Tipo_basicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico_ident(T5GrammarParser.Tipo_basico_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico_ident(T5GrammarParser.Tipo_basico_identContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void enterTipo_estendido(T5GrammarParser.Tipo_estendidoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void exitTipo_estendido(T5GrammarParser.Tipo_estendidoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void enterValor_constante(T5GrammarParser.Valor_constanteContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void exitValor_constante(T5GrammarParser.Valor_constanteContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#registro}.
	 * @param ctx the parse tree
	 */
	void enterRegistro(T5GrammarParser.RegistroContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#registro}.
	 * @param ctx the parse tree
	 */
	void exitRegistro(T5GrammarParser.RegistroContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_global(T5GrammarParser.Declaracao_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_global(T5GrammarParser.Declaracao_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(T5GrammarParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(T5GrammarParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(T5GrammarParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(T5GrammarParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#corpo}.
	 * @param ctx the parse tree
	 */
	void enterCorpo(T5GrammarParser.CorpoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#corpo}.
	 * @param ctx the parse tree
	 */
	void exitCorpo(T5GrammarParser.CorpoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(T5GrammarParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(T5GrammarParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeia(T5GrammarParser.CmdLeiaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeia(T5GrammarParser.CmdLeiaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscreva(T5GrammarParser.CmdEscrevaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscreva(T5GrammarParser.CmdEscrevaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void enterCmdSe(T5GrammarParser.CmdSeContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void exitCmdSe(T5GrammarParser.CmdSeContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void enterCmdCaso(T5GrammarParser.CmdCasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void exitCmdCaso(T5GrammarParser.CmdCasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void enterCmdPara(T5GrammarParser.CmdParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void exitCmdPara(T5GrammarParser.CmdParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(T5GrammarParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(T5GrammarParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void enterCmdFaca(T5GrammarParser.CmdFacaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void exitCmdFaca(T5GrammarParser.CmdFacaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void enterCmdAtribuicao(T5GrammarParser.CmdAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void exitCmdAtribuicao(T5GrammarParser.CmdAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void enterCmdChamada(T5GrammarParser.CmdChamadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void exitCmdChamada(T5GrammarParser.CmdChamadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void enterCmdRetorne(T5GrammarParser.CmdRetorneContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void exitCmdRetorne(T5GrammarParser.CmdRetorneContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#selecao}.
	 * @param ctx the parse tree
	 */
	void enterSelecao(T5GrammarParser.SelecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#selecao}.
	 * @param ctx the parse tree
	 */
	void exitSelecao(T5GrammarParser.SelecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void enterItem_selecao(T5GrammarParser.Item_selecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void exitItem_selecao(T5GrammarParser.Item_selecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#constantes}.
	 * @param ctx the parse tree
	 */
	void enterConstantes(T5GrammarParser.ConstantesContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#constantes}.
	 * @param ctx the parse tree
	 */
	void exitConstantes(T5GrammarParser.ConstantesContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void enterNumero_intervalo(T5GrammarParser.Numero_intervaloContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void exitNumero_intervalo(T5GrammarParser.Numero_intervaloContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void enterOp_unario(T5GrammarParser.Op_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void exitOp_unario(T5GrammarParser.Op_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExp_aritmetica(T5GrammarParser.Exp_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExp_aritmetica(T5GrammarParser.Exp_aritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(T5GrammarParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(T5GrammarParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(T5GrammarParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(T5GrammarParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#op1}.
	 * @param ctx the parse tree
	 */
	void enterOp1(T5GrammarParser.Op1Context ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#op1}.
	 * @param ctx the parse tree
	 */
	void exitOp1(T5GrammarParser.Op1Context ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#op2}.
	 * @param ctx the parse tree
	 */
	void enterOp2(T5GrammarParser.Op2Context ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#op2}.
	 * @param ctx the parse tree
	 */
	void exitOp2(T5GrammarParser.Op2Context ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#op3}.
	 * @param ctx the parse tree
	 */
	void enterOp3(T5GrammarParser.Op3Context ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#op3}.
	 * @param ctx the parse tree
	 */
	void exitOp3(T5GrammarParser.Op3Context ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#parcela}.
	 * @param ctx the parse tree
	 */
	void enterParcela(T5GrammarParser.ParcelaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#parcela}.
	 * @param ctx the parse tree
	 */
	void exitParcela(T5GrammarParser.ParcelaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_unario(T5GrammarParser.Parcela_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_unario(T5GrammarParser.Parcela_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_nao_unario(T5GrammarParser.Parcela_nao_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_nao_unario(T5GrammarParser.Parcela_nao_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void enterExp_relacional(T5GrammarParser.Exp_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void exitExp_relacional(T5GrammarParser.Exp_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void enterOp_relacional(T5GrammarParser.Op_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void exitOp_relacional(T5GrammarParser.Op_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressao(T5GrammarParser.ExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressao(T5GrammarParser.ExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void enterTermo_logico(T5GrammarParser.Termo_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void exitTermo_logico(T5GrammarParser.Termo_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void enterFator_logico(T5GrammarParser.Fator_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void exitFator_logico(T5GrammarParser.Fator_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void enterParcela_logica(T5GrammarParser.Parcela_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void exitParcela_logica(T5GrammarParser.Parcela_logicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_1(T5GrammarParser.Op_logico_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_1(T5GrammarParser.Op_logico_1Context ctx);
	/**
	 * Enter a parse tree produced by {@link T5GrammarParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_2(T5GrammarParser.Op_logico_2Context ctx);
	/**
	 * Exit a parse tree produced by {@link T5GrammarParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_2(T5GrammarParser.Op_logico_2Context ctx);
}