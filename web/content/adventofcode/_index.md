---
title: AdventOfCode
weight: 3
---

<!-- markdownlint-disable MD010 MD013 MD024-->

## 2023 day1

[problem](https://adventofcode.com/2023/day/1)

### Part I

```java
// ../../../src/main/java/com/dll/aoc/TrebuchetOne.java

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

```

### Part II

```go
// ../../../golang/aoc2023/trebuchet_two.go

package aoc2023

import (
	"strings"
	"strconv"
)

var letterDigits = []string{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}
var letterDigitsReversed = []string{"eno", "owt", "eerht", "ruof", "evif", "xis", "neves", "thgie", "enin"}

var letterDigitsMap = map[string]int{
	"one":   1,
	"two":   2,
	"three": 3,
	"four":  4,
	"five":  5,
	"six":   6,
	"seven": 7,
	"eight": 8,
	"nine":  9,
}

var letterDigitsReversedMap = map[string]int{
	"eno":   1,
	"owt":   2,
	"eerht": 3,
	"ruof":  4,
	"evif":  5,
	"xis":   6,
	"neves": 7,
	"thgie": 8,
	"enin":  9,
}

func isDigital(given string) (int, bool) {
	if len(given) != 1 {
		return 0, false
	}
	digital, err := strconv.Atoi(given)
	return digital, err == nil
}

func isLetterDigitalPrefix(given string, letterDigits []string) bool {
	for _, letterDigit := range letterDigits {
		if strings.HasPrefix(letterDigit, given) {
			return true
		}
	}
	return false
}

func getWindow(line string, start int, end int) string {
	return line[start:end]
}

func meetFirstDigital(line string, letterDigits []string, letterDigitsMap map[string]int) int {
	for start, end := 0, 1; start < len(line) && end <= len(line); {
		window := getWindow(line, start, end)
		if digital, bol := isDigital(window); bol {
			return digital
		}
		if bol := isLetterDigitalPrefix(window, letterDigits); bol {
			if val, ok := letterDigitsMap[window]; ok {
				return val
			}
			end++
		} else {
			if start < end {
				start++
			} else {
				end++
			}
		}
	}
	panic("shold not reach here")
	return -1
}

func reverse(in string) (out string) {
	for _, r := range in {
		out = string(r) + out
	}
	return
}

func Run(input string) int {
	lines := strings.Split(input, "\n")
	sum := 0
	for _, line := range lines {
		sum += meetFirstDigital(line, letterDigits, letterDigitsMap)*10 + meetFirstDigital(reverse(line), letterDigitsReversed, letterDigitsReversedMap)
	}
	return sum
}
```

## 2023 day2

[problem](https://adventofcode.com/2023/day/2)

### Part I

```go
// ../../../golang/aoc2023/cube_conundrum.go

package aoc2023

import "strings"
import (
	"regexp"
	"strconv"
)

func PartOne(input string) int {
	var re = regexp.MustCompile(`(?mi)(\d+) (\w+)[,;]?`)
	lines := strings.Split(input, "\n")
	result := 0
	for i, line := range lines {
		m := map[string]int{
			"red":   0,
			"green": 0,
			"blue":  0,
		}
		match := re.FindAllStringSubmatch(line, -1)
		for _, group := range match {
			count, _ := strconv.Atoi(group[1])
			color := group[2]
			if exist, ok := m[color]; ok && exist < count {
				m[color] = count
			}
		}
		if m["red"] <= 12 && m["green"] <= 13 && m["blue"] <= 14 {
			result += i + 1
		}
	}
	return result
}
```
