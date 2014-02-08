/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar Calculator;

start
    : (assign | cal) EOF
    ;

assign
    : NAME EQ cal
    ;

EQ
    : '='
    ;

NAME
    : [a-zA-Z_]+
    ;

cal
    : minus_e (ADD minus_e)*
    ;

minus_e
    : mul_e (MINUS mul_e)*
    ;

mul_e
    : div_e (MUL div_e)*
    ;

div_e
    : unit (DIV unit)*
    ;

ADD
    : '+';

MINUS
    : '-';

MUL
    : '*';

DIV
    : '/';


unit
    : NUMBER
    | NAME
    | '(' cal ')'
    ;

NUMBER
    : [0-9]+ ('.' [0-9]+) ?
    ;

WS
    : (' ' | '\t' ) -> channel(HIDDEN)
    ;
