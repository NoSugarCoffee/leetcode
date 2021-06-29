# LeetCode

[![GitHub Super-Linter](https://github.com/NoSugarCoffee/leetcode/workflows/Lint%20Code%20Base/badge.svg)](https://github.com/marketplace/actions/super-linter)

LeetCode solutions writing in java. Rendered by hugo & github pages.
You can visit [here](https://nosugarcoffee.github.io/leetcode) for a happy reading.

## Local dev and preview

1. Add comment of relative file path in .md which you want to include, like following:

    ```java
    // ../../../../src/lrucache_146/LRUCache.java
    ```

2. Run `npx embedme README.md`

3. Run `hugo server` under web directory

## Github Actions

- Use [super-linter](https://github.com/github/super-linter) for code lint

- Use [actions-hugo](https://github.com/peaceiris/actions-hugo) for pages deploy

## Q&A

- How to embed source code by link rather than COPY&PASTE in README.

  You can use this [tool](https://github.com/zakhenry/embedme) written by js.