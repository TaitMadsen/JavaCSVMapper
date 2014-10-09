import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MapperTest {

    private static final String FILE_PATH = "/Users/taitmadsen/Documents/javaPrograms/CSVMapper/tst/";

    @Test
    public void idealTest() throws IOException {
        String delimiter = ",";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH + "testBoxes.txt"));

        Mapper<TestBox> testBoxMapper = new Mapper<TestBox>(TestBox.class);
        List<TestBox> listOfBoxes = testBoxMapper.map(bufferedReader, delimiter);
        bufferedReader.close();

        List<TestBox> expectedBoxes = Arrays.asList(
                new TestBox(1, 1, 1, "red"),
                new TestBox(5, 3, 4, "blue"),
                new TestBox(4, 8, 4, "green"));

        assertEquals(expectedBoxes.get(0), listOfBoxes.get(0));
        assertEquals(expectedBoxes.get(1), listOfBoxes.get(1));
        assertEquals(expectedBoxes.get(2), listOfBoxes.get(2));
    }

    @Test(expected = RuntimeException.class)
    public void nonValueOf_test() throws IOException {
        String delimiter = ",";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH + "TestTransactions.txt"));

        Mapper<TestTransaction> transactionMapper = new Mapper<TestTransaction>(TestTransaction.class);
        transactionMapper.map(bufferedReader, delimiter);
        bufferedReader.close();
    }

    @Test(expected = RuntimeException.class)
    public void misappliedAnnotation_test() throws IOException {
        String delimiter = ",";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH + "testBooks.txt"));

        Mapper<TestBook> bookMapper = new Mapper<TestBook>(TestBook.class);
        bookMapper.map(bufferedReader, delimiter);
        bufferedReader.close();
    }

    @Test
    public void subclassingTest() throws IOException {
        String delimiter = ",";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH + "testFilledBoxes.txt"));

        Mapper<TestFilledBox> filledBoxMapper = new Mapper<TestFilledBox>(TestFilledBox.class);
        List<TestFilledBox> filledBoxList = filledBoxMapper.map(bufferedReader, delimiter);
        bufferedReader.close();

        List<TestFilledBox> expectedFilledBoxes = Arrays.asList(
                new TestFilledBox(1, 1, 1, "red", true),
                new TestFilledBox(5, 3, 4, "blue", false),
                new TestFilledBox(4, 8, 4, "green", false));

        assertEquals(expectedFilledBoxes.get(0), filledBoxList.get(0));
        assertEquals(expectedFilledBoxes.get(1), filledBoxList.get(1));
        assertEquals(expectedFilledBoxes.get(2), filledBoxList.get(2));
    }


}
