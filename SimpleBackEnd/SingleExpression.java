public class SingleExpression extends AST {
  String op;
  AST exp;
  SingleExpression(String o, AST l) {
    op = o;
    exp = l;
  }
  public AST getExpr() {
    return exp;
  }
  public String getOp() {
    return op;
  }
}