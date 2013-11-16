grammar Roboj;

start: 'bot' ID 'do' body 'end';
body: root finders | finders;
root: 'root' selectors;

finders: finder | finder finders;
finder: take selectors property as id
		| take selectors property as id process
		| take selectors as id
		| take selectors as id process;

process : 'process' code 'end';
code : method '(' params ')';
method: 'replace' | 'prefix' | 'sufix';
params: param ',' params | param;
param: STRING;

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
STRING : '"' ~'\n'*? '"';
WS : [ \t\r\n]+ -> skip;
