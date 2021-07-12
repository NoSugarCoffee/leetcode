package main

import "fmt"
import "sync"

func main() {
    var wg sync.WaitGroup
    chFor, chBar := make(chan int, 1), make(chan int, 1)
    wg.Add(2)
    chFor <- 1
    go func() {
        defer wg.Done()
        for i :=0; i < 10; i++ {
            if _, ok := <- chFor; ok {
                fmt.Println("foo")
                chBar <- 1
            }
        }
    }()

    go func() {
        defer wg.Done()
        for i := 0; i < 10; i++ {
            if _, ok := <- chBar; ok {
                fmt.Println("bar")
                chFor <- 1
            }
        }
    }()
    wg.Wait()
    close(chFor)
    close(chBar)
}