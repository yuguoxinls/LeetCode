package LeetCodeByClass.Algorithm.Sort;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    /**
     * 1. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 示例 1:
     * 输入: [3,2,1,5,6,4], k = 2
     * 输出: 5
     * 示例 2:
     * 输入: [3,2,3,1,2,4,5,5,6], k = 4
     * 输出: 4
     */
    public int findKthLargest(int[] nums, int k) {
        // 方法一：排序
        /*quickSortRe(nums, 0, nums.length - 1);
        return nums[k-1];*/
        // 方法二：堆 效率不如方法一
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
        // 方法三：基于快速排序的快速选择 放弃
    }
    private void quickSortRe(int[] nums, int l, int r) {
        // 子数组长度为 1 时终止递归
        if (l >= r) return;
        int i = l, j = r;
        while (i < j){
            while (i < j && nums[j] <= nums[l]) j--;
            while (i < j && nums[i] >= nums[l]) i++;
            swap(nums, i, j);
        }
        swap(nums, i, l);
        quickSort(nums, l, i - 1);
        quickSort(nums, i + 1, r);
    }
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 桶排序
     * 1. 前 K 个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     */
    public int[] topKFrequent(int[] nums, int k) {
        /*int[] ans = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)){
                map.put(num, 1);
            }else {
                Integer value = map.get(num);
                map.put(num, ++value);
            }
        }
        Set<Integer> keySet = map.keySet();
        for (int i = 0; i < k; i++) {
            int max = -1, tmpKey = -1;
            for (Integer key : keySet) {
                Integer value = map.get(key);
                if (value > max){
                    max = value;
                    tmpKey = key;
                }
            }
            ans[i] = tmpKey;
            map.remove(tmpKey);
        }
        return ans;*/
        /**
         * 使用桶排序
         * 设置若干个桶，每个桶存储出现频率相同的数。桶的下标表示数出现的频率，即第 i 个桶中存储的数出现的频率为 i。
         * 把数都放到桶之后，从后向前遍历桶，最先得到的 k 个数就是出现频率最多的的 k 个数。
         */
        Map<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int key : frequencyForNum.keySet()) {
            int frequency = frequencyForNum.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
            if (buckets[i] == null) {
                continue;
            }
            if (buckets[i].size() <= (k - topK.size())) {
                topK.addAll(buckets[i]);
            } else {
                topK.addAll(buckets[i].subList(0, k - topK.size()));
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = topK.get(i);
        }
        return res;
    }

    /**
     * 2. 根据字符出现频率排序
     * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
     * 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。
     * 示例 1:
     * 输入: s = "tree"
     * 输出: "eert"
     * 解释: 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     * 示例 2:
     * 输入: s = "cccaaa"
     * 输出: "cccaaa"
     * 解释: 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
     * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
     * 示例 3:
     * 输入: s = "Aabb"
     * 输出: "bbAa"
     * 解释: 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
     * 注意'A'和'a'被认为是两种不同的字符。
     */
    public String frequencySort(String s) {
        /*Map<Character, Integer> frequencyForCh = new HashMap<>();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            frequencyForCh.put(s.charAt(i), frequencyForCh.getOrDefault(s.charAt(i), 0) + 1);
        }

        Set<Character> set = frequencyForCh.keySet();
        List<Character> collect = set.stream().sorted((o1, o2) -> frequencyForCh.get(o2).compareTo(frequencyForCh.get(o1))).collect(Collectors.toList());
        for (Character character : collect) {
            Integer frequency = frequencyForCh.get(character);
            for (int i = 0; i < frequency; i++) {
                str.append(character);
            }
        }
        return str.toString();*/
        Map<Character, Integer> frequencyForNum = new HashMap<>();
        for (char c : s.toCharArray())
            frequencyForNum.put(c, frequencyForNum.getOrDefault(c, 0) + 1);

        List<Character>[] frequencyBucket = new ArrayList[s.length() + 1];
        for (char c : frequencyForNum.keySet()) {
            int f = frequencyForNum.get(c);
            if (frequencyBucket[f] == null) {
                frequencyBucket[f] = new ArrayList<>();
            }
            frequencyBucket[f].add(c);
        }
        StringBuilder str = new StringBuilder();
        for (int i = frequencyBucket.length - 1; i >= 0; i--) {
            if (frequencyBucket[i] == null) {
                continue;
            }
            for (char c : frequencyBucket[i]) {
                for (int j = 0; j < i; j++) {
                    str.append(c);
                }
            }
        }
        return str.toString();
    }

    /**
     * 3. 颜色分类
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * 必须在不使用库的sort函数的情况下解决这个问题。
     * 示例 1：
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     * 示例 2：
     * 输入：nums = [2,0,1]
     * 输出：[0,1,2]
     */
    public void sortColors(int[] nums) {
//        quickSort(nums, 0, nums.length - 1);
        int zero = -1, one = 0, two = nums.length;  // 看不懂
        while (one < two) {
            if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else if (nums[one] == 2) {
                swap(nums, --two, one);
            } else {
                ++one;
            }
        }
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int i = left, j = right;
        while (i < j){
            while (i < j && nums[j] >= nums[left]) j--;
            while (i < j && nums[i] <= nums[left]) i++;
            swap(nums, i, j);
        }
        swap(nums, i, left);
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }
}
