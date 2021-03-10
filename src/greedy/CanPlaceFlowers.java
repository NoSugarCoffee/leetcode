package greedy;

public class CanPlaceFlowers {

  class Solution {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
      int zeroCountContinuous = 0;
      int counter = 0;
      for (int i = 0; i < flowerbed.length; i++) {
        if (flowerbed[i] == 1) {
          if (i - 1 >= 0) {
            flowerbed[i - 1] = 2;
            if (zeroCountContinuous > 0) {
              zeroCountContinuous--;
            }
          }
          if (i + 1 < flowerbed.length) {
            flowerbed[i + 1] = 2;
          }
          counter += (zeroCountContinuous + 1) / 2;
          zeroCountContinuous = 0;
        } else if (flowerbed[i] == 0) {
          zeroCountContinuous++;
        }
      }
      if (zeroCountContinuous > 0) {
        counter += (zeroCountContinuous + 1) / 2;
      }
      return counter >= n;
    }
  }
}
