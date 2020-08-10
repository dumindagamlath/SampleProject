import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import examples.JoinerSplitter;

import static org.junit.Assert.assertEquals;

public class JoinerSplitterTest {

    @Test
    public void givenArray_transformedToStream_convertToString() {
        String[] programmingLanguages = {"java", "python", "nodejs", "ruby"};
        String expectation = "java,python,nodejs,ruby";

        String result  = JoinerSplitter.join(programmingLanguages);
        assertEquals(result, expectation);
    }

    @Test
    public void givenString_transformedToStream_convertToList() {
        String programmingLanguages = "java,python,nodejs,ruby";

        List<String> expectation = new ArrayList<>();
        expectation.add("java");
        expectation.add("python");
        expectation.add("nodejs");
        expectation.add("ruby");

        List<String> result  = JoinerSplitter.split(programmingLanguages);

        assertEquals(result, expectation);
    }

    @Test
    public void givenStringArray_transformedToStream_convertToMap() {

        String[] programming_languages = new String[] {"language:java","os:linux","editor:emacs"};

        Map<String,String> expectation=new HashMap<>();
        expectation.put("language", "java");
        expectation.put("os", "linux");
        expectation.put("editor", "emacs");

        Map<String, String> result = JoinerSplitter.arrayToMap(programming_languages);
        assertEquals(result, expectation);

    }
}
