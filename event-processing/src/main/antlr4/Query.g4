grammar Query;

@header {
	package event.processing.query.language;
}

query: condition WS 'FROM' WS domainlist;

domainlist: domain | domainlist WS? COMMA WS? domainlist;

domain: Name;

condition: 'SELECT' WS compare;
compare: property WS? OPERATOR WS? property;
property: Name '.' Name | Int;

Name: ('A'..'Z' | 'a'..'z')+;
Int: ('0'..'9')+;

COMMA: (',')+;
WS: (' ' | '\t')+;
NL:  '\r'? '\n';
OPERATOR: ('=' | '<' | '>' | '<=' | '>=');