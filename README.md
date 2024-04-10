# LeetCode solutions

[![github pages](https://github.com/NoSugarCoffee/leetcode/actions/workflows/gh-pages.yml/badge.svg)](https://github.com/NoSugarCoffee/leetcode/actions/workflows/gh-pages.yml)
[![GitHub Super-Linter](https://github.com/NoSugarCoffee/leetcode/actions/workflows/linter-and-test.yml/badge.svg)](https://github.com/marketplace/actions/super-linter)


LeetCode solutions are written in Java. Rendered by Hugo & GitHub pages. You can
visit [here](https://nosugarcoffee.github.io/leetcode) for a happy reading.

## Local Development And Preview

1. Add the comment of the relative path in .md that you want to include, like the following:
    ```java
    // ../../../../src/lrucache_146/LRUCache.java
    ```
2. Run `npx embedme README.md`
3. Run `hugo server` under web/

## Github Actions

- Use [super-linter](https://github.com/github/super-linter) for code lint
- Use [setup-java](https://docs.github.com/en/actions/guides/building-and-testing-java-with-maven) for maven test
- Use [actions-hugo](https://github.com/peaceiris/actions-hugo) for pages deploy

## Spec

- Following [Chinese Copywriting Guidelines](https://github.com/sparanoid/chinese-copywriting-guidelines) for better written communication
## Q&A

- How to embed source code by link rather than COPY&PASTE in README?
    - You can use this [tool](https://github.com/zakhenry/embedme) written by js.