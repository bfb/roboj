grammar Roboj;

start: 'bot' ID 'do' finders 'end';
finders: 'take' selector 'as' ID;
selector: 'h1' | 'p' | 'img';
ID : [a-z]+;
WS : [ \t\r\n]+ -> skip;