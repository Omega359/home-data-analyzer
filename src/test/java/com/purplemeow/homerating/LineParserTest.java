package com.purplemeow.homerating;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class LineParserTest {

    private static Map<String, List<String>> data = new HashMap<>();

    static {
        data.put("\"John Doe\" \"Canada/Ontario/Toronto\" 1.5", Lists.newArrayList("John Doe", "Canada/Ontario/Toronto", "1.5"));
        data.put("\"John \\\"Doe\" \"Canada/Ontario/Toronto\" 1.5", Lists.newArrayList("John \"Doe", "Canada/Ontario/Toronto", "1.5"));
        data.put("\"John Doe\\\"\" \"Canada/Ontario/Toronto\" 1.5", Lists.newArrayList("John Doe\"", "Canada/Ontario/Toronto", "1.5"));
        data.put("\"Samanta Smith\" \"Canada/Ontario/London\" 3.7", Lists.newArrayList("Samanta Smith", "Canada/Ontario/London", "3.7"));
        data.put("\"Adam Xin\" \"Canada/British Columbia/Vancouver\" 2.110", Lists.newArrayList("Adam Xin", "Canada/British Columbia/Vancouver", "2.110"));
        data.put("\"Monica Taylor\" \"Canada/Ontario/Toronto\" 2.110", Lists.newArrayList("Monica Taylor", "Canada/Ontario/Toronto", "2.110"));
        data.put("\"Alicia Yazzie\" \"US/Arizona/Phoenix\" 5.532", Lists.newArrayList("Alicia Yazzie", "US/Arizona/Phoenix", "5.532"));
        data.put("\"Mohammed Zadeh\" \"Canada/Ontario/Toronto\"", Lists.newArrayList("Mohammed Zadeh", "Canada/Ontario/Toronto"));
        data.put("\" Mohammed Zadeh\"    \"Canada/Ontario/Toronto\"", Lists.newArrayList("Mohammed Zadeh", "Canada/Ontario/Toronto"));
        data.put("\"王秀英\" \"Canada/Ontario/Toronto\"", Lists.newArrayList("王秀英", "Canada/Ontario/Toronto"));
        data.put(" \"王秀英\" \"Canada/Ontario/Toronto\"                 ", Lists.newArrayList("王秀英", "Canada/Ontario/Toronto"));
    }

    @Test
    public void testParse() {
        for (String testStr : data.keySet()) {
            List<String> parsed = LineParser.parse(testStr);
            assertNotNull(parsed);
            assertFalse(parsed.isEmpty());
            assertEquals(data.get(testStr), parsed);
        }
    }
}
