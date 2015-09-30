grammar Query;

@header {
	package event.processing.query.language;
}

query: conditions domains? window?;

window: WS 'WIN.TIME(' WS? INT WS? ')' | WS 'WIN.LENGTH(' WS? INT WS? ')';

domains: (WS 'FROM' WS domain);

domain: domainName | domain WS? COMMA WS? domain;

domainName: VARIABLE;

conditions: 'CONDITION' WS condition;

condition: singleCondition | compositeCondition;

singleCondition: evaluation | aggregateCondition;

compositeCondition: compositeOperationSingleDigit | compositeOperationDoubleDigit | singleCondition;

compositeOperationSingleDigit: compositeFunctionSingleDigit WS compositeCondition;

compositeFunctionSingleDigit: 'NOT'; 

compositeOperationDoubleDigit: singleCondition WS compositeFunctionDoubleDigit WS compositeCondition;

compositeFunctionDoubleDigit: ('AND' | 'OR'); 

aggregateCondition: aggregateOperation WS? operator WS? INT | INT WS? operator WS? aggregateOperation;

aggregateOperation: aggregateFunction WS? '(' WS? VARIABLE WS? ')'; 

aggregateFunction: ('SUM' | 'AVG' | 'COUNT' | 'MAX' | 'MIN');

evaluation: property WS? operator WS? property;

property: (VARIABLE | STRING | INT);

operator: ('=' | '<' | '>' | '<=' | '>=');

VARIABLE: ('A'..'Z' | 'a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9' | '-' | '_' )+;

STRING: '\'' ('A'..'Z' | 'a'..'z' | '0'..'9')+ '\'';
INT: ('0'..'9')+;

COMMA: (',')+;
WS: (' ' | '\t')+;
NL:  '\r'? '\n';