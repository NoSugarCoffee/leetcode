package com.dll.array;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BeautifulArrangement {
  class Solution {
    private List<Integer> path = new ArrayList<>();
    private int N;
    private int result;

    private int[] remain(int[] array, int index) {
      return IntStream.range(0, array.length).filter(i -> index != i).map(i -> array[i]).toArray();
    }

    private boolean isArrangement(int[] array) {
      for (int i = 0; i < array.length; i++) {
        if (array[array.length - i - 1] % (array.length - i) != 0
            && (array.length - i) % array[array.length - i - 1] != 0) {
          return false;
        }
      }
      return true;
    }

    private void recursion(int[] array) {
      if (path.size() > 0
          && path.get(path.size() - 1) % (path.size()) != 0
          && path.size() % path.get(path.size() - 1) != 0) {
        return;
      }
      if (this.path.size() == N) {
        result++;
      }
      for (int i = 0; i < array.length; i++) {
        this.path.add(array[i]);
        recursion(this.remain(array, i));
        this.path.remove(this.path.size() - 1);
      }
    }

    public int countArrangement(int n) {
      init(n);
      this.recursion(IntStream.rangeClosed(1, n).toArray());
      return result;
    }

    private void init(int n) {
      this.N = n;
    }
  }
}
