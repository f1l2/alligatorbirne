grammar Rule;

@header {
	package common.gen.language;
}

structure: querySequence WS ('TRIGGERS'|'triggers') WS reactions  window?;

window: WS windowType'(' WS? intValue WS? ')' | WS windowType'(' WS? intValue WS? ')';

querySequence: (querySingle WS? '->' WS? querySequence) | querySingle;

querySingle: negation WS query | query;

query: VARIABLE;

negation: 'NOT' | 'not';

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

windowType: ('WIN:TIME' | 'win:time' | 'WIN:LENGTH' | 'win:length');

intValue: INT;

VARIABLE: ('A'..'Z' | 'a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9' | '-' | '_' )+;

STRING: '\'' ('A'..'Z' | 'a'..'z' | '0'..'9')+ '\'';
INT: ('0'..'9')+;

COMMA: (',')+;
WS: (' ' | '\t')+;
NL:  '\r'? '\n';