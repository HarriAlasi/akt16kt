package kodu2;
import kodu2.Regex;
 
public class RegexDemo {
    public static void main(String[] args) {
        Regex r = Regex.repetition(
                Regex.concatenation(
                        Regex.letter('a'),
                        Regex.alternation(
                                Regex.letter('b'),
                                Regex.epsilon()
                                )
                        )
                );
 
        System.out.println(r.toString()); // väljastab (a(b|ε))*
        System.out.println(r.matchesEmptyWord()); // väljastab true
        System.out.println(r.matchesInfinitelyManyWords()); // väljastab true
    }
}