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
