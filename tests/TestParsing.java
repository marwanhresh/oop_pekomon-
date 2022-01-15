import org.junit.Assert;
import org.junit.Test;
import parsing.Parser;

public class TestParsing {
    @Test
    public void TestParsing1() {
        var filePath1 = "data/G1.json";

        var g = Parser.getGraph(filePath1);

        Assert.assertTrue(g != null);

        var filePath2 = "data/G1234.json";

        var ok = Parser.putGraph(g, filePath2);

        Assert.assertTrue(ok);

         g = Parser.getGraph(filePath2);

        Assert.assertTrue(g != null);
    }
}
