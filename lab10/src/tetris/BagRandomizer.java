package tetris;

import java.util.ArrayList;
import java.util.Random;

/**
 *  A Tetris-accurate Tetromino randomizer.
 *  This prevents the same Tetromino shape from appearing multiple times in succession.
 *  中文：符合俄罗斯方块规则的方块随机器，用于避免同一形状连续多次出现。
 *
 *  @author Erik Nelson
 */

public class BagRandomizer {

  private Random random;

  // A list of current values in the "bag".
  // 中文：“袋子”中当前的值列表。
  ArrayList<Integer> values;

  // The total capacity of the bag.
  // 中文：袋子的总容量。
  int capacity;

  public BagRandomizer(Random r, int n) {
    this.random = r;
    this.capacity = n;

    refillValues();
  }

  /**
   * Resets the values of the bag to contain integers from 0 (inclusive) to capacity (exclusive).
   * 中文：重置袋子，使其包含从 0（含）到 capacity（不含）的整数。
   */
  private void refillValues() {
    ArrayList<Integer> newValues = new ArrayList<>();
    for (int i = 0; i < this.capacity; i++) {
      newValues.add(i);
    }
    values = newValues;
  }

  /**
   * Grabs and removes a random item from the bag. If the bag is empty, refill it.
   * 中文：从袋子中随机取出并删除一项；如果袋子为空，先重新填充。
   * @return the removed integer
   */
  public int getValue() {
    if (values.isEmpty()) {
      refillValues();
    }

    int randomIndex = random.nextInt(values.size());
    int randomValue = values.get(randomIndex);

    values.remove(randomIndex);
    return randomValue;
  }
}
