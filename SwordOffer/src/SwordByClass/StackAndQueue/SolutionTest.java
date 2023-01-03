package SwordByClass.StackAndQueue;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {

    Solution solution = new Solution();
    @Test
    public void getLeastNumbers() {
        System.out.println(Arrays.toString(solution.getLeastNumbers(new int[]{3, 2, 1}, 2)));
    }
}