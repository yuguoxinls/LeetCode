package SwordByClass.Search;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void getSum() {
        System.out.println(solution.getSum(3));
    }

    @Test
    public void movingCount() {
        System.out.println(solution.movingCount(2, 3, 1));
    }

    @Test
    public void permutation() {
        System.out.println(Arrays.toString(solution.permutation("aab")));
    }
}