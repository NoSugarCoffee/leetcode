package com.dll.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BestTimeToBuyAndSellStockII {
  class Solution {
    public int maxProfit(int[] prices) {
      List<Integer> profit = new ArrayList<>();
      IntStream.range(0, prices.length)
          .forEach(
              i -> {
                if (i < prices.length - 1) {
                  profit.add(prices[i + 1] - prices[i]);
                }
              });
      return profit.stream().filter(i -> i > 0).mapToInt(i -> i).sum();
    }
  }
}
