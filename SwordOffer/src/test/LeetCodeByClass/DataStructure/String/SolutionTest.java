package test.LeetCodeByClass.DataStructure.String; 

import LeetCodeByClass.DataStructure.String.Solution;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Solution Tester. 
* 
* @author <Authors name> 
* @since <pre>12ÔÂ 25, 2022</pre> 
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
* Method: isAnagram(String s, String t) 
* 
*/
Solution solution = new Solution();
@Test
public void testIsAnagram() throws Exception { 
//TODO: Test goes here...

    String s = "a";
    String t = "ab";
    System.out.println(solution.isAnagram(s, t));
}

@Test
public void testLongestPalindrome() {
    String s = "abccccdd";
    System.out.println(solution.longestPalindrome(s));
}
@Test
public void testIsIsomorphic(){
    System.out.println(solution.isIsomorphic("add", "egg"));
}

@Test
public void testCountSubstrings(){
    System.out.println(solution.countSubstrings("aaa"));
}

@Test
public void testCountBinarySubstrings(){
    System.out.println(solution.countBinarySubstrings("00111011"));
}





} 
