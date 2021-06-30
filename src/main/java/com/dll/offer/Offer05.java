package com.dll.offer;

import java.util.stream.Stream;

public class Offer05 {
  class Solution {
    public String replaceSpace(String s) {
      if (s == null) {
        return "";
      }
      StringBuilder sb = new StringBuilder();
      Stream.of(s.split("")).forEach(ss -> {
        if (" ".equals(ss)) {
          sb.append("%20");
        } else {
          sb.append(ss);
        }
      });
      return sb.toString();
    }
  }
}
