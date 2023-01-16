package SwordByClass.Math;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void majorityElement() {
        System.out.println(solution.majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }
}