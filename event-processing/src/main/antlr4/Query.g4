grammar Query;

@header {
	package event.processing.query.language;
}

query: condition WS domain? window?;

window: WS 'WIN.TIME(' WS? INT WS? ')' | WS 'WIN.LENGTH(' WS? INT WS? ')';

domain: ('FROM' WS domainlist);

domainlist: domainName | domainlist WS? COMMA WS? domainlist;

domainName: VARIABLE;

condition: 'CONDITION' WS compare | 'CONDITION' WS logicLink | 'CONDITION' WS aggregateCompare;

compare: property WS? OPERATOR WS? property;

logicLink: compare WS 'AND' WS compare | compare WS 'OR' WS compare | 'NOT' compare;

aggregateCompare: aggregate WS? OPERATOR WS? INT | INT WS? OPERATOR WS? aggregate;

property: (VARIABLE | STRING | INT);

aggregate: 'SUM' WS? '(' WS? VARIABLE WS? ')' | 'AVG' WS? '(' WS? VARIABLE WS? ')' | 'COUNT' WS? '(' WS? VARIABLE WS? ')' | 'MAX' WS? '(' WS? VARIABLE WS? ')' | 'MIN' WS? '(' WS? VARIABLE WS? ')'; 

VARIABLE: ('A'..'Z' | 'a'..'z' | '-' | '_' )+;
STRING: '\'' ('A'..'Z' | 'a'..'z')+ '\'';
INT: ('0'..'9')+;

COMMA: (',')+;
WS: (' ' | '\t')+;
NL:  '\r'? '\n';
OPERATOR: ('=' | '<' | '>' | '<=' | '>=');