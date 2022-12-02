package LeetCodeByClass.Algorithm.DynamicProgramming;


import org.junit.Test;

public class FibonacciTest {
    Fibonacci fibonacci = new Fibonacci();

    @Test
    public void climbStairs() {
        System.out.println(fibonacci.climbStairs(2));
    }

    @Test
    public void rob() {
        int[] nums = {1,2};
        System.out.println(fibonacci.rob(nums));
    }

    @Test
    public void rob2() {
        int[] nums = {1,2,3,1};
        System.out.println(fibonacci.rob2(nums));
    }
}