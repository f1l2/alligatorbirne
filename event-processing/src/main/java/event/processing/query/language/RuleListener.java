// Generated from Rule.g4 by ANTLR 4.5.1

	package event.processing.query.language;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RuleParser}.
 */
public interface RuleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RuleParser#rule}.
	 * @param ctx the parse tree
	 */
	void enterRule(RuleParser.RuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#rule}.
	 * @param ctx the parse tree
	 */
	void exitRule(RuleParser.RuleContext ctx);
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
	 * Enter a parse tree produced by {@link RuleParser#deviceInformation}.
	 * @param ctx the parse tree
	 */
	void enterDeviceInformation(RuleParser.DeviceInformationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#deviceInformation}.
	 * @param ctx the parse tree
	 */
	void exitDeviceInformation(RuleParser.DeviceInformationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#deviceInformationName}.
	 * @param ctx the parse tree
	 */
	void enterDeviceInformationName(RuleParser.DeviceInformationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#deviceInformationName}.
	 * @param ctx the parse tree
	 */
	void exitDeviceInformationName(RuleParser.DeviceInformationNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#domainInformation}.
	 * @param ctx the parse tree
	 */
	void enterDomainInformation(RuleParser.DomainInformationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#domainInformation}.
	 * @param ctx the parse tree
	 */
	void exitDomainInformation(RuleParser.DomainInformationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#domainInformationName}.
	 * @param ctx the parse tree
	 */
	void enterDomainInformationName(RuleParser.DomainInformationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#domainInformationName}.
	 * @param ctx the parse tree
	 */
	void exitDomainInformationName(RuleParser.DomainInformationNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#configurationModification}.
	 * @param ctx the parse tree
	 */
	void enterConfigurationModification(RuleParser.ConfigurationModificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#configurationModification}.
	 * @param ctx the parse tree
	 */
	void exitConfigurationModification(RuleParser.ConfigurationModificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleParser#configurationModificationName}.
	 * @param ctx the parse tree
	 */
	void enterConfigurationModificationName(RuleParser.ConfigurationModificationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#configurationModificationName}.
	 * @param ctx the parse tree
	 */
	void exitConfigurationModificationName(RuleParser.ConfigurationModificationNameContext ctx);
}