package LeetCodeByClass.DataStructure.HashTable;

import java.util.*;

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

    /**
     * 4. 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 示例 1：
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 示例 2：
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     */
    public int longestConsecutive(int[] nums) {
        // TODO: 2022/12/25 https://leetcode.cn/problems/longest-consecutive-sequence/solutions/276931/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
        /**
         * 我们考虑枚举数组中的每个数 x，考虑以其为起点，不断尝试匹配 x+1,x+2,⋯是否存在，假设最长匹配到了 x+y，
         * 那么以 x 为起点的最长连续序列即为 x,x+1,x+2,⋯,x+y，其长度为 y+1，我们不断枚举并更新答案即可
         *
         * 对于匹配的过程，暴力的方法是 O(n)遍历数组去看是否存在这个数，
         * 但其实更高效的方法是用一个哈希表存储数组中的数，这样查看一个数是否存在即能优化至 O(1) 的时间复杂度
         *
         * 仔细分析这个过程，我们会发现其中执行了很多不必要的枚举，
         * 如果已知有一个 x,x+1,x+2,⋯,x+y 的连续序列，而我们却重新从 x+1，x+2 或者是 x+y 处开始尝试匹配，那么得到的结果肯定不会优于枚举 x 为起点的答案，
         * 因此我们在外层循环的时候碰到这种情况跳过即可
         *
         * 那么怎么判断是否跳过呢？由于我们要枚举的数 x 一定是在数组中不存在前驱数 x−1 的，不然按照上面的分析我们会从 x−1 开始尝试匹配，
         * 因此我们每次在哈希表中检查是否存在 x−1 即能判断是否需要跳过了。
         */

        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) { // 使用hashset存储数组元素，方便后续的查找，同时也达到了去重的效果
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) { // 我们要枚举的数 x 一定是不存在前驱数 x−1 的，不然会从 x−1 开始尝试匹配，如果存在的话就直接进行下一次循环
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) { // 如果包含x+1，当前的最长序列自增一，同时判断是否有x+2，如此循环
                    currentNum += 1;
                    currentStreak += 1;
                }
                // 退出循环时，得到了以当前 x 为起点的最长子序列长度，下面动态维护最大子序列长度即可
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}
