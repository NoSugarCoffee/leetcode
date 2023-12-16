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
