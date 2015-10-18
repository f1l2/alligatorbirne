grammar Rule;

@header {
	package event.processing.gen.language;
}

structure: query WS 'TRIGGERS' WS reactions;

query: VARIABLE;

reactions: (reaction  WS? ';' WS? reactions | reaction ';'?);

reaction: devInfo ',' domainInfo ',' cM;

devInfo:  WS? devInfoName  WS?;

devInfoName: VARIABLE;

domainInfo: WS? domainInfoName  WS?;

domainInfoName: VARIABLE;

cM: cMProperty;

cMProperty: WS? cMKey WS? '=' WS? cMValue WS?;

cMValue: INT;

cMKey:  VARIABLE;

VARIABLE: ('A'..'Z' | 'a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9' | '-' | '_' )+;

STRING: '\'' ('A'..'Z' | 'a'..'z' | '0'..'9')+ '\'';
INT: ('0'..'9')+;

COMMA: (',')+;
WS: (' ' | '\t')+;
NL:  '\r'? '\n';