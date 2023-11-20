package com.dll.array;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class RewardTopKStudentsTest {
  @Test
  public void TestRewardTopKStudents() {
    String[] positive_feedback = new String[] {"smart", "brilliant", "studious"};
    String[] negative_feedback = new String[] {"not"};
    String[] report = new String[] {"this student is studious", "the student is smart"};
    int[] student_id = new int[] {1, 2};
    int k = 2;
    List<Integer> integers =
        new RewardTopKStudents().new Solution()
            .topStudents(positive_feedback, negative_feedback, report, student_id, k);
    Assert.assertArrayEquals(new Integer[] {1, 2}, integers.toArray());
  }

  @Test
  public void TestRewardTopKStudents2() {
    String[] positive_feedback = new String[] {"fkeofjpc", "qq", "iio"};
    String[] negative_feedback =
        new String[] {"jdh", "khj", "eget", "rjstbhe", "yzyoatfyx", "wlinrrgcm"};
    String[] report =
        new String[] {
          "rjstbhe eget kctxcoub urrmkhlmi yniqafy fkeofjpc iio yzyoatfyx khj iio",
          "gpnhgabl qq qq fkeofjpc dflidshdb qq iio khj qq yzyoatfyx",
          "tizpzhlbyb eget z rjstbhe iio jdh jdh iptxh qq rjstbhe",
          "jtlghe wlinrrgcm jnkdbd k iio et rjstbhe iio qq jdh",
          "yp fkeofjpc lkhypcebox rjstbhe ewwykishv egzhne jdh y qq qq",
          "fu ql iio fkeofjpc jdh luspuy yzyoatfyx li qq v",
          "wlinrrgcm iio qq omnc sgkt tzgev iio iio qq qq", // 6
          "d vhg qlj khj wlinrrgcm qq f jp zsmhkjokmb rjstbhe"
        };
    int[] student_id =
        new int[] {
          96537918, 589204657, 765963609, 613766496, 43871615, 189209587, 239084671, 908938263
        };
    int k = 3;
    List<Integer> integers =
        new RewardTopKStudents().new Solution()
            .topStudents(positive_feedback, negative_feedback, report, student_id, k);
    Assert.assertArrayEquals(new Integer[] {239084671, 589204657, 43871615}, integers.toArray());
  }
}
