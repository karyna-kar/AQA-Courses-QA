import org.junit.Assert;
import org.junit.Test;

public class ArrayCounterTest {
    private int [] testArrayPositive = new int []{ 10, 5, 9};
    private int [] testArrayNegative = new int []{ -1, -5, -3};
    private int [] testArrayCombine = new int []{ -1, -2, 0, 11, 6};

    @Test
    public void checkMaxPosArray()
    {
        Assert.assertEquals(ArrayCounter.findMax(testArrayPositive), 10);
    }
    @Test
    public void checkMaxNegArray()
    {
        Assert.assertEquals(ArrayCounter.findMax(testArrayNegative), -1);
    }

    @Test
    public void checkMaxCombArray()
    {
        Assert.assertEquals(ArrayCounter.findMax(testArrayCombine), 11);
    }

    @Test
    public void checkMinPosArray()
    {
        Assert.assertEquals(ArrayCounter.findMin(testArrayPositive), 5);
    }

    @Test
    public void checkMinNegArray()
    {
        Assert.assertEquals(ArrayCounter.findMin(testArrayNegative), -5);
    }

    @Test
    public void checkMinCombArray()
    {
        Assert.assertEquals(ArrayCounter.findMin(testArrayCombine), -2);
    }
}
