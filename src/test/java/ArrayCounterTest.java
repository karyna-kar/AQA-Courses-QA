import org.testng.Assert;
import org.testng.annotations.Test;

public class ArrayCounterTest {
    private int [] testArrayPositive = new int []{ 10, 5, 9};
    private int [] testArrayNegative = new int []{ -1, -5, -3};
    private int [] testArrayCombine = new int []{ -1, -2, 0, 11, 6};

    @Test
    public void findMax()
    {
        Assert.assertEquals(ArrayCounter.findMax(testArrayPositive), 10);
        Assert.assertEquals(ArrayCounter.findMax(testArrayNegative), -1);
        Assert.assertEquals(ArrayCounter.findMax(testArrayCombine), 11);
    }

    @Test
    public void findMin()
    {
        Assert.assertEquals(ArrayCounter.findMin(testArrayPositive), 5);
        Assert.assertEquals(ArrayCounter.findMin(testArrayNegative), -5);
        Assert.assertEquals(ArrayCounter.findMin(testArrayCombine), -2);
    }
}

