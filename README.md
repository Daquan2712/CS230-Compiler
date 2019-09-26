# CS230-Compiler
Install Javacc-5.0 before running files
# The Simple Grammar

  <program> → void main ( ) <block>
  <block> → { <declarations> <optional_statements>? }
  <declarations>  →  (<declaration>)*
  <declaration  →  <type> <identifier_list>;
  <identifier_list>  →  <id>  ( , <id> ) *
  <type>  →   int  |  bool
  <optional_statements>  → <statement_list>
  <statement_list>  → <statement> ( <statement> )*
  <statement>  → <variable> <assignop> <expression>
                                | <block>
                                | if (<expression>) <statement>  <else_clause>
                                | while (<expression>) <statement>  
  <variable> →<id>
  <expression> → <simple_expression>
  <simple_expression>  → <term> ( <addop> <term> ) *
  <else_clause>  →  ( else <statement> )?
  <expression>   →  <simple_expression> <relopclause>
  <relopclause>   →  <relop> <simple_expression> | ε
  <term> → <factor> (<mulop> <factor>) *
                     | <addop> <term>
  <factor>  → <id> |  (<expression>) |  <num>  |  ! <factor> 
  <id> → <letter> ( <letter>  | <digit> ) *
  <sign>  →  + | -
  <mulop> → *  |  /  | %
  <num>  →   <digit>   <digit>*
  <relop>  →   ==  |  !=   |  <  |  <=  |  >  |  >=
  <digit> →   [0 - 9]
  <letter> →    [a - z | A - Z]
  <assignop> → =
  <mulop>  →   *   |  /  | % |    &&
  <addop> →  + | -  |  ||

# The front-end is written with JavaCC to generate an abstract syntax tree
# The back-end is just slightly touched, however. More works needed for optimisation and improvement
