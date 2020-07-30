import org.junit.Assert;
import org.junit.Test;

public class ArrayCounterTest {
    private int [] testArrayPositive = new int []{ 10, 5, 9};
    private int [] testArrayNegative = new int []{ -1, -5, -3};
    private int [] testArrayCombine = new int []{ -1, -2, 0, 11, 6};

    @Test
    public void findMaxPosArray()
    {
        Assert.assertEquals(ArrayCounter.findMax(testArrayPositive), 10);
    }
    @Test
    public void findMaxNegArray()
    {
        Assert.assertEquals(ArrayCounter.findMax(testArrayNegative), -1);
    }

    @Test
    public void findMaxCombArray()
    {
        Assert.assertEquals(ArrayCounter.findMax(testArrayCombine), 11);
    }

    @Test
    public void findMinPosArray()
    {
        Assert.assertEquals(ArrayCounter.findMin(testArrayPositive), 5);
    }

    @Test
    public void findMinNegArray()
    {
        Assert.assertEquals(ArrayCounter.findMin(testArrayNegative), -5);
    }

    @Test
    public void findMinCombArray()
    {
        Assert.assertEquals(ArrayCounter.findMin(testArrayCombine), -2);
    }
}
