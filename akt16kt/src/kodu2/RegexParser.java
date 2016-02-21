package kodu2;

import java.text.ParseException;

/**
 * Regulaaravaldise parsimine. Selle kohta rÃƒÂ¤ÃƒÂ¤gime veel tulevikus...
 *
 * @author Vesal Vojdani &lt;vesal@cs.ut.ee&gt;
 */
public class RegexParser {
    final public static char EPS = 'ε';
    final private String input;
    private int pos;

    public static Regex parse(String regex) {
        RegexParser parser = new RegexParser(regex);
        return parser.parse();
    }

    private RegexParser(String input) {
        this.input = input;
    }

    private  Regex parse() {
        pos=0;
        try {
            return start();
        } catch (ParseException e) {
            System.out.println("Parse error: " + e.getMessage());
            System.out.println(input + '$');
            for (int i=0; i < e.getErrorOffset(); i++)
                System.out.print(' ');
            System.out.println('^');
            e.printStackTrace();
            return null;
        }
    }

    private void match(char x) throws ParseException {
        if (pos < input.length()) {
            char y = input.charAt(pos);
            if (y == x) {
                pos++;
            } else
                throw new ParseException("Unexpected char " + y, pos);
        } else
            throw new ParseException("Unexpected end of file.", pos);
    }

    private Regex anychar() throws ParseException {
        if (pos < input.length()) {
            char y = input.charAt(pos);
            if (Character.isLetter(y) || Character.isDigit(y)) {
                pos++;
                return Regex.letter(y);
            } else
                throw new ParseException("Unexpected symbol " + y, pos);
        } else
            throw new ParseException("Unexpected end of file.", pos);
    }


    private void done() throws ParseException {
        if (pos < input.length()) {
            char y = input.charAt(pos);
            throw new ParseException("Unexpected char " + y, pos);
        }
    }

    private Regex start() throws ParseException {
        Regex root = regexp();
        done();
        return root;
    }

    private char peek() {
        if (pos < input.length())
            return input.charAt(pos);
        return '$';
    }

    public static void main(String[] args) {
        RegexParser parser = new RegexParser(args[0]);
        System.out.println("Parsing: " + parser.input);
        Regex root = parser.parse();
        //root.createDotFile("tree.dot");
    }


    // RULES OF THE GRAMMAR

    // S -> Concat '|' S
    private Regex regexp() throws ParseException {
        Regex fst = concat();
        if (peek() == '|') {
            match('|');
            Regex snd = regexp();
            return Regex.alternation(fst, snd);
        } else
            return fst;
    }

    // Concat -> Repeat Concat
    private Regex concat() throws ParseException {
        Regex fst = repeat();
        if (peek() != '|' && peek() != '$' && peek() != ')') {
            Regex snd = concat();
            return Regex.concatenation(fst, snd);
        } else
            return fst;
    }

    // Rep -> SimpleExp | SimpleExp*
    private Regex repeat() throws ParseException {
        Regex fst = simple();
        if (peek() == '*') {
            match('*');
            return Regex.repetition(fst);
        }
        else
            return fst;
    }

    // SimpleExp -> ID | '(' RegExp ')'
    private Regex simple () throws ParseException {
        if (peek() == '(') {
            match('(');
            Regex res = regexp();
            match(')');
            return res;
        } else if (peek() == EPS) {
            match(EPS);
            return Regex.epsilon();
        } else
            return anychar();
    }
}