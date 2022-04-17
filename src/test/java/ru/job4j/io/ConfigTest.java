package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/config1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.password"), is(nullValue()));
        assertThat(config.value("hibernate.connection.username"), is("postgres=true"));
    }

    @Test
    public void whenConfigWithCommentWithEmptyString() {
        String path = "./data/config2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is(nullValue()));
    }

   @Test(expected = IllegalArgumentException.class)
    public void whenConfigWithPatternViolation() {
        String path = "./data/config3.properties";
        Config config = new Config(path);
        config.load();
    }
}