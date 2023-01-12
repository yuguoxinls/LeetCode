package SwordByClass.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    /**
     * 21. 调整数组顺序使奇数位于偶数前面
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     * 示例：
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     */
    public int[] exchange(int[] nums) {
        if (nums.length == 0) return nums;
        int left = 0, right = nums.length-1;
        while (left < right){
            while (left < right && nums[left]%2 == 1) left++;
            while (left < right && nums[right]%2 == 0) right--;
            swap(nums,left,right);
        }
        return nums;
    }
    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    /**
     * 45. 把数组排成最小的数
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 示例 1:
     * 输入: [10,2]
     * 输出: "102"
     * 示例 2:
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     */
    public String minNumber(int[] nums) {
        // TODO: 2023/1/12 核心是排序规则的制定
        // 对于两个数 x,y 如果拼接结果 x+y>y+x，那么x是"大于"y的，反之亦然
        // 那么采用任何一种排序方式对nums中的元素由小到大排序，最后拼接即可
        String[] str = new String[nums.length];
        for (int i = 0; i < str.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }
//        Arrays.sort(str, ((o1, o2) -> (o1+o2).compareTo(o2+o1))); // 采用java自带排序API
        quickSort(str, 0, str.length-1); // 自己用快速排序实现，速度不如直接用自带API
        StringBuilder ans = new StringBuilder();
        for (String s : str) {
            ans.append(s);
        }
        return ans.toString();
    }
    private void quickSort(String[] str, int left, int right) {
        if (left >= right) return;
        int i = left, j = right;
        String tmp = str[i];
        while (i < j){
            while ((str[j]+str[left]).compareTo(str[left]+str[j]) >= 0 && i<j){
                // 这道题意义下的 str[j] > str[left]，也就是比基准大，我们要找比基准小的
                j--;
            }
            while ((str[i]+str[left]).compareTo(str[left]+str[i]) <= 0 && i<j) i++;
            tmp = str[i];
            str[i] = str[j];
            str[j] = tmp;
        }
        str[i] = str[left];
        str[left] = tmp;
        quickSort(str, left, i-1);
        quickSort(str, i+1, right);
    }
}
