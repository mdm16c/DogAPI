import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.*;

public class imageSearchTest {

    @Test
    public void test1() throws IOException {
        String[] s = new String[] {"-breed", "hound/blood", "-limit", "4"};
        imageSearch.main(s);
    }

    @Test
    public void test2() throws IOException {
        imageSearch.getBreedList();
    }
}