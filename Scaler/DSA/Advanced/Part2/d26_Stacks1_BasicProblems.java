package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author Harish Velmurugan
 * @last-modified 04-03-2025
 * @since 04-03-2025
 */
@SuppressWarnings({"EnhancedSwitchMigration", "UnusedReturnValue", "SpellCheckingInspection"})
public class d26_Stacks1_BasicProblems {


    private final PrintHelper printHelper = new PrintHelper();
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d26_Stacks1_BasicProblems d26_Stacks1_BasicProblems = new d26_Stacks1_BasicProblems();

        ArrayList<String> A = new ArrayList<>(Arrays.asList("2", "1", "+", "3", "*"));
        d26_Stacks1_BasicProblems.postFixExpression(A); // Q1 // LC150
        d26_Stacks1_BasicProblems.balancedParenthesis("{()()()}({"); // Q2 // LC20
        d26_Stacks1_BasicProblems.doubleCharacterTrouble("abccbc"); // Q3 // LC1047
        d26_Stacks1_BasicProblems.passingGameOfBall(10, 48, new int[]{4, 0, 30, 0, 41, 28, 50, 2, 47, 39}); // Q4 // LC1472 Similar leetcode


        d26_Stacks1_BasicProblems.minStackInit(); // AQ1 // LC155
        d26_Stacks1_BasicProblems.redundantBracesUnecessaryBraces("(a+(a+b))"); // AQ2
        d26_Stacks1_BasicProblems.checkTwoBracketExpressions("-(a+b+c)", "-a-b-c"); // AQ3
        d26_Stacks1_BasicProblems.infixToPostfix("a+b*(c^d-e)^(f+g*h)-i"); // AQ4

    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void bruteForce() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    public int postFixExpression(ArrayList<String> A) {
        /* QUESTION
        An arithmetic expression is given by a string array A of size N. Evaluate the value of an arithmetic expression
        in Reverse Polish Notation.
        Valid operators are +, -, *, /. Each string may be an integer or an operator.
        Reverse Polish Notation is equivalent to Postfix Expression, where operators are written after their operands.
        */

        /*
         * POSTFIX EXPRESSION EVALUATOR - Stack
         *
         * STRATEGY: number → push | operator → pop two, apply, push result
         *
         * EXAMPLE: ["2","1","+","3","*"]
         *   push 2 → [2]
         *   push 1 → [2,1]
         *   +      → pop b=1, a=2 → push 3     [3]
         *   push 3 → [3,3]
         *   *      → pop b=3, a=3 → push 9     [9]
         *   return 9
         *
         * ORDER: b=pop, a=pop → apply (a op b) not (b op a) (matters for - and /)
         *
         * Time: O(N)  Space: O(N)
         */


        Stack<Integer> stack = new Stack<>();

        for (String token : A) {
            if (isOperator(token)) {
                int b = stack.pop();
                int a = stack.pop();

                int result = applyOperation(a, b, token);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/';
    }

    private int applyOperation(int a, int b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public int balancedParenthesis(String A) {
        /* QUESTION :
        Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]”
        are correct in A.
        Refer to the examples for more clarity.
        */

        /*
         * BALANCED PARENTHESIS - Stack
         *
         * STRATEGY: open bracket → push | close bracket → pop and check match
         *           mismatch or empty stack on close → invalid
         *           stack not empty at end → invalid
         *
         * EXAMPLE: "{[()]}"
         *   push {  → [{]
         *   push [  → [{,[]
         *   push (  → [{,[,(]
         *   )  → pop ( → match ✓
         *   ]  → pop [ → match ✓
         *   }  → pop { → match ✓
         *   stack empty → return 1
         *
         * PAIRS: ( ) | [ ] | { }
         *
         * Time: O(N)  Space: O(N)
         */

        char[] array = A.toCharArray();
        int N = array.length;
        if ((N & 1) != 0) {
            return 0;
        }

        Stack<Character> stack = new Stack<>();
        for (char target : array) {
            if (isForwardParenthesis(target)) {
                stack.push(target);
            } else {
                if (!stack.isEmpty()) {
                    char topElement = stack.pop();
                    char pairRequired = pairingParenthesis(target);
                    if (topElement != pairRequired) {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }

    private boolean isForwardParenthesis(char x) {
        if (x == '{' || x == '[' || x == '(') {
            return true;
        }
        return false;
    }

    private char pairingParenthesis(char x) {
        switch (x) {
            case '}':
                return '{';
            case ']':
                return '[';
            default:
                return '(';
        }
    }

    public int passingGameOfBall(int A, int B, int[] C) {
        /* QUESTION :
        There is a football event going on in your city. In this event, you are given A passes and players having ids
        between 1 and 106.
        Initially, some player with a given id had the ball in his possession. You have to make a program to display
        the id of the player who possessed the ball after exactly A passes.
        There are two kinds of passes: 1) ID 2) 0
        For the first kind of pass, the player in possession of the ball passes the ball "forward" to the player with id = ID.
        For the second kind of pass, the player in possession of the ball passes the ball back to the player who had
        forwarded the ball to him.
        In the second kind of pass "0" just means Back Pass.
        Return the ID of the player who currently possesses the ball.
          */

        /*
         * FOOTBALL PASSING - Stack
         *
         * STRATEGY: forward pass → push to stack | back pass(0) → pop from stack
         *
         * EXAMPLE: start=1, passes=[2,3,0,4]
         *   push 2 → [1,2]   (1 passed to 2)
         *   push 3 → [1,2,3] (2 passed to 3)
         *   0      → pop 3   [1,2]  (3 passes back to 2)
         *   push 4 → [1,2,4] (2 passed to 4)
         *   return stack.peek() = 4
         *
         * INIT: push starting player id before processing passes
         * BACK PASS: stack.pop() removes current, peek() gives previous holder
         *
         * Time: O(N)  Space: O(N)
         */
        Stack<Integer> stack = new Stack<>();
        stack.push(B);
        int currentPlayer = B;

        for (int i = 0; i < A; i++) {
            if (C[i] == 0) {
                stack.pop();                  // current leaves
                currentPlayer = stack.peek(); // previous resumes
            } else {
                currentPlayer = C[i];
                stack.push(C[i]);            // new player takes over
            }
        }
        return currentPlayer;
    }

    public String doubleCharacterTrouble(String A) {
        /* QUESTION :
        You have a string, denoted as A.
        To transform the string, you should perform the following operation repeatedly:
        Identify the first occurrence of consecutive identical pairs of characters within the string.
                Remove this pair of identical characters from the string.
                Repeat steps 1 and 2 until there are no more consecutive identical pairs of characters.
        The final result will be the transformed string.
        */

        Stack<Character> stack = new Stack<>();
        char[] array = A.toCharArray();
        for (char currentElement : array) {
            if (stack.isEmpty()) {
                stack.push(currentElement);
            } else {
                int prevElement = stack.peek();
                if (prevElement == currentElement) {
                    stack.pop();
                } else {
                    stack.push(currentElement);
                }
            }
        }

        A = "";
        while (!stack.isEmpty()) {
            A = stack.pop() + A;
        }
        return A;
    }

    public int redundantBracesUnecessaryBraces(String A) {
        /* QUESTION
        Given a string A denoting an expression. It contains the following operators '+', '-', '*', '/'.
        Check whether A has redundant braces or not.
        A will be always a valid expression and will not contain any white spaces.
        */
        char[] charArray = A.toCharArray();
        Stack<Character> stack = new Stack<>();
        int N = charArray.length;
        for (char x : charArray) {
            if (x == ')') {
                boolean hasOp = false;
                while (stack.peek() != '(') {
                    hasOp = hasOp || isOperator(stack.peek());
                    stack.pop();
                }
                stack.pop();

                if (!hasOp) {
                    return 1;
                }
            } else {
                stack.push(x);
            }
        }
        return 0;
    }

    private int precedence(char op) {
        switch (op) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    public String infixToPostfix(String A) {
        /*
         * INFIX TO POSTFIX - Stack
         *
         * STRATEGY: operand → output | operator → pop >= precedence then push | brackets → scope control
         *
         * EXAMPLE: a+b*c-d
         *   a        → output             output=[a]        stack=[]
         *   +        → stack empty, push  output=[a]        stack=[+]
         *   b        → output             output=[ab]       stack=[+]
         *   *        → prec(+)<prec(*),   output=[ab]       stack=[+,*]
         *              push
         *   c        → output             output=[abc]      stack=[+,*]
         *   -        → prec(*)>=prec(-),  output=[abc*]     stack=[+]
         *              pop * then         output=[abc*+]    stack=[]
         *              prec(+)>=prec(-),
         *              pop + then push -  output=[abc*+]    stack=[-]
         *   d        → output             output=[abc*+d]   stack=[-]
         *   END      → pop all            output=[abc*+d-]  stack=[]
         *
         * OPERATOR RULE:
         *   pop stack while → top != '('
         *                   → top precedence >= current precedence
         *   then push current
         *
         * BRACKET RULE:
         *   '(' → push as wall, never pop past it
         *   ')' → pop until '(' found, discard '('
         *
         * PRECEDENCE:  ^ = 3  |  * / = 2  |  + - = 1
         * ASSOCIATIVE: all left-to-right → equal precedence always pops
         *
         * Time: O(N)  Space: O(N)
         */

        StringBuilder postfix = new StringBuilder();
        Stack<Character> stk = new Stack<>();

        for (char c : A.toCharArray()) {

            // 1. Operand → directly to output
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            }

            // 2. '(' → push as wall
            else if (c == '(') {
                stk.push(c);
            }

            // 3. ')' → pop until matching '('
            else if (c == ')') {
                while (!stk.isEmpty() && stk.peek() != '(') {
                    postfix.append(stk.pop());
                }
                stk.pop(); // discard '('
            }

            // 4. Operator → pop equal or higher precedence, then push
            else {
                while (!stk.isEmpty()
                        && stk.peek() != '('
                        && precedence(stk.peek()) >= precedence(c)) {
                    postfix.append(stk.pop());
                }
                stk.push(c);
            }
        }

        // 5. Pop all remaining operators
        while (!stk.isEmpty()) {
            postfix.append(stk.pop());
        }

        return postfix.toString();
    }

    public void minStackInit() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (!minStack.isEmpty()) {
            minStack.push(Math.min(val, minStack.peek()));
        } else {
            minStack.push(val);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            minStack.pop();
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();  // peek not pop
        }
        return -1;
    }

    public int getMin() {
        if (!stack.isEmpty()) {
            return minStack.peek();
        }
        return -1;
    }

    public int checkTwoBracketExpressions(String A, String B) {

        /*
         * CHECK TWO BRACKET EXPRESSIONS - Stack sign tracking
         *
         * GOAL: check if two expressions evaluate to same result
         *       by comparing net sign of each variable a-z
         *
         * STRATEGY: track effective sign of each variable considering nested brackets
         *   stack stores cumulative sign of current bracket context
         *   currentSign resets to +1 after each '('
         *
         * SIGN RULES:
         *   '(' → push stack.peek() * currentSign (inherit outer context)
         *   ')' → pop (exit bracket context)
         *   '+' → currentSign = +1
         *   '-' → currentSign = -1
         *   'a'-'z' → sign[c] = stack.peek() * currentSign
         *
         * EXAMPLE: "a-(b+c)" vs "a-b-c"
         *   a=+1, b=-1, c=-1  |  a=+1, b=-1, c=-1  → match → return 1
         *
         * COMPARE: sign arrays of both expressions, any mismatch → return 0
         *
         * Time: O(N)  Space: O(N)
         */
        int[] first = build(A);
        int[] second = build(B);

        for (int i = 0; i < 26; i++) {
            if (first[i] != second[i]) {
                return 0;
            }
        }
        return 1;
    }

    public int[] build(String X) {
        int[] sign = new int[26];
        Stack<Integer> stack = new Stack<>();
        int currentSign = 1;
        stack.push(1);

        for (char c : X.toCharArray()) {
            if (c == '+') {
                currentSign = 1;
            } else if (c == '-') {
                currentSign = -1;
            } else if (c == '(') {
                stack.push(stack.peek() * currentSign);
                currentSign = 1;
            } else if (c == ')') {
                stack.pop();
            } else {
                sign[c - 'a'] = stack.peek() * currentSign;
            }
        }
        return sign;
    }

    /* Section : ------------------------------- [ Leetcode ] ------------------------------- */


    public int minAddToMakeParanthesisValid(String s) {
        /* Question
        You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
        For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
        Return the minimum number of moves required to make s valid.
        */

        Stack<Character> stack = new Stack<>();

        for (char x : s.toCharArray()) {
            if (!stack.isEmpty()) {
                if (x == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                        continue;
                    }
                }
            }
            stack.push(x);
        }

        return stack.size();
    }


    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /**/
    }

    private void links() {
        /**/
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
