grammar Roboj;

t: 'take' element 'as' ID;
element: 'h1' | 'p' | 'img';
ID : [a-z]+;
WS : [ \t\r\n]+ -> skip;