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
