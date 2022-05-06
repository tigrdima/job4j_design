package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {

    @Test
    public void produce() {

        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan Ivanov");
        map.put("subject", "you");

        String template =  "I am a ${name}, Who are ${subject}?";
        String expected = "I am a Ivan Ivanov, Who are you?";

        Generator generator = new GeneratorName();
        String rsl = generator.produce(template, map);
        assertThat(expected, is(rsl));
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceMapHaveNoKeys() {
        Map<String, String> map = new HashMap<>();

        String template = "I am a ${name}, Who are ${subject}?";

        Generator generator = new GeneratorName();
        generator.produce(template, map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceMapContainLessKeys() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan Ivanov");

        String template =  "I am a ${name}, Who are ${subject}?";

        Generator generator = new GeneratorName();
        generator.produce(template, map);

    }

    @Test(expected = IllegalArgumentException.class)
    public void produceMapContainRedundantKeys() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan Ivanov");
        map.put("subject", "you");
        map.put("age", "30 old");

        String template =  "I am a ${name}, Who are ${subject}?";

        Generator generator = new GeneratorName();
        generator.produce(template, map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceMapNotMatcherKeys() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan Ivanov");
        map.put("name2", "Petr Petrov");

        String template =  "I am a ${name}, Who are ${subject}?";

        Generator generator = new GeneratorName();
        generator.produce(template, map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceSampleHasNoKeys() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan Ivanov");
        map.put("name2", "Petr Petrov");

        String template =  "I am a Ivan, Who are you?";

        Generator generator = new GeneratorName();
        generator.produce(template, map);
    }
}