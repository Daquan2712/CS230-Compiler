
import java.util.*;
//This is the class that represents the expression which has a String operation and a left and a right child
public class BinaryExpression {
  String op;
  BinaryExpression left, right;
  BinaryExpression(String o, BinaryExpression l, BinaryExpression r) {
    op = o;
    left = l;
    right = r;
  }
  public String toString() {
    if (left == null && right == null) {
      return op;
    } else if (left == null && right != null) {
      return "(" + op + " " + right + ")";
    } else if (right == null && left != null) {
      return "(" + op + " " + left + ")";
    } else if (right == null && left == null) {
      return "";
    } else {
      return "(" + op + " " + left + " " + right + ")";
    }
  }
  public String getOp() {
    return op;
  }
  public BinaryExpression getExpr() {
    return left;
  }
  public BinaryExpression getLeft() {
    return left;
  }
  public BinaryExpression getRight() {
    return right;
  }
}