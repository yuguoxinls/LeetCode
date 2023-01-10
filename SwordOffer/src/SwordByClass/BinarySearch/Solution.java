package SwordByClass.BinarySearch;

public class Solution {
    /**
     * 11. 旋转数组的最小数字
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。
     * 请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果
     * 为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 示例 1：
     * 输入：numbers = [3,4,5,1,2]
     * 输出：1
     * 示例 2：
     * 输入：numbers = [2,2,2,0,1]
     * 输出：0
     */
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length-1;
        while (left < right){
            int mid = left + (right-left)/2;
            if (numbers[mid] > numbers[right]) left = mid+1;
            else if (numbers[mid] < numbers[right]) right = mid;
            else right--;
        }
        return numbers[left];
    }

    /**
     * 53. 在排序数组中查找数字 I
     * 统计一个数字在排序数组中出现的次数。
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     * 示例 2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int left = 0, right = nums.length - 1, mid = 0, count = 0;
        while (left <= right){
            mid = left + (right-left)/2;
            if (nums[mid] > target) right = mid-1;
            else if (nums[mid] < target) left = mid + 1;
            else break;
        }
        if (nums[mid] != target) return 0; // 如果数组中不存在target，提前返回
        for (int i = mid; i < nums.length; i++) {
            if (nums[i] == target) count++;
            else break;
        }
        for (int i = mid - 1; i >= 0; i--) {
            if (nums[i] == target) count++;
            else break;
        }
        return count;
    }
}
