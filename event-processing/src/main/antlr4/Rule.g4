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

cM: WS? cMName WS? ;

cMName:  VARIABLE;

VARIABLE: ('A'..'Z' | 'a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9' | '-' | '_' )+;

STRING: '\'' ('A'..'Z' | 'a'..'z' | '0'..'9')+ '\'';
INT: ('0'..'9')+;

COMMA: (',')+;
WS: (' ' | '\t')+;
NL:  '\r'? '\n';