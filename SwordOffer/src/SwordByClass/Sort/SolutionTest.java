package SwordByClass.Sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void exchange() {
        System.out.println(Arrays.toString(solution.exchange(new int[]{1, 2, 3, 4})));
    }
}