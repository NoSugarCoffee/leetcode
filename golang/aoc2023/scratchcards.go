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
			total += 1
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
		instances[cardIndex] = instances[cardIndex] + 1
		for j := 1; j <= matched; j++ {
			instances[cardIndex+j] = instances[cardIndex+j] + instances[cardIndex]
		}
	}
	for _, instance := range instances {
		result += instance
	}
	return result
}
