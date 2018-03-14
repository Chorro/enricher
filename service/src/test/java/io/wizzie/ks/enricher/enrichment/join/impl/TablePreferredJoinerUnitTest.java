package io.wizzie.ks.enricher.enrichment.join.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TablePreferredJoinerUnitTest {

    TablePreferredJoiner tablePreferredJoiner;

    @Before
    public void init() {
        tablePreferredJoiner = new TablePreferredJoiner();
    }

    @Test
    public void tablePreferedJoinerTestShouldWork() {

        Map<String, Object> stream = new HashMap<>();
        stream.put("a", 1);
        stream.put("b", 2);

        Map<String, Object> table = new HashMap<>();
        table.put("c", 3);
        table.put("d", 4);

        Map<String, Object> result = tablePreferredJoiner.join(stream, table);

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("a", 1);
        expectedResult.put("b", 2);
        expectedResult.put("c", 3);
        expectedResult.put("d", 4);

        assertEquals(expectedResult, result);

    }

    @Test
    public void shouldReturnSameMapWhetherTableIsNull() {
        Map<String, Object> stream = new HashMap<>();
        stream.put("a", 1);
        stream.put("b", 2);

        Map<String, Object> table = null;

        Map<String, Object> result = tablePreferredJoiner.join(stream, table);

        assertEquals(stream, result);
    }

}
