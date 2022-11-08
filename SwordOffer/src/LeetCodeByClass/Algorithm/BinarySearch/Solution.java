package LeetCodeByClass.Algorithm.BinarySearch;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Solution {
    /**
     * 二分查找
     * 时间复杂度
     * 二分查找也称为折半查找，每次都能将查找区间减半，这种折半特性的算法时间复杂度为 O(logN)。
     * m 计算，有两种计算中值 m 的方式：
     *  m = (l + h) / 2
     *  m = l + (h - l) / 2 最好使用第二种计算法方法。
     * l + h 可能出现加法溢出，也就是说加法的结果大于整型能够表示的范围。但是 l 和 h 都为正数，因此 h - l 不会出现加法溢出问题。
     * 未成功查找的返回值，循环退出时如果仍然没有查找到 key，那么表示查找失败。可以有两种返回值：
     *  -1：以一个错误码表示没有查找到 key
     *  l：将 key 插入到 nums 中的正确位置
     * **变种** 二分查找可以有很多变种，实现变种要注意边界值的判断
     */

    /**
     * 1. x 的平方根
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5
     * 示例 1：
     * 输入：x = 4
     * 输出：2
     * 示例 2：
     * 输入：x = 8
     * 输出：2
     * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     */
    public int mySqrt(int x) {
         /*if (x <= 1) return x; // TODO: 2022/11/7 直接查的话，有int越界问题，要转成long
        long left = 1, right = x-1;
        while (left <= right){
            long mid = left + (right - left)/2;
            if (mid * mid == x){
                return (int)mid;
            }else if (mid * mid < x){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return (int)right;*/
        if (x <= 1) return x;
        int left = 1, right = x-1;
        while (left <= right){
            int mid = left + (right - left)/2;
            int sqrt = x/mid; // TODO: 2022/11/7 用除的形式代替了上面直接相乘，避免了越界
            if (sqrt == mid) return mid;
            else if (sqrt > mid) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }

    /**
     * 2. 寻找比目标字母大的最小字母
     * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
     * 在比较时，字母是依序循环出现的。举个例子：
     * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
     * 示例 1：
     * 输入: letters = ["c", "f", "j"]，target = "a"
     * 输出: "c"
     * 示例 2:
     * 输入: letters = ["c","f","j"], target = "c"
     * 输出: "f"
     * 示例 3:
     * 输入: letters = ["c","f","j"], target = "d"
     * 输出: "f"
     */
    public char nextGreatestLetter(char[] letters, char target) { // TODO: 2022/11/7 数组中会有重复元素
        int left = 0, len = letters.length, right = len - 1, mid = 0;
        if (target >= letters[right]) return letters[0];
        while (left <= right){
            mid = left + (right - left)/2;
            if (letters[mid] <= target) left = mid + 1;
            else {
                right = mid - 1;
            }
        }
        return letters[left];
    }

    /**
     * 3. 有序数组中的单一元素
     * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
     * 请你找出并返回只出现一次的那个数。
     * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
     * 示例 1:
     * 输入: nums = [1,1,2,3,3,4,4,8,8]
     * 输出: 2
     * 示例 2:
     * 输入: nums =  [3,3,7,7,10,11,11]
     * 输出: 10
     */
    public int singleNonDuplicate(int[] nums) {
        /*Map<Integer, Boolean> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, !map.containsKey(num));
        }
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            if (Boolean.TRUE.equals(map.get(key))) return key;
        }
        return -1;*/
        // TODO: 2022/11/7 使用双指针提高效率
        /**
         * 单一元素的出现会使得其后面的元素规律被打乱，用这一性质来缩小区间
         * nums = [1,1,2,2,3,3,4,8,8]，设4的索引为index，以及left， mid，right，可见，在index之前，对于偶数的mid，有nums[mid]==nums[mid+1]
         */
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (m % 2 == 1) {
                m--;   // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[l];
    }

    /**
     * 4. 第一个错误的版本
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     * 示例 1：
     * 输入：n = 5, bad = 4
     * 输出：4
     * 解释：
     * 调用 isBadVersion(3) -> false
     * 调用 isBadVersion(5) -> true
     * 调用 isBadVersion(4) -> true
     * 所以，4 是第一个错误的版本。
     * 示例 2：
     * 输入：n = 1, bad = 1
     * 输出：1
     */
    /*public int firstBadVersion(int n) {

    }*/

    /**
     * 5. 寻找旋转排序数组中的最小值
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
     * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     *  若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     *  若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * 示例 1：
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
     * 示例 2：
     * 输入：nums = [4,5,6,7,0,1,2]
     * 输出：0
     * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
     * 示例 3：
     * 输入：nums = [11,13,15,17]
     * 输出：11
     * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left)/2;
            if (nums[mid] < nums[right]) right = mid; // TODO: 2022/11/8 主要是if中条件的确定，以及左右边界变化的时候在mid基础上怎么变
            else left = mid + 1;
        }
        return nums[left];
    }

    /**
     * 6. 在排序数组中查找元素的第一个和最后一个位置
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     */
    public int[] searchRange(int[] nums, int target) {
        int index = findTarget(nums, 0, nums.length - 1, target);
        if (index == -1) return new int[]{-1, -1};
        int leftIndex = 0, rightIndex = nums.length - 1;
        for (int i = index; i < nums.length; i++) {
            if (nums[i] == target) rightIndex = i;
            else break;
        }
        for (int i = index; i >= 0; i--) {
            if (nums[i] == target) leftIndex = i;
            else break;
        }
        return new int[]{leftIndex, rightIndex};
    }

    public int findTarget(int[] nums, int left, int right, int target) {
        int mid = 0;
        while (left <= right){
            mid = left + (right - left)/2;
            if (nums[mid] > target) right = mid - 1;
            else if (nums[mid] < target) left = mid + 1;
            else return mid;
        }
        return -1;
    }
}