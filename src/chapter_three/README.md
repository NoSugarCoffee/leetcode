## 双指针
### [Two Sum II - Input array is sorted](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) 
由于数组升序排列, 分别使用指针 ps 指向数组开头, 指针 pe 指向数组末尾, 当 array[ps] + array[pe] > target, 则 pe 向前移动, 否则 ps 向后移动, 直到 = target 

### [88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)
开辟足够大的 nums3, p1、p2 分别指向两个有序数组初始位置, 储存当前指针指向的较小值至 nums3 且移动指针, 相等则任选

