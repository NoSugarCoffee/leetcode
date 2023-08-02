package com.dll.array;

import java.util.Comparator;import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardFlippingGame {
  class Tuple {
    int index;
    int value;
    boolean IsPositive;

    public Tuple(int index, int value, boolean isPositive) {
      this.index = index;
      this.value = value;
      IsPositive = isPositive;
    }

    @Override public String toString() {
    return "Tuple{" +
    "index=" + index +
    ", value=" + value +
    ", IsPositive=" + IsPositive +
    '}';
}}

  class Solution {
    boolean different(Tuple tuple, int[] fronts, int[] backs) {
      if (fronts[tuple.index] == backs[tuple.index]) {
        return false;
      }
      int value = fronts[tuple.index];
      if (!tuple.IsPositive) {
        value = backs[tuple.index];
      }
      for (int i =0; i < tuple.index; i++) {
        if (value == fronts[i] && value == backs[i]) {
          return false;
        }
      }
      for (int i= tuple.index + 1; i < fronts.length; i++) {
        if (value == fronts[i] && value == backs[i]) {
          return false;
        }
      }
      return true;
    }

    public int flipgame(int[] fronts, int[] backs) {
      List<Tuple> frontsTuple =
          IntStream.range(0, fronts.length)
              .mapToObj(i -> new Tuple(i, fronts[i], true))
              .collect(Collectors.toList());

      List<Tuple> backsTuple =
          IntStream.range(0, backs.length)
              .mapToObj(i -> new Tuple(i, backs[i], false))
              .collect(Collectors.toList());
      frontsTuple.addAll(backsTuple);
      frontsTuple.sort(Comparator.comparingInt(o -> o.value));
      return frontsTuple.stream().filter((tuple) -> this.different(tuple, fronts, backs)).findFirst().orElseGet( () -> new Tuple(-1, 0, true)).value;
    }
  }
}
