package test.LeetCodeByClass.DataStructure.BitOperations; 

import LeetCodeByClass.DataStructure.BitOperations.Solution;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.Arrays;

/** 
* Solution Tester. 
* 
* @author <Authors name> 
* @since <pre>12ÔÂ 30, 2022</pre> 
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
* Method: hammingDistance(int x, int y) 
* 
*/
Solution solution = new Solution();
@Test
public void testHammingDistance() throws Exception { 
//TODO: Test goes here...

    System.out.println(solution.hammingDistance(1, 4));
}

@Test
public void testSingleNumber3(){
    System.out.println(Arrays.toString(solution.singleNumber3(new int[]{1, 2, 1, 3, 2, 5})));
}

@Test
public void testFindComplement(){
    System.out.println(solution.findComplement(5));
}

@Test
public void testMaxProduct(){
    System.out.println(solution.maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
}


} 
