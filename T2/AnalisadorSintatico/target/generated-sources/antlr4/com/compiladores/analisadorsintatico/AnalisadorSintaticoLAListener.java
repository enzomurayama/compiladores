// Generated from java-escape by ANTLR 4.11.1
package com.compiladores.analisadorsintatico;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AnalisadorSintaticoLAParser}.
 */
public interface AnalisadorSintaticoLAListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(AnalisadorSintaticoLAParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(AnalisadorSintaticoLAParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracoes(AnalisadorSintaticoLAParser.DeclaracoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracoes(AnalisadorSintaticoLAParser.DeclaracoesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void enterDecl_local_global(AnalisadorSintaticoLAParser.Decl_local_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void exitDecl_local_global(AnalisadorSintaticoLAParser.Decl_local_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_local(AnalisadorSintaticoLAParser.Declaracao_localContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_local(AnalisadorSintaticoLAParser.Declaracao_localContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterVariavel(AnalisadorSintaticoLAParser.VariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitVariavel(AnalisadorSintaticoLAParser.VariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#identificador}.
	 * @param ctx the parse tree
	 */
	void enterIdentificador(AnalisadorSintaticoLAParser.IdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#identificador}.
	 * @param ctx the parse tree
	 */
	void exitIdentificador(AnalisadorSintaticoLAParser.IdentificadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void enterDimensao(AnalisadorSintaticoLAParser.DimensaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void exitDimensao(AnalisadorSintaticoLAParser.DimensaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(AnalisadorSintaticoLAParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(AnalisadorSintaticoLAParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico(AnalisadorSintaticoLAParser.Tipo_basicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico(AnalisadorSintaticoLAParser.Tipo_basicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico_ident(AnalisadorSintaticoLAParser.Tipo_basico_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico_ident(AnalisadorSintaticoLAParser.Tipo_basico_identContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void enterTipo_estendido(AnalisadorSintaticoLAParser.Tipo_estendidoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void exitTipo_estendido(AnalisadorSintaticoLAParser.Tipo_estendidoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void enterValor_constante(AnalisadorSintaticoLAParser.Valor_constanteContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void exitValor_constante(AnalisadorSintaticoLAParser.Valor_constanteContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#registro}.
	 * @param ctx the parse tree
	 */
	void enterRegistro(AnalisadorSintaticoLAParser.RegistroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#registro}.
	 * @param ctx the parse tree
	 */
	void exitRegistro(AnalisadorSintaticoLAParser.RegistroContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_global(AnalisadorSintaticoLAParser.Declaracao_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_global(AnalisadorSintaticoLAParser.Declaracao_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(AnalisadorSintaticoLAParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(AnalisadorSintaticoLAParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(AnalisadorSintaticoLAParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(AnalisadorSintaticoLAParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#corpo}.
	 * @param ctx the parse tree
	 */
	void enterCorpo(AnalisadorSintaticoLAParser.CorpoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#corpo}.
	 * @param ctx the parse tree
	 */
	void exitCorpo(AnalisadorSintaticoLAParser.CorpoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(AnalisadorSintaticoLAParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(AnalisadorSintaticoLAParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeia(AnalisadorSintaticoLAParser.CmdLeiaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeia(AnalisadorSintaticoLAParser.CmdLeiaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscreva(AnalisadorSintaticoLAParser.CmdEscrevaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscreva(AnalisadorSintaticoLAParser.CmdEscrevaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void enterCmdSe(AnalisadorSintaticoLAParser.CmdSeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void exitCmdSe(AnalisadorSintaticoLAParser.CmdSeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void enterCmdCaso(AnalisadorSintaticoLAParser.CmdCasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void exitCmdCaso(AnalisadorSintaticoLAParser.CmdCasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void enterCmdPara(AnalisadorSintaticoLAParser.CmdParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void exitCmdPara(AnalisadorSintaticoLAParser.CmdParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(AnalisadorSintaticoLAParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(AnalisadorSintaticoLAParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void enterCmdFaca(AnalisadorSintaticoLAParser.CmdFacaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void exitCmdFaca(AnalisadorSintaticoLAParser.CmdFacaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void enterCmdAtribuicao(AnalisadorSintaticoLAParser.CmdAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void exitCmdAtribuicao(AnalisadorSintaticoLAParser.CmdAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void enterCmdChamada(AnalisadorSintaticoLAParser.CmdChamadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void exitCmdChamada(AnalisadorSintaticoLAParser.CmdChamadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void enterCmdRetorne(AnalisadorSintaticoLAParser.CmdRetorneContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void exitCmdRetorne(AnalisadorSintaticoLAParser.CmdRetorneContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#selecao}.
	 * @param ctx the parse tree
	 */
	void enterSelecao(AnalisadorSintaticoLAParser.SelecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#selecao}.
	 * @param ctx the parse tree
	 */
	void exitSelecao(AnalisadorSintaticoLAParser.SelecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void enterItem_selecao(AnalisadorSintaticoLAParser.Item_selecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void exitItem_selecao(AnalisadorSintaticoLAParser.Item_selecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#constantes}.
	 * @param ctx the parse tree
	 */
	void enterConstantes(AnalisadorSintaticoLAParser.ConstantesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#constantes}.
	 * @param ctx the parse tree
	 */
	void exitConstantes(AnalisadorSintaticoLAParser.ConstantesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void enterNumero_intervalo(AnalisadorSintaticoLAParser.Numero_intervaloContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void exitNumero_intervalo(AnalisadorSintaticoLAParser.Numero_intervaloContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void enterOp_unario(AnalisadorSintaticoLAParser.Op_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void exitOp_unario(AnalisadorSintaticoLAParser.Op_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExp_aritmetica(AnalisadorSintaticoLAParser.Exp_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExp_aritmetica(AnalisadorSintaticoLAParser.Exp_aritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(AnalisadorSintaticoLAParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(AnalisadorSintaticoLAParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(AnalisadorSintaticoLAParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(AnalisadorSintaticoLAParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#op1}.
	 * @param ctx the parse tree
	 */
	void enterOp1(AnalisadorSintaticoLAParser.Op1Context ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#op1}.
	 * @param ctx the parse tree
	 */
	void exitOp1(AnalisadorSintaticoLAParser.Op1Context ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#op2}.
	 * @param ctx the parse tree
	 */
	void enterOp2(AnalisadorSintaticoLAParser.Op2Context ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#op2}.
	 * @param ctx the parse tree
	 */
	void exitOp2(AnalisadorSintaticoLAParser.Op2Context ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#op3}.
	 * @param ctx the parse tree
	 */
	void enterOp3(AnalisadorSintaticoLAParser.Op3Context ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#op3}.
	 * @param ctx the parse tree
	 */
	void exitOp3(AnalisadorSintaticoLAParser.Op3Context ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#parcela}.
	 * @param ctx the parse tree
	 */
	void enterParcela(AnalisadorSintaticoLAParser.ParcelaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#parcela}.
	 * @param ctx the parse tree
	 */
	void exitParcela(AnalisadorSintaticoLAParser.ParcelaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_unario(AnalisadorSintaticoLAParser.Parcela_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_unario(AnalisadorSintaticoLAParser.Parcela_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_nao_unario(AnalisadorSintaticoLAParser.Parcela_nao_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_nao_unario(AnalisadorSintaticoLAParser.Parcela_nao_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void enterExp_relacional(AnalisadorSintaticoLAParser.Exp_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void exitExp_relacional(AnalisadorSintaticoLAParser.Exp_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void enterOp_relacional(AnalisadorSintaticoLAParser.Op_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void exitOp_relacional(AnalisadorSintaticoLAParser.Op_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressao(AnalisadorSintaticoLAParser.ExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressao(AnalisadorSintaticoLAParser.ExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void enterTermo_logico(AnalisadorSintaticoLAParser.Termo_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void exitTermo_logico(AnalisadorSintaticoLAParser.Termo_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void enterFator_logico(AnalisadorSintaticoLAParser.Fator_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void exitFator_logico(AnalisadorSintaticoLAParser.Fator_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void enterParcela_logica(AnalisadorSintaticoLAParser.Parcela_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void exitParcela_logica(AnalisadorSintaticoLAParser.Parcela_logicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_1(AnalisadorSintaticoLAParser.Op_logico_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_1(AnalisadorSintaticoLAParser.Op_logico_1Context ctx);
	/**
	 * Enter a parse tree produced by {@link AnalisadorSintaticoLAParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_2(AnalisadorSintaticoLAParser.Op_logico_2Context ctx);
	/**
	 * Exit a parse tree produced by {@link AnalisadorSintaticoLAParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_2(AnalisadorSintaticoLAParser.Op_logico_2Context ctx);
}