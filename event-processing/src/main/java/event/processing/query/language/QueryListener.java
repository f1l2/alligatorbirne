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
	 * Enter a parse tree produced by {@link QueryParser#domains}.
	 * @param ctx the parse tree
	 */
	void enterDomains(QueryParser.DomainsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#domains}.
	 * @param ctx the parse tree
	 */
	void exitDomains(QueryParser.DomainsContext ctx);
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
	 * Enter a parse tree produced by {@link QueryParser#conditions}.
	 * @param ctx the parse tree
	 */
	void enterConditions(QueryParser.ConditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#conditions}.
	 * @param ctx the parse tree
	 */
	void exitConditions(QueryParser.ConditionsContext ctx);
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
	 * Enter a parse tree produced by {@link QueryParser#singleCondition}.
	 * @param ctx the parse tree
	 */
	void enterSingleCondition(QueryParser.SingleConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#singleCondition}.
	 * @param ctx the parse tree
	 */
	void exitSingleCondition(QueryParser.SingleConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#compositeCondition}.
	 * @param ctx the parse tree
	 */
	void enterCompositeCondition(QueryParser.CompositeConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#compositeCondition}.
	 * @param ctx the parse tree
	 */
	void exitCompositeCondition(QueryParser.CompositeConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#compositeOperationSingleDigit}.
	 * @param ctx the parse tree
	 */
	void enterCompositeOperationSingleDigit(QueryParser.CompositeOperationSingleDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#compositeOperationSingleDigit}.
	 * @param ctx the parse tree
	 */
	void exitCompositeOperationSingleDigit(QueryParser.CompositeOperationSingleDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#compositeFunctionSingleDigit}.
	 * @param ctx the parse tree
	 */
	void enterCompositeFunctionSingleDigit(QueryParser.CompositeFunctionSingleDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#compositeFunctionSingleDigit}.
	 * @param ctx the parse tree
	 */
	void exitCompositeFunctionSingleDigit(QueryParser.CompositeFunctionSingleDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#compositeOperationDoubleDigit}.
	 * @param ctx the parse tree
	 */
	void enterCompositeOperationDoubleDigit(QueryParser.CompositeOperationDoubleDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#compositeOperationDoubleDigit}.
	 * @param ctx the parse tree
	 */
	void exitCompositeOperationDoubleDigit(QueryParser.CompositeOperationDoubleDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#compositeFunctionDoubleDigit}.
	 * @param ctx the parse tree
	 */
	void enterCompositeFunctionDoubleDigit(QueryParser.CompositeFunctionDoubleDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#compositeFunctionDoubleDigit}.
	 * @param ctx the parse tree
	 */
	void exitCompositeFunctionDoubleDigit(QueryParser.CompositeFunctionDoubleDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#aggregateCondition}.
	 * @param ctx the parse tree
	 */
	void enterAggregateCondition(QueryParser.AggregateConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#aggregateCondition}.
	 * @param ctx the parse tree
	 */
	void exitAggregateCondition(QueryParser.AggregateConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#aggregateFunction}.
	 * @param ctx the parse tree
	 */
	void enterAggregateFunction(QueryParser.AggregateFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#aggregateFunction}.
	 * @param ctx the parse tree
	 */
	void exitAggregateFunction(QueryParser.AggregateFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterEvaluation(QueryParser.EvaluationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitEvaluation(QueryParser.EvaluationContext ctx);
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
	 * Enter a parse tree produced by {@link QueryParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(QueryParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(QueryParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#intValue}.
	 * @param ctx the parse tree
	 */
	void enterIntValue(QueryParser.IntValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#intValue}.
	 * @param ctx the parse tree
	 */
	void exitIntValue(QueryParser.IntValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(QueryParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(QueryParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#windowType}.
	 * @param ctx the parse tree
	 */
	void enterWindowType(QueryParser.WindowTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#windowType}.
	 * @param ctx the parse tree
	 */
	void exitWindowType(QueryParser.WindowTypeContext ctx);
}