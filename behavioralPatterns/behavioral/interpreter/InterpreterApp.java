package behavioral.interpreter;

/**
 * Created by Nazar on 06.08.2017.
 */
public class InterpreterApp {
    public static void main(String[] args) {
        Context context = new Context();
        //"1-2+3"
        Expression expression = context.evaluate("1-2+3");
        System.out.println(expression.interpret());
        //"11-2+3"
        expression = context.evaluate("11-2+3");
        System.out.println(expression.interpret());
        //"54+19-100
        expression = context.evaluate("54+19-100");
        System.out.println(expression.interpret());
    }
}

interface Expression{
    int interpret();
}

class NumberExpression implements Expression{
    int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public int interpret(){
        return number;
    }
}

class MinusExpression implements Expression{
    Expression left;
    Expression right;

    public MinusExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() - right.interpret();
    }
}

class PlusExpression implements Expression{
    Expression left;
    Expression right;

    public PlusExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

class Context{
    Expression evaluate(String s){
        int pos = s.length()-1;
        while(pos>0){
            if(Character.isDigit(s.charAt(pos))){
                pos--;
            }
            else{
                Expression left = evaluate(s.substring(0, pos));
                Expression right = new NumberExpression(Integer.valueOf(s.substring(pos+1, s.length())));
                char operator = s.charAt(pos);
                switch (operator){
                    case '-': return new MinusExpression(left, right);
                    case '+': return new PlusExpression(left, right);
                }
            }
        }
        int result = Integer.valueOf(s);
        return new NumberExpression(result);
    }
}