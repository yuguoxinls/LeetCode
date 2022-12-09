package test.LeetCodeByClass.DataStructure.Tree;

import LeetCodeByClass.DataStructure.Tree.Solution;
import LeetCodeByClass.DataStructure.Tree.TreeNode;
import org.junit.Test;

/** 
* Solution Tester. 
* 
* @author <Authors name> 
* @since <pre>12ÔÂ 9, 2022</pre> 
* @version 1.0 
*/ 
public class SolutionTest {
    Solution s = new Solution();



/** 
* 
* Method: maxDepth(TreeNode root) 
* 
*/ 
@Test
public void testMaxDepth() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: isBalanced(TreeNode root) 
* 
*/ 
@Test
public void testIsBalanced() throws Exception { 
//TODO: Test goes here...
    TreeNode root = new TreeNode();
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    root.left = node1;
    root.right = node2;
    node1.left = node3;
    node1.right = node4;
    System.out.println(s.diameterOfBinaryTree(root));

} 


/** 
* 
* Method: height(TreeNode root) 
* 
*/ 
@Test
public void testHeight() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Solution.getClass().getMethod("height", TreeNode.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
