## 双指针
### [Two Sum II - Input array is sorted](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) 
由于数组升序排列, 分别使用指针 ps 指向数组开头, 指针 pe 指向数组末尾, 当 array[ps] + array[pe] > target, 则 pe 向前移动, 否则 ps 向后移动, 直到 = target 