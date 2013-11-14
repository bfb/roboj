grammar Roboj;

start: 'bot' ID 'do' '\n' finders 'end';

finders: finder | finder finders;
finder: take selectors property as id '\n'
		| take selectors property as id '\n' process;

process : 'process' code 'end' '\n';
code : 'self.substring(' NUMBER ',' NUMBER ')';

selectors : selector | selector '=>' selectors;
selector : element'('tag')['NUMBER']' | element'('tag')' | element'['NUMBER']' | element;

take : 'take';
as : 'as';
id : ID;
element : ID;
property : ':' ID;

tag: '.' ID | '#' ID;

ID : [a-zA-Z]+([A-Za-z0-9] | '-')*;
NUMBER : [0-9]+;
WS : [ \t\r]+ -> skip;