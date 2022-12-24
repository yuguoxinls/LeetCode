package LeetCodeByClass.DataStructure.HashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     */
    public int[] twoSum(int[] nums, int target) {
        // TODO: 2022/12/24 hash
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int newTarget = target - nums[i];
            if (map.containsKey(newTarget)) return new int[] {map.get(newTarget), i};
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 2. 存在重复元素
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
     * 示例 1：
     * 输入：nums = [1,2,3,1]
     * 输出：true
     * 示例 2：
     * 输入：nums = [1,2,3,4]
     * 输出：false
     * 示例 3：
     * 输入：nums = [1,1,1,3,3,4,3,2,4,2]
     * 输出：true
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return true;
            else set.add(num);
        }
        return false;
    }

    /**
     * 3. 最长和谐子序列
     * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
     * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
     * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
     * 示例 1：
     * 输入：nums = [1,3,2,2,5,2,3,7]
     * 输出：5
     * 解释：最长的和谐子序列是 [3,2,2,2,3]
     * 示例 2：
     * 输入：nums = [1,2,3,4]
     * 输出：2
     * 示例 3：
     * 输入：nums = [1,1,1,1]
     * 输出：0
     */
    public int findLHS(int[] nums) { // TODO: 2022/12/24
        HashMap <Integer, Integer> cnt = new HashMap <>();
        int res = 0;
        for (int num : nums) { // 统计数组中每个元素出现的次数
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        for (int key : cnt.keySet()) { // keySet相当于去重后的数组，针对其中的每一个元素
            if (cnt.containsKey(key + 1)) { // 如果存在比当前元素大 1 的元素，也就是存在“和谐”，就动态更新当前元素以及“和谐”元素的最大个数和
                res = Math.max(res, cnt.get(key) + cnt.get(key + 1));
            }
        }
        return res;
    }
}
