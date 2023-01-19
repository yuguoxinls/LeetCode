package SwordByClass.Others;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void printNumbers() {
        System.out.println(Arrays.toString(solution.printNumbers(2)));
    }

    @Test
    public void translateNum() {
        System.out.println(solution.translateNum(25));
    }
}