package com.data.lambda.part2.example;

import static org.junit.Assert.assertEquals;

import com.data.Person;
import java.util.function.Function;
import org.junit.Test;

public class FunctionCombination {

    @Test
    public void personToInt0() {
        // Person -> Integer
        final Function<Person, Integer> lastNameLength = p -> p.getLastName().length();

        assertEquals(Integer.valueOf(5), lastNameLength.apply(new Person("a", "abcde", 0))); // TODO : fix
    }

    // (Person -> String, String -> Integer) -> (Person -> Integer)
    private Function<Person, Integer> personStringPropertyToInt(
            Function<Person, String> personToString,
            Function<String, Integer> stringToInteger) {
        return p -> {
            final String str = personToString.apply(p);
            final Integer result = stringToInteger.apply(str);
            return result;
        };
    }

    @Test
    public void personToInt1() {
        final Function<Person, String> getLastName = Person::getLastName;
        final Function<String, Integer> getLength = String::length;
        final Function<Person, Integer> lastNameLength = personStringPropertyToInt(getLastName, getLength);

        assertEquals(Integer.valueOf(5), lastNameLength.apply(new Person("a", "abcde", 0)));
    }

    // (A -> B, B -> C) -> A -> C
    private <A, B, C> Function<A, C> andThen(Function<A, B> f1, Function<B, C> f2) {
        return a -> f2.apply(f1.apply(a));
    }

    @Test
    public void personToInt2() {
        final Function<Person, String> getLastName = Person::getLastName;
        final Function<String, Integer> getLength = String::length;
        final Function<Person, Integer> lastNameLength = andThen(getLastName, getLength);

        assertEquals(Integer.valueOf(5), lastNameLength.apply(new Person("a", "abcde", 0))); // TODO: fix
    }
}
