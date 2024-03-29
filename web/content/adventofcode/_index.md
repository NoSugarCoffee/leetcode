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

```go
// ../../../golang/aoc2023/cube_conundrum.go

package aoc2023

import (
	"regexp"
	"strconv"
	"strings"
)

func CubeCountingPartOne(input string) int {
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

func CubeCountingPartTwo(input string) int {
	var re = regexp.MustCompile(`(?mi)(\d+) (\w+)[,;]?`)
	lines := strings.Split(input, "\n")
	result := 0
	for _, line := range lines {
		m := map[string]int{
			"red":   1,
			"green": 1,
			"blue":  1,
		}
		match := re.FindAllStringSubmatch(line, -1)
		for _, group := range match {
			count, _ := strconv.Atoi(group[1])
			color := group[2]
			if exist, ok := m[color]; ok && exist < count {
				m[color] = count
			}
		}
		result += m["red"] * m["green"] * m["blue"]
	}
	return result
}

```

## 2023 day3

[problem](https://adventofcode.com/2023/day/3)

```go
// ../../../golang/aoc2023/gear_ratios.go

package aoc2023

import (
	"fmt"
	"regexp"
	"strconv"
	"strings"
)

type Point struct {
	x            int
	y            int
	Parent       *Point
	AroundPoints []Point
}

func NewPoint(x, y int) Point {
	return Point{x, y, nil, []Point{}}
}

func NewParent(x, y int, parent *Point) Point {
	return Point{x, y, parent, []Point{}}
}

func (p *Point) String() string {
	return fmt.Sprintf("(%d, %d)", p.x, p.y)
}

var numberAndDot = "0123456789."

func exist(point Point, points []Point) (Point, bool) {
	for _, p := range points {
		for _, x := range p.AroundPoints {
			if x.String() == point.String() {
				return p, true
			}
		}
	}
	return NewPoint(-1, -1), false
}

func GearRatiosPartOne(input string) int {
	symbolBoardAndAround := make([]Point, 0)
	for i, line := range strings.Split(input, "\n") {
		for j, ch := range line {
			if !strings.Contains(numberAndDot, string(ch)) {
				around := make([]Point, 0)
				center := NewPoint(i, j)
				for x := i - 1; x <= i+1; x++ {
					for y := j - 1; y <= j+1; y++ {
						if !(x == i && y == j) {
							around = append(around, NewParent(x, y, &center))
						}
					}
				}
				center.AroundPoints = around
				symbolBoardAndAround = append(symbolBoardAndAround, center)
			}
		}
	}
	re := regexp.MustCompile(`(?mi)\d+`)
	result := 0
	for x, line := range strings.Split(input, "\n") {
		numbersIndex := re.FindAllStringIndex(line, -1)
		numbers := re.FindAllString(line, -1)
		for i, numberIndex := range numbersIndex {
			start := numberIndex[0]
			end := numberIndex[1]
			for y := start; y < end; y++ {
				if _, bol := exist(NewPoint(x, y), symbolBoardAndAround); bol {
					n, _ := strconv.Atoi(numbers[i])
					result += n
					break
				}
			}
		}
	}
	return result
}

var gear = "*"

func GearRatiosPartTwo(input string) int {
	gearBoard := make([]Point, 0)
	for i, line := range strings.Split(input, "\n") {
		for j, v := range line {
			parent := NewPoint(i, j)
			if strings.Contains(gear, string(v)) {
				around := make([]Point, 0)
				for x := i - 1; x <= i+1; x++ {
					for y := j - 1; y <= j+1; y++ {
						if !(x == i && y == j) {
							around = append(around, Point{x, y, &parent, []Point{}})
						}
					}
				}
				parent.AroundPoints = around
				gearBoard = append(gearBoard, parent)
			}
		}
	}
	adjacentMultiply := make(map[string]int)
	re := regexp.MustCompile(`(?mi)\d+`)
	result := 0
	for x, line := range strings.Split(input, "\n") {
		numbersIndex := re.FindAllStringIndex(line, -1)
		numbers := re.FindAllString(line, -1)
		for ni, numberIndex := range numbersIndex {
			start := numberIndex[0]
			end := numberIndex[1]
			for y := start; y < end; y++ {
				if p, ok := exist(NewPoint(x, y), gearBoard); ok {
					n, _ := strconv.Atoi(numbers[ni])
					if v, ok := adjacentMultiply[p.String()]; ok {
						result += v * n
					} else {
						adjacentMultiply[p.String()] = n
					}
					break
				}
			}
		}
	}
	return result
}

```

## 2023 day4

[problem](https://adventofcode.com/2023/day/4)

```go
// ../../../golang/aoc2023/scratchcards.go

package aoc2023

import (
	"math"
	"regexp"
	"strconv"
	"strings"
)

func calcPoints(times int) int {
	if times > 0 {
		return int(math.Pow(2, float64(times-1)))
	}
	return 0
}

var re = regexp.MustCompile(`(?mi)Card([\d ]+):([\d ]+)\|([\d ]+)`)

func splitToNumbers(numberStr string) []int {
	strs := strings.Split(numberStr, " ")
	var nums = make([]int, 0)
	for _, s := range strs {
		if s != "" {
			si, _ := strconv.Atoi(s)
			nums = append(nums, si)
		}
	}
	return nums
}

func matchCount(line string) int {
	match := re.FindAllStringSubmatch(line, -1)
	left := match[0][2]
	right := match[0][3]

	leftNums := splitToNumbers(left)
	leftNumbsMap := make(map[int]int)
	for _, num := range leftNums {
		leftNumbsMap[num] = -1
	}
	rightNums := splitToNumbers(right)
	total := 0
	for _, num := range rightNums {
		if leftNumbsMap[num] == -1 {
			total++
		}
	}
	return total
}

func ScratchcardsPartOne(input string) int {
	lines := strings.Split(input, "\n")
	result := 0
	for _, line := range lines {
		result += calcPoints(matchCount(line))
	}
	return result
}

func ScratchcardsPartTwo(input string) int {
	lines := strings.Split(input, "\n")
	instances := make(map[int]int, 0)
	result := 0
	for i, line := range lines {
		cardIndex := i + 1
		matched := matchCount(line)
		instances[cardIndex]++
		for j := 1; j <= matched; j++ {
			instances[cardIndex+j] += instances[cardIndex]
		}
	}
	for _, instance := range instances {
		result += instance
	}
	return result
}

```