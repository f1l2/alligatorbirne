// Generated from Query.g4 by ANTLR 4.5.1

	package event.processing.query.language;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryParser}.
 */
public interface QueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(QueryParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(QueryParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#window}.
	 * @param ctx the parse tree
	 */
	void enterWindow(QueryParser.WindowContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#window}.
	 * @param ctx the parse tree
	 */
	void exitWindow(QueryParser.WindowContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#domain}.
	 * @param ctx the parse tree
	 */
	void enterDomain(QueryParser.DomainContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#domain}.
	 * @param ctx the parse tree
	 */
	void exitDomain(QueryParser.DomainContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#domainlist}.
	 * @param ctx the parse tree
	 */
	void enterDomainlist(QueryParser.DomainlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#domainlist}.
	 * @param ctx the parse tree
	 */
	void exitDomainlist(QueryParser.DomainlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#domainName}.
	 * @param ctx the parse tree
	 */
	void enterDomainName(QueryParser.DomainNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#domainName}.
	 * @param ctx the parse tree
	 */
	void exitDomainName(QueryParser.DomainNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(QueryParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(QueryParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterCompare(QueryParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitCompare(QueryParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#logicLink}.
	 * @param ctx the parse tree
	 */
	void enterLogicLink(QueryParser.LogicLinkContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#logicLink}.
	 * @param ctx the parse tree
	 */
	void exitLogicLink(QueryParser.LogicLinkContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#aggregateCompare}.
	 * @param ctx the parse tree
	 */
	void enterAggregateCompare(QueryParser.AggregateCompareContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#aggregateCompare}.
	 * @param ctx the parse tree
	 */
	void exitAggregateCompare(QueryParser.AggregateCompareContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(QueryParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(QueryParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void enterAggregate(QueryParser.AggregateContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void exitAggregate(QueryParser.AggregateContext ctx);
}