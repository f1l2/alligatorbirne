grammar Query;

@header {
	package event.processing.query.language;
}

query: condition WS 'FROM' domainlist? window?;

window: WS 'WIN.TIME(' WS? Int WS? ')' | WS 'WIN.LENGTH(' WS? Int WS? ')';

domainlist: WS domain | WS domainlist WS? COMMA WS? domainlist;

domain: Name;

condition: 'CONDITION' WS comparelogic;

comparelogic: comparelogic WS 'AND' WS comparelogic | comparelogic WS 'AND' WS comparelogic | 'NOT' comparelogic | compare;

compare: aggregate WS? OPERATOR WS? aggregate;

aggregate: 'SUM' WS? '(' WS? property WS? ')' | 'AVG' WS? '(' WS? property WS? ')' | 'COUNT' WS? '(' WS? property WS? ')' | 'MAX' WS? '(' WS? property WS? ')' | 'MIN' WS? '(' WS? property WS? ')' | property; 

property: Name '.' Name | Int;

Name: ('A'..'Z' | 'a'..'z')+;
Int: ('0'..'9')+;

COMMA: (',')+;
WS: (' ' | '\t')+;
NL:  '\r'? '\n';
OPERATOR: ('=' | '<' | '>' | '<=' | '>=');