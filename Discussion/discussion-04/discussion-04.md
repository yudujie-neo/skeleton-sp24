# Discussion 04 — Comparators, Iterators / 比较器与迭代器

> UC Berkeley CS 61B, Spring 2024
> Discussion date: February 12, 2024

## Regular

### Question 1 — OHQueue / 答疑队列

Meshan is designing the new 61B Office Hours Queue. The code below for `OHRequest` represents a single request. It has a reference to the next request. `description` and `name` contain the description of the bug and name of the person on the queue, and `isSetup` marks the ticket as being a setup issue or not.

> **中文翻译：** Meshan 正在设计新的 CS 61B Office Hours Queue（答疑队列）。下面的 `OHRequest` 类表示一条请求，并保存指向下一条请求的引用。`description` 和 `name` 分别记录问题描述和队列中学生的姓名，`isSetup` 则标记该工单是否属于环境配置问题。

```java
public class OHRequest {
    public String description;
    public String name;
    public boolean isSetup;
    public OHRequest next;

    public OHRequest(String description, String name,
                     boolean isSetup, OHRequest next) {
        this.description = description;
        this.name = name;
        this.isSetup = isSetup;
        this.next = next;
    }
}
```

#### 1a

Create a class `OHIterator` that implements an `Iterator` over `OHRequest`s and only returns requests with good descriptions (using the `isGood` function). Our `OHIterator`’s constructor takes in an `OHRequest` that represents the first `OHRequest` on the queue. If we run out of office hour requests, we should throw a `NoSuchElementException` when our iterator tries to get another request, like so:

```java
throw new NoSuchElementException();
```

> **中文翻译：** 创建一个 `OHIterator` 类，实现遍历 `OHRequest` 的 `Iterator`，并且只返回具有良好描述的请求（用 `isGood` 函数判断）。`OHIterator` 构造器接收代表队首请求的 `OHRequest`。如果答疑请求已经用尽，迭代器仍尝试获取下一项，就应如上抛出 `NoSuchElementException`。

```java
public class OHIterator ____________________________________________________ {
    private OHRequest curr;

    public OHIterator(OHRequest request) {
        __________________________________________;
    }

    public static boolean isGood(String description) {
        return description.length() >= 5;
    }

    @Override
    __________________ __________________ _______________________________ {
        while (____________________________________________________________) {
            ____________________________________;
        }
        ____________________________________;
    }

    @Override
    __________________ __________________ _______________________________ {
        if (____________________________________) {
            throw __________ _______________________________________________;
        }
        ____________________________________;
        ____________________________________;
        ____________________________________;
    }
}
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```java
public class OHIterator implements Iterator<OHRequest> {
    private OHRequest curr;

    public OHIterator(OHRequest request) {
        curr = request;
    }

    public static boolean isGood(String description) {
        return description.length() >= 5;
    }

    @Override
    public boolean hasNext() {
        while (curr != null && !isGood(curr.description)) {
            curr = curr.next;
        }
        return curr != null;
    }

    @Override
    public OHRequest next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        OHRequest temp = curr;
        curr = curr.next;
        return temp;
    }
}
```

Explanation: The `OHRequest` object `queue` passed into `OHIterator`’s constructor represents the first `OHRequest` on the queue. Initializing `curr` to `queue` in the constructor allows our `OHIterator` to start at this first request. Since `OHIterator` implements an `Iterator` over `OHRequest`s, we must provide implementations for the interface methods `hasNext()` and `next()`. The `hasNext()` method handles checking whether there are more `OHRequest`s. However, we only want requests with good (as defined by `isGood`) descriptions, so we must check the descriptions of each `OHRequest` and skip over the ones with bad descriptions before determining whether there are `OHRequest`s left.

#### 解析

`OHIterator` 实现 `Iterator<OHRequest>`，因此必须提供 `hasNext()` 和 `next()`。字段 `curr` 表示下一条尚未返回的候选请求。

`hasNext()` 不只是检查 `curr != null`，还承担过滤工作：只要当前请求存在但描述不合格，就沿 `next` 指针继续前进。循环条件必须先判断 `curr != null`，利用 `&&` 的短路求值避免访问 `null.description`。跳过所有不合格请求后，`curr != null` 才表示确实还有可返回的元素。

`next()` 先调用 `hasNext()`，既检查是否还有元素，也确保 `curr` 已经移动到下一条合格请求。随后暂存当前请求、推进 `curr`，最后返回暂存值。如果没有下一项，则遵守迭代器约定抛出 `NoSuchElementException`。

常见错误是让 `next()` 返回推进后的节点，从而跳过当前元素；或者只在 `next()` 中过滤，导致 `hasNext()` 错误地声称还有元素。

#### 考点

- `Iterator<T>` 接口
- `hasNext()` 与 `next()`
- 泛型
- 链表遍历
- 短路求值
- `NoSuchElementException`

</details>

#### 1b

Define a class `OHQueue` below: we want our `OHQueue` to be `Iterable` so that we can process `OHRequest` objects with good descriptions. Our constructor takes in the first `OHRequest` object on the queue.

> **中文翻译：** 定义下面的 `OHQueue` 类。我们希望 `OHQueue` 是 `Iterable`，从而可以处理描述合格的 `OHRequest` 对象。构造器接收队列中的第一个 `OHRequest` 对象。

```java
public class OHQueue _____________________________________________________ {
    private OHRequest request;

    public OHQueue(OHRequest request) {
        ____________________________________________;
    }

    @Override
    __________________ __________________ _______________________________ {
        ____________________________________________;
    }
}
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```java
public class OHQueue implements Iterable<OHRequest> {
    private OHRequest request;

    public OHQueue(OHRequest request) {
        this.request = request;
    }

    @Override
    public Iterator<OHRequest> iterator() {
        return new OHIterator(request);
    }
}
```

Explanation: If we want our `OHQueue` to be `Iterable`, `OHQueue` has to implement the interface `Iterable`. A condition of this is implementing the methods of the interface (which in the case of `Iterable`, is the `iterator()` method). As our `OHQueue` processes `OHRequest` objects, `iterator()` in `OHQueue` should return an `OHIterator` over `OHRequest` objects.

#### 解析

要让对象能用于增强 `for` 循环，类需要实现 `Iterable<OHRequest>` 并提供 `iterator()`。每次调用 `iterator()` 都创建一个新的 `OHIterator`，使每次遍历拥有独立的游标状态；如果复用同一个迭代器，第二次遍历可能会从上一次停止的位置开始。

`OHQueue` 负责保存数据入口，`OHIterator` 负责具体遍历和过滤。两种职责分离后，队列本身不需要实现 `hasNext()` 或 `next()`。

#### 考点

- `Iterable<T>` 接口
- `iterator()` 方法
- 增强 `for` 循环
- Iterable 与 Iterator 的职责区别

</details>

#### 1c

Suppose we notice a bug in our office hours system: if a ticket’s description contains the words `"thank u"`, it is put on the queue twice. To combat this, we’d like to define a new iterator, `TYIterator`.

If the current item’s description contains the words `"thank u"`, it should skip the next item on the queue, because we know the next item is an accidental duplicate from our buggy system. As an example, if there were 4 `OHRequest` objects on the queue with descriptions `["thank u", "thank u", "im bored", "help me"]`, calls to `next()` should return the 0th, 2nd, and 3rd `OHRequest` objects on the queue.

To check if a `String s` contains the substring `"thank u"`, you can use:

```java
s.contains("thank u")
```

Hint — we’ve already enforced good descriptions with our regular `OHIterator`. Using inheritance, how can we reuse that functionality without repeating ourselves? Also, notice that `OHIterator`’s instance variables are private, so we can’t access them from subclasses of `OHIterator`.

> **中文翻译：** 假设答疑系统存在一个错误：如果工单描述包含 `"thank u"`，它就会被重复加入队列。为了解决这个问题，我们要定义新的迭代器 `TYIterator`。如果当前元素的描述包含 `"thank u"`，就跳过队列中的下一项，因为下一项是系统错误产生的重复项。例如，队列中四个请求的描述为 `["thank u", "thank u", "im bored", "help me"]` 时，多次调用 `next()` 应依次返回第 0、2、3 个请求。可以用 `s.contains("thank u")` 检查字符串是否包含该子串。提示：普通 `OHIterator` 已经实现了合格描述过滤；思考如何通过继承复用它。还要注意，`OHIterator` 的实例变量是 `private`，子类无法直接访问。

```java
public class TYIterator __________________________________________________________ {
    public TYIterator(OHRequest queue) {
        _____________________________;
    }

    @Override
    ______________ ____________ ______________ {
        OHRequest result = ______________________;
        if (__________________________________________) {
            ______________________;
        }
        return ______________________;
    }
}
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```java
public class TYIterator extends OHIterator {
    public TYIterator(OHRequest queue) {
        super(queue);
    }

    @Override
    public OHRequest next() {
        OHRequest result = super.next();
        if (result.description.contains("thank u")) {
            super.next();
        }
        return result;
    }
}
```

#### 解析

`TYIterator` 继承 `OHIterator`，从而直接复用“只返回合格描述”的逻辑。因为父类的 `curr` 是 `private`，子类不能直接推进它，但可以通过调用父类公开的 `next()` 间接完成相同操作。

第一次 `super.next()` 取得应返回的合格请求并推进父类迭代器。如果该请求描述包含 `"thank u"`，第二次 `super.next()` 取出并丢弃下一条合格请求，达到跳过重复项的效果。最后仍返回第一次取得的 `result`。

该实现依赖题目保证：包含 `"thank u"` 的请求后确实存在一个重复项。如果最后一项包含该短语但后面没有元素，第二次 `super.next()` 会抛出 `NoSuchElementException`。

#### 考点

- 实现继承
- `extends`
- `super` 构造器与方法调用
- 方法重写
- `private` 成员的访问限制
- 复用父类逻辑

</details>

#### 1d

Now assume the `OHQueue` uses a `TYIterator` as its iterator. Fill in the blanks to print only the names of tickets from the queue beginning at `s1` with good descriptions, skipping over duplicate descriptions that contain `"thank u"`. What would be printed after we run the `main` method?

> **中文翻译：** 现在假设 `OHQueue` 使用 `TYIterator` 作为迭代器。请填空，只打印从 `s1` 开始、描述合格的工单姓名，同时跳过包含 `"thank u"` 的重复描述。运行 `main` 方法后会打印什么？

```java
public static void main(String[] args) {
    OHRequest s5 = new OHRequest(
            "I deleted all of my files, thank u", "Elana", true, null);
    OHRequest s4 = new OHRequest(
            "conceptual: what is Java", "Mihir", false, s5);
    OHRequest s3 = new OHRequest(
            "git: I never did lab 1", "Kevin", true, s4);
    OHRequest s2 = new OHRequest(
            "help", "Angel", false, s3);
    OHRequest s1 = new OHRequest(
            "no I haven't tried stepping through", "Ashley", false, s2);

    OHQueue q = _______________________________________;
    for (__________________________________________________) {
        __________________________________________________;
    }
}
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```java
public static void main(String[] args) {
    OHRequest s5 = new OHRequest(
            "I deleted all of my files, thank u", "Elana", true, null);
    OHRequest s4 = new OHRequest(
            "conceptual: what is Java", "Mihir", false, s5);
    OHRequest s3 = new OHRequest(
            "git: I never did lab 1", "Kevin", true, s4);
    OHRequest s2 = new OHRequest(
            "help", "Angel", false, s3);
    OHRequest s1 = new OHRequest(
            "no I haven't tried stepping through", "Ashley", false, s2);

    OHQueue q = new OHQueue(s1);
    for (OHRequest r : q) {
        System.out.println(r.name);
    }
}
```

Overall, we print:

```text
Ashley
Kevin
Mihir
Elana
```

#### 解析

增强 `for` 循环会调用 `q.iterator()` 取得迭代器，并反复调用 `hasNext()` 与 `next()`。

- Ashley 的描述长度合格，因此返回。
- Angel 的描述是 `"help"`，长度小于 5，被 `OHIterator.hasNext()` 跳过。
- Kevin 和 Mihir 的描述均合格，依次返回。
- Elana 的描述包含 `"thank u"`。`TYIterator.next()` 先把它暂存在局部变量 `result` 中，随后尝试跳过重复项；但它已经是最后一个节点，第二次 `super.next()` 会在执行 `return result` 之前抛出 `NoSuchElementException`。因此 Elana 不会返回给增强 `for` 循环，也不会被打印。

官方 Solutions PDF 列出的预期打印结果是 Ashley、Kevin、Mihir、Elana。这里存在一个边界条件不一致：严格执行官方代码时，实际会依次打印 Ashley、Kevin、Mihir，然后抛出异常。要稳定得到官方列出的四行输出，`TYIterator` 跳过重复项前还应先确认父迭代器确实存在下一项，例如使用 `if (result.description.contains("thank u") && super.hasNext())`。这条补充属于边界分析，不是对官方答案的改写。

#### 考点

- 增强 `for` 循环
- `Iterable`/`Iterator` 协作
- 过滤迭代器
- 链表遍历顺序
- 迭代器边界条件

</details>

#### 1e

Meshan would like to find a way to prioritize setup tickets on the queue so that they appear at the top. He wants to implement this based on the `isSetup` field of each `OHRequest`, but sometimes students forget to set it to `true`, so he decides to use `description` as backup to break ties.

Fill in the `compare` method of `OHRequestComparator` below. First, if one but not both of the `OHRequest`s have their `isSetup` set to `true`, the one with `isSetup` set to `true` should take priority (i.e. earlier on the queue). If both or neither of the `OHRequest`s have their `isSetup` set to `true`, tiebreak with the `description`: the description has to exactly match `"setup"` in order to be counted as a setup issue. If both requests have such descriptions, it’s a true tie and return 0.

Hint — if `o1` is prioritized over `o2`, then `o1` is considered less than `o2`.

> **中文翻译：** Meshan 希望优先处理环境配置工单，让它们排在队列前面。他首先依据每个 `OHRequest` 的 `isSetup` 字段判断；但学生有时忘记将它设为 `true`，因此再用 `description` 打破平局。请补全 `OHRequestComparator` 的 `compare` 方法：若恰好一个请求的 `isSetup` 为 `true`，该请求优先；若两者都为 `true` 或都不为 `true`，则检查描述，只有与 `"setup"` 完全相同的描述才算配置问题。若两个描述都满足，则真正平局并返回 0。提示：若 `o1` 比 `o2` 优先，则 `o1` 被视为小于 `o2`。

```java
public class OHRequestComparator
        implements Comparator<_____________________________> {
    @Override
    public int compare(___________________________ o1,
                       ________________________ o2) {
        // use as many lines as you need
    }
}
```

> **代码注释翻译：** `use as many lines as you need` 表示“根据需要使用任意行数”。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```java
public class OHRequestComparator implements Comparator<OHRequest> {
    @Override
    public int compare(OHRequest o1, OHRequest o2) {
        boolean isO1DescSetup = o1.description.equals("setup");
        boolean isO2DescSetup = o2.description.equals("setup");
        if (o1.isSetup && !o2.isSetup) {
            return -1;
        } else if (!o1.isSetup && o2.isSetup) {
            return 1;
        } else if (isO1DescSetup && !isO2DescSetup) {
            return -1;
        } else if (!isO1DescSetup && isO2DescSetup) {
            return 1;
        }
        return 0;
    }
}
```

Official alternate:

```java
public class OHRequestComparator implements Comparator<OHRequest> {
    @Override
    public int compare(OHRequest o1, OHRequest o2) {
        if (o1.isSetup == o2.isSetup) {
            boolean isO1DescSetup = o1.description.equals("setup");
            boolean isO2DescSetup = o2.description.equals("setup");
            return Boolean.compare(isO1DescSetup, isO2DescSetup);
        }
        return Boolean.compare(o1.isSetup, o2.isSetup);
    }
}
```

Explanation: The `compare` method should return a negative integer if `s1` has higher priority than `s2`, zero if the two requests are of equal priority, and a positive integer if `s1` has lower priority than `s2`. We are given that if either (but not both) request has their `isSetUp` set to `true`, the request with the `true` value receives higher priority. The two possible combinations of this scenario are covered in the first two `if` statements. We only check `description` if both or neither requests have `isSetUp` set to `true`, so the `description` checks must come after those of `isSetUp`.

The alternate uses the same idea, but exploits the fact that `Boolean.compare` compares two booleans for us. This allows us to condense the `if`-statements. Note that we use `==` to check for equality on primitives and `.equals` to check for equality on reference types (e.g. `String`).

#### 解析

`Comparator.compare(o1, o2)` 返回负数表示 `o1` 排在 `o2` 前，返回正数表示 `o1` 排在后面，返回 0 表示两者在该排序规则下相等。

官方主解法先比较优先级更高的 `isSetup` 字段。只有这一步无法区分二者时，才检查描述是否与 `"setup"` 完全相等。使用 `.equals` 是因为 `String` 是引用类型；`==` 比较的是引用身份而不是字符串内容。

需要特别注意：PDF 中的官方备用代码被原样保留，但其 `Boolean.compare(o1Value, o2Value)` 会把 `false` 排在 `true` 前，因为 Java 规定 `false < true`。这与题目要求的“`true` 优先且视为更小”方向相反。若要满足题意，应交换参数：

```java
return Boolean.compare(isO2DescSetup, isO1DescSetup);
// 以及
return Boolean.compare(o2.isSetup, o1.isSetup);
```

这两行是对官方备用答案问题的分析与修正建议，不属于官方答案。

#### 考点

- `Comparator<T>`
- `compare` 的负数、零、正数约定
- 多级比较规则
- `String.equals`
- `Boolean.compare`
- 基本类型与引用类型的相等判断

</details>

# Exam Prep

### Question 1 — Take Us to Your “Yrnqre” / 带我们去见你们的“Yrnqre”

You’re a traveler who just landed on another planet. Luckily, the aliens there use the same alphabet as the English language, but in a different order.

> **中文翻译：** 你是一位刚刚降落在另一颗星球上的旅行者。幸运的是，那里的外星人使用与英语相同的字母，只是字母顺序不同。

Given the `AlienAlphabet` class below, fill in `AlienComparator` class so that it compares strings lexicographically, based on the order passed into the `AlienAlphabet` constructor. For simplicity, you may assume all words passed into `AlienComparator` have letters present in `order`.

For example, if the alien alphabet has the order `"dba..."`, which means that `d` is the first letter, `b` is the second letter, etc., then `AlienComparator.compare("dab", "bad")` should return a negative value, since `dab` comes before `bad`.

If one word is an exact prefix of another, the longer word comes later. For example, `"bad"` comes before `"badly"`. Hint: `indexOf` might be helpful.

> **中文翻译：** 给定下面的 `AlienAlphabet` 类，请补全 `AlienComparator`，使它按照传入 `AlienAlphabet` 构造器的字母顺序，对字符串进行字典序比较。为简化问题，可以假设传给 `AlienComparator` 的所有单词，其字母都存在于 `order` 中。例如，外星字母顺序为 `"dba..."` 时，`d` 排第一、`b` 排第二，因此 `AlienComparator.compare("dab", "bad")` 应返回负数，因为 `dab` 在 `bad` 前面。如果一个单词恰好是另一个单词的前缀，则较长的单词排在后面，例如 `"bad"` 排在 `"badly"` 前。提示：`indexOf` 可能有用。

```java
public class AlienAlphabet {
    private String order;

    public AlienAlphabet(String alphabetOrder) {
        order = alphabetOrder;
    }

    public class AlienComparator implements Comparator<____________> {
        public int compare(String word1, String word2) {
            int minLength = Math.min(_______________________,
                                     _______________________);

            for (_________________________________________________________________) {
                int char1Rank = ______________________________________________;
                int char2Rank = ______________________________________________;

                if (______________________________________________) {
                    return -1;
                } else if (______________________________________________) {
                    return 1;
                }
            }

            return _______________________________
                    - _______________________________;
        }
    }
}
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```java
public class AlienAlphabet {
    private String order;

    public AlienAlphabet(String alphabetOrder) {
        order = alphabetOrder;
    }

    public class AlienComparator implements Comparator<String> {
        public int compare(String word1, String word2) {
            int minLength = Math.min(word1.length(), word2.length());
            for (int i = 0; i < minLength; i++) {
                int char1Rank = order.indexOf(word1.charAt(i));
                int char2Rank = order.indexOf(word2.charAt(i));
                if (char1Rank < char2Rank) {
                    return -1;
                } else if (char1Rank > char2Rank) {
                    return 1;
                }
            }

            return word1.length() - word2.length();
        }
    }
}
```

#### 解析

字典序比较从左到右寻找第一对不同字符。`order.indexOf(character)` 把字符转换成外星字母表中的排名；第一处排名更小的字符决定整个单词更靠前，因此立即返回 `-1` 或 `1`。

循环只比较到较短单词的长度，避免索引越界。如果这一范围内所有字符都相同，说明两个单词相同，或者较短单词是较长单词的完整前缀。此时返回长度差：短词减长词为负数，所以短词排在前面；长度相同则返回 0。

时间复杂度并不只是 $O(n)$：`String.indexOf` 每次可能扫描整个字母表。若字母表长度为 $A$、较短单词长度为 $n$，该实现最坏为 $O(nA)$。英语字母表大小固定时通常可视为 $O(n)$。

#### 考点

- 自定义 `Comparator<String>`
- 字典序比较
- `charAt` 与 `indexOf`
- 前缀处理
- 提前返回
- 时间复杂度分析

</details>

### Question 2 — Iterator of Iterators / 迭代器的迭代器

Implement an `IteratorOfIterators` which takes in a `List` of `Iterator`s of `Integer`s as an argument. The first call to `next()` should return the first item from the first iterator in the list. The second call should return the first item from the second iterator in the list. If the list contained $n$ iterators, the $(n+1)$th time that we call `next()`, we would return the second item of the first iterator in the list.

Note that if an iterator is empty in this process, we continue to the next iterator. Then, once all the iterators are empty, `hasNext` should return `false`. For example, if we had 3 Iterators A, B, and C such that A contained the values `[1, 3, 4, 5]`, B was empty, and C contained the values `[2]`, calls to `next()` for our `IteratorOfIterators` would return `[1, 2, 3, 4, 5]`.

Hint: What can you use to store all the iterators? How would you know which iterator is next?

> **中文翻译：** 实现 `IteratorOfIterators`，它接收一个由整数迭代器组成的 `List`。第一次调用 `next()` 应返回列表中第一个迭代器的第一项；第二次调用应返回第二个迭代器的第一项。如果列表包含 $n$ 个迭代器，第 $(n+1)$ 次调用 `next()` 时，应返回第一个迭代器的第二项。过程中若某个迭代器为空，就继续处理下一个；所有迭代器都为空后，`hasNext` 应返回 `false`。例如 A 包含 `[1, 3, 4, 5]`、B 为空、C 包含 `[2]` 时，连续调用 `next()` 应得到 `[1, 2, 3, 4, 5]`。提示：可以用什么保存所有迭代器？怎样知道下一个该使用哪个迭代器？

```java
import java.util.*;

public class IteratorOfIterators ______________________________ {

    public IteratorOfIterators(List<Iterator<Integer>> a) {



    }

    @Override
    public boolean hasNext() {



    }

    @Override
    public Integer next() {



    }
}
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

[Here is a video walkthrough of the solution.](https://youtu.be/2QPNzIClYnw)

```java
public class IteratorOfIterators implements Iterator<Integer> {
    LinkedList<Iterator<Integer>> iterators;

    public IteratorOfIterators(List<Iterator<Integer>> a) {
        iterators = new LinkedList<>();
        for (Iterator<Integer> iterator : a) {
            if (iterator.hasNext()) {
                iterators.add(iterator);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !iterators.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Iterator<Integer> iterator = iterators.removeFirst();
        int ans = iterator.next();
        if (iterator.hasNext()) {
            iterators.addLast(iterator);
        }
        return ans;
    }
}
```

Explanation: In the constructor, we make sure the iterator is not empty and add it to our list of possible iterators. For `hasNext`, we make sure that there is an iterator for us to use.

For `next`, we first make sure that there is a possible next element. If so, we get the next element from the current iterator by removing the front of our list. If the iterator still has elements left, we put it back on the end of the list for future iterations.

Alternate Solution: Although this solution provides the right functionality, it is not as efficient as the first one.

```java
public class IteratorOfIterators implements Iterator<Integer> {
    LinkedList<Integer> l;

    public IteratorOfIterators(List<Iterator<Integer>> a) {
        l = new LinkedList<>();
        while (!a.isEmpty()) {
            Iterator<Integer> curr = a.remove(0);
            if (curr.hasNext()) {
                l.add(curr.next());
                a.add(curr);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !l.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return l.removeFirst();
    }
}
```

Explanation: This solution is essentially the same as the first, except we preprocess all the elements from all iterators before going into `hasNext` or `next`. This is less efficient because we may not need all these elements; for example, what if there are a million elements but our iterator is only called twice?

#### 解析

主解法把所有尚有元素的迭代器放入 `LinkedList`，把它当作队列使用。构造时过滤掉空迭代器，因此“队列非空”等价于“还有元素可返回”。

每次 `next()`：

1. 从队首取出一个迭代器。
2. 从该迭代器取出一个整数。
3. 如果它还有元素，就把它放回队尾；否则永久丢弃。

这个过程形成轮转调度（round-robin）：每个非空迭代器每轮贡献一个元素。例子中先从 A 得到 1，跳过空的 B，从 C 得到 2；C 随后耗尽，只剩 A，于是继续得到 3、4、5。

主解法是惰性的，只在调用 `next()` 时消费一个元素。设一共返回 $m$ 个整数，构造阶段检查 $k$ 个迭代器需 $O(k)$，之后每次 `next()` 的队首删除和队尾添加都是 $O(1)$。

官方备用解法在构造器中预先消费并保存全部整数。它能产生相同顺序，但会立即做 $O(m)$ 工作并占用 $O(m)$ 额外空间；如果调用者最终只取少数元素，这些预处理就被浪费了。此外，它会通过 `remove(0)` 和 `add` 修改传入的列表。

#### 考点

- 迭代器组合
- `LinkedList` 作为队列
- Round-robin 调度
- 惰性求值与预处理
- `NoSuchElementException`
- 时间和空间复杂度

</details>

---

## 完整性检查

- Regular：原题与 Solutions 均包含 Question 1 的 1a、1b、1c、1d、1e，题号一一对应。
- Exam Prep：原题与 Solutions 均包含 Question 1 和 Question 2，题号一一对应。
- 四份 PDF 均无必须提取为图片的数据结构图、树、Graph 或内存图；`assets/` 目录保留备用。
- 官方解答中的代码与本文中文补充分析严格分区；发现的边界条件或方向问题均在“解析”中明确标为非官方补充。

## 官方资料

- [Regular PDF](https://sp24.datastructur.es/assets/discussions/regular04.pdf)
- [Regular Solutions PDF](https://sp24.datastructur.es/assets/discussions/regular04sol.pdf)
- [Exam Prep PDF](https://sp24.datastructur.es/assets/discussions/examlevel04.pdf)
- [Exam Prep Solutions PDF](https://sp24.datastructur.es/assets/discussions/examlevel04sol.pdf)
