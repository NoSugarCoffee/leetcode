package com.dll.array;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RewardTopKStudents {
  class Solution {
    class Student implements Comparable<Student> {
      private int id;
      private int score;

      public Student(int id, int score) {
        this.id = id;
        this.score = score;
      }

      public int getId() {
        return id;
      }

      public void setId(int id) {
        this.id = id;
      }

      public int getScore() {
        return score;
      }

      public void setScore(int score) {
        this.score = score;
      }

      @Override
      public String toString() {
        return "Student{" + "id=" + id + ", score=" + score + '}';
      }

      @Override
      public int compareTo(Student o) {
        if (o.getScore() == this.getScore()) {
          return this.getId() - o.getId();
        }
        return o.getScore() - this.getScore();
      }
    }

    public Map<String, Integer> feedbackAndScore(String[] feedback, Integer val) {
      return Arrays.stream(feedback)
          .map(word -> new AbstractMap.SimpleEntry<>(word, val))
          .collect(
              Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    public List<Integer> topStudents(
        String[] positive_feedback,
        String[] negative_feedback,
        String[] report,
        int[] student_id,
        int k) {
      Map<String, Integer> positive = feedbackAndScore(positive_feedback, 3);
      Map<String, Integer> negative = feedbackAndScore(negative_feedback, -1);
      Map<String, Integer> feedbackAndScore =
          Stream.concat(positive.entrySet().stream(), negative.entrySet().stream())
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      List<Student> students =
          IntStream.range(0, report.length)
              .mapToObj(
                  i -> {
                    int score =
                        Arrays.stream(report[i].split(" "))
                            .map(each -> feedbackAndScore.getOrDefault(each, 0))
                            .mapToInt(Integer::intValue)
                            .sum();
                    return new Student(student_id[i], score);
                  })
              .collect(Collectors.toList());
      return Arrays.stream(students.toArray(new Student[] {}))
          .sorted(Student::compareTo)
          .map(Student::getId)
          .limit(k)
          .collect(Collectors.toList());
    }
  }
}
