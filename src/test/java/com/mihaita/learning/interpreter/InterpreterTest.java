package com.mihaita.learning.interpreter;

import org.junit.jupiter.api.Test;

import java.util.StringTokenizer;

public class InterpreterTest {


    /**
     * Define:
     * AbstractExpression,
     * Interpreter,
     * TerminalExpression
     * NonTerminalExpression (Compound expressions)
     * Context
     */
    @Test
    public void test() {

        String context = "foo, bar";
        Expression expression1 = new TerminalExpression("bar");
        Expression expression2 = new TerminalExpression("foo");

        Expression tree = new OrExpression(expression1, expression2);

        System.out.println(tree.interpret(context));

    }

    static class OrExpression implements Expression {
        private final Expression first;
        private final Expression second;

        public OrExpression(Expression first, Expression second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean interpret(String value) {
            return first.interpret(value) || second.interpret(value);
        }
    }
    static class TerminalExpression implements Expression {
        private final String data;

        TerminalExpression(String data) {
            this.data = data;
        }

        @Override
        public boolean interpret(String value) {
            StringTokenizer tokenizer = new StringTokenizer(value);

            while(tokenizer.hasMoreElements()) {
                if (data.equals(tokenizer.nextToken()))
                    return true;
            }
            return false;
        }
    }
    interface Expression {
        boolean interpret(String value);
    }

}
