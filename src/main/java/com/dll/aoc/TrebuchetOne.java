package com.dll.aoc;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.IntStream;

public class TrebuchetOne {
  public int Run(String input_) {
    BufferedReader input = new BufferedReader(new StringReader(input_));
    return input
        .lines()
        .map(
            line -> {
              int first =
                  line.chars()
                      .mapToObj(c -> (char) c)
                      .filter(Character::isDigit)
                      .findFirst()
                      .map(Character::getNumericValue)
                      .orElse(0);
              int last =
                  IntStream.range(0, line.length())
                      .mapToObj(i -> line.charAt(line.length() - i - 1))
                      .filter(Character::isDigit)
                      .findFirst()
                      .map(Character::getNumericValue)
                      .orElse(0);
              return first * 10 + last;
            })
        .mapToInt(Integer::intValue)
        .sum();
  }
}
