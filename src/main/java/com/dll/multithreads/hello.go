package main
import "fmt"
import "sync"
func main() {
    var wg sync.WaitGroup
    ch_for, ch_bar := make(chan int, 1), make(chan int, 1)
    wg.Add(2)
    ch_for <- 1
    go func() {
        defer wg.Done()
        for i :=0; i < 10; i++ {
            select {
                case <- ch_for:
                    fmt.Println("foo")
                    ch_bar <- 1
            }
        }
    }()

    go func() {
        defer wg.Done()
        for i := 0; i < 10; i++ {
            select {
                case <- ch_bar:
                    fmt.Println("bar")
                    ch_for <- 1
            }
        }
    }()
    wg.Wait()
}