grammar Rule;

@header {
	package event.processing.query.language;
}

rule: query WS 'TRIGGERS' WS reactions;

query: VARIABLE;

reactions: (reaction | reaction  WS? ';' WS? reactions);

reaction: deviceInformation ',' domainInformation ',' configurationModification;

deviceInformation:  WS ?('deviceInformation' WS? '=' WS?)? deviceInformationName WS?;

deviceInformationName: VARIABLE;

domainInformation: WS? ('domainInformation' WS? '=' WS?)? domainInformationName WS?;

domainInformationName: VARIABLE;

configurationModification: WS? ('configurationModification' WS? '=' WS?)? configurationModificationName WS?;

configurationModificationName: VARIABLE;

VARIABLE: ('A'..'Z' | 'a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9' | '-' | '_' )+;

STRING: '\'' ('A'..'Z' | 'a'..'z' | '0'..'9')+ '\'';
INT: ('0'..'9')+;

COMMA: (',')+;
WS: (' ' | '\t')+;
NL:  '\r'? '\n';