package test.LeetCodeByClass.DataStructure.ArrayAndMatrix; 

import LeetCodeByClass.DataStructure.ArrayAndMatrix.Solution;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

/** 
* Solution Tester. 
* 
* @author <Authors name> 
* @since <pre>12ÔÂ 27, 2022</pre> 
* @version 1.0 
*/ 
public class SolutionTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: moveZeroes(int[] nums) 
* 
*/
Solution solution = new Solution();
@Test
public void testMoveZeroes() throws Exception { 
//TODO: Test goes here...

    solution.moveZeroes(new int[] {0,1,0,3,12});
}

@Test
public void testFindMaxConsecutiveOnes(){
    System.out.println(solution.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
}

@Test
public void testFindShortestSubArray(){
    System.out.println(solution.findShortestSubArray(new int[]{1,3,2,2,3,1}));
}


} 
