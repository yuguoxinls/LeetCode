package test.LeetCodeByClass.Algorithm.Math; 

import LeetCodeByClass.Algorithm.Math.Solution;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Solution Tester. 
* 
* @author <Authors name> 
* @since <pre>12ÔÂ 2, 2022</pre> 
* @version 1.0 
*/ 
public class SolutionTest {
    Solution s = new Solution();

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: countPrimes(int n) 
* 
*/ 
@Test
public void testCountPrimes() throws Exception { 
//TODO: Test goes here...
    System.out.println(s.countPrimes(499979));
} 


/** 
* 
* Method: isZ(int num) 
* 
*/ 
@Test
public void testIsZ() throws Exception { 
//TODO: Test goes here... 

    System.out.println(s.isZ(2));
}

@Test
public void testConvertToBase7(){
    System.out.println(s.convertToBase7(100));
}

@Test
public void testToHex(){
    System.out.println(s.toHex(-1));
}

@Test
public void testConvertToTitle(){
    System.out.println(s.convertToTitle(2147483647));
}

@Test
public void testTrailingZeroes(){
    System.out.println(s.trailingZeroes(3));
}

@Test
public void testMajorityElement(){
    System.out.println(s.majorityElement(new int[]{8,8,7,7,7}));
}

@Test
public void testIsPerfectSquare(){
    System.out.println(s.isPerfectSquare(808201));
}

@Test
public void testMaximumProduct(){
    System.out.println(s.maximumProduct(new int[]{-1, -2, -3}));
}

} 
