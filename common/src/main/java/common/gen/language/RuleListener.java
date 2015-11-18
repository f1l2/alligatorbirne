// Generated from Rule.g4 by ANTLR 4.5.1

	package common.gen.language;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RuleParser}.
 */
public interface RuleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RuleParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterStructure(RuleParser.StructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitStructure(RuleParser.StructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#querySequence}.
	 * @param ctx the parse tree
	 */
	void enterQuerySequence(RuleParser.QuerySequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#querySequence}.
	 * @param ctx the parse tree
	 */
	void exitQuerySequence(RuleParser.QuerySequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(RuleParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(RuleParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#reactions}.
	 * @param ctx the parse tree
	 */
	void enterReactions(RuleParser.ReactionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#reactions}.
	 * @param ctx the parse tree
	 */
	void exitReactions(RuleParser.ReactionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#reaction}.
	 * @param ctx the parse tree
	 */
	void enterReaction(RuleParser.ReactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#reaction}.
	 * @param ctx the parse tree
	 */
	void exitReaction(RuleParser.ReactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#devInfo}.
	 * @param ctx the parse tree
	 */
	void enterDevInfo(RuleParser.DevInfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#devInfo}.
	 * @param ctx the parse tree
	 */
	void exitDevInfo(RuleParser.DevInfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#devInfoName}.
	 * @param ctx the parse tree
	 */
	void enterDevInfoName(RuleParser.DevInfoNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#devInfoName}.
	 * @param ctx the parse tree
	 */
	void exitDevInfoName(RuleParser.DevInfoNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#domainInfo}.
	 * @param ctx the parse tree
	 */
	void enterDomainInfo(RuleParser.DomainInfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#domainInfo}.
	 * @param ctx the parse tree
	 */
	void exitDomainInfo(RuleParser.DomainInfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#domainInfoName}.
	 * @param ctx the parse tree
	 */
	void enterDomainInfoName(RuleParser.DomainInfoNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#domainInfoName}.
	 * @param ctx the parse tree
	 */
	void exitDomainInfoName(RuleParser.DomainInfoNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#cM}.
	 * @param ctx the parse tree
	 */
	void enterCM(RuleParser.CMContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#cM}.
	 * @param ctx the parse tree
	 */
	void exitCM(RuleParser.CMContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#cMProperty}.
	 * @param ctx the parse tree
	 */
	void enterCMProperty(RuleParser.CMPropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#cMProperty}.
	 * @param ctx the parse tree
	 */
	void exitCMProperty(RuleParser.CMPropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#cMValue}.
	 * @param ctx the parse tree
	 */
	void enterCMValue(RuleParser.CMValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#cMValue}.
	 * @param ctx the parse tree
	 */
	void exitCMValue(RuleParser.CMValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#cMKey}.
	 * @param ctx the parse tree
	 */
	void enterCMKey(RuleParser.CMKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#cMKey}.
	 * @param ctx the parse tree
	 */
	void exitCMKey(RuleParser.CMKeyContext ctx);
}