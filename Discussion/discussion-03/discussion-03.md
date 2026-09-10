# Discussion 03 — Inheritance / 继承

> UC Berkeley CS 61B, Spring 2024
> Regular date: February 5, 2024
> Exam-Level sheet date printed in the PDF: February 6, 2023

## Regular

### Question 1 — It’s a Bird! It’s a Plane! It’s a CatBus! / 是鸟！是飞机！是猫巴士！

On a research expedition studying air traffic, we discovered a new species: the Flying Interfacing CatBus, which acts like a vehicle and has the ability to honk (safety is important!).

> **中文翻译：** 在一次研究空中交通的考察中，我们发现了一个新物种：会飞且实现接口的猫巴士（Flying Interfacing CatBus）。它既能像交通工具一样行动，也会鸣笛（安全很重要！）。

#### 1a

Given the `Vehicle` and `Honker` interfaces, fill out the `CatBus` class so that CatBuses can rev their engines and honk at other CatBuses with a CatBus-specific honk.

> **中文翻译：** 给定 `Vehicle` 和 `Honker` 接口，请补全 `CatBus` 类，使猫巴士既能发动引擎，也能用猫巴士特有的鸣笛声向其他猫巴士鸣笛。

```java
interface Vehicle {
    public void revEngine();
}

interface Honker {
    public void honk();
}

public class CatBus ______________________ ________________, ________________ {
    @Override
    ___________ __________ _______________ {
        /* CatBus revs engine, implementation hidden */
    }

    @Override
    ___________ __________ _______________ {
        /* CatBus honks, implementation hidden */
    }

    /** Allows CatBus to honk at other CatBuses. */
    public void conversation(CatBus target) {
        honk();
        target.honk();
    }
}
```

> **代码注释翻译：** `CatBus revs engine, implementation hidden` 表示“猫巴士发动引擎，具体实现已隐藏”；`CatBus honks, implementation hidden` 表示“猫巴士鸣笛，具体实现已隐藏”；`Allows CatBus to honk at other CatBuses` 表示“允许猫巴士向其他猫巴士鸣笛”。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```java
interface Vehicle {
    public void revEngine();
}

interface Honker {
    public void honk();
}

public class CatBus implements Vehicle, Honker {
    @Override
    public void revEngine() {
        // CatBus revs its engine, implementation not shown
    }

    @Override
    public void honk() {
        // CatBus honks, implementation not shown
    }

    /** Allows CatBus to honk at other CatBuses. */
    public void conversation(CatBus target) {
        honk();
        target.honk();
    }
}
```

#### 解析

`CatBus` 同时满足两份接口契约，所以类声明使用 `implements Vehicle, Honker`。接口中的两个抽象方法都是 `public void`，实现时方法签名必须与之匹配；特别是实现接口方法时不能降低访问权限，因此必须写 `public`。

`@Override` 让编译器检查下面的方法是否确实实现了父类型中声明的方法。如果方法名、参数或返回类型写错，编译器会直接报告问题。

#### 考点

- Java interface
- `implements`
- 方法重写与 `@Override`
- 接口方法的访问权限

</details>

#### 1b

It’s a lovely morning in the skies and we’ve encountered a horrible `Goose`, which also implements `Honker` (it has a knife in its beak!). Modify the `conversation` method signature so that CatBuses can honk at both `CatBus` and `Goose` objects while only having one argument, `target`.

> **中文翻译：** 天空中的早晨十分美好，但我们遇到了一只可怕的 `Goose`，它也实现了 `Honker` 接口（它的嘴里还叼着一把刀！）。请修改 `conversation` 方法的签名，使猫巴士只用一个参数 `target`，就能同时向 `CatBus` 和 `Goose` 对象鸣笛。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

We can change the method signature so that the type of the parameter `target` is `Honker` (both `CatBus` and `Goose` implement `Honker`):

```java
/** Allows CatBus to honk at other both CatBuses and Gooses. */
public void conversation(Honker target) {
    honk();
    target.honk();
}
```

#### 解析

`CatBus` 与 `Goose` 没有必要互相继承，但它们都实现了 `Honker`。把参数的静态类型从具体类 `CatBus` 提升为共同接口 `Honker` 后，两个类的对象都可以作为实参传入。

方法体只需要调用 `target.honk()`，而这个能力已经由 `Honker` 接口保证。这里体现了“面向接口编程”：参数只要求调用方真正需要的最小能力，因此代码更通用。

#### 考点

- 接口作为参数类型
- 子类型多态
- 静态类型决定可调用的方法
- 面向接口编程

</details>

#### 1c

Assume that we have another class, `CanadaGoose`, which extends `Goose`. Which of the following lines compile?

> **中文翻译：** 假设还有一个继承 `Goose` 的类 `CanadaGoose`。下面哪些代码行可以通过编译？

```java
Honker cb = new CatBus();
CatBus g = new Goose();
Honker h = new Honker();
CanadaGoose cg = new Goose();
Honker hcg = new CanadaGoose();
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```java
Honker cb = new CatBus();        // Compiles - a CatBus is a kind of Honker
CatBus g = new Goose();          // Errors - a Goose is not a CatBus, even though they are both Honkers
                                 // ("siblings" in the inheritance tree)
Honker h = new Honker();         // Errors - cannot instantiate an interface
CanadaGoose cg = new Goose();    // Errors - a CanadaGoose is a Goose,
                                 // but not necessarily the other way around
Honker hcg = new CanadaGoose();  // Compiles - a CanadaGoose is a kind of Honker
```

#### 解析

- `Honker cb = new CatBus();`：可以编译。`CatBus` 实现了 `Honker`，把子类型引用赋给父类型变量是安全的向上转型。
- `CatBus g = new Goose();`：不能编译。两者虽然都实现 `Honker`，但同属一个接口的不同实现类不互为子类型。
- `Honker h = new Honker();`：不能编译。接口不能被直接实例化。
- `CanadaGoose cg = new Goose();`：不能编译。每个 `CanadaGoose` 都是 `Goose`，但并非每个 `Goose` 都是 `CanadaGoose`。
- `Honker hcg = new CanadaGoose();`：可以编译。`CanadaGoose` 继承 `Goose`，而 `Goose` 实现 `Honker`，因此 `CanadaGoose` 也是 `Honker`。

常见错误是只看到两个类拥有相同接口，就误以为它们可以彼此赋值。赋值是否合法取决于右侧对象的类型是否是左侧变量类型的子类型。

#### 考点

- is-a 关系
- 向上转型
- 接口不能实例化
- 继承关系的传递性
- 兄弟类型不能直接互相赋值

</details>

### Question 2 — Raining Cats and Dogs / 猫狗齐下

#### 2a

What would Java do after executing the `main` method in the `TestAnimal` class? Fill in the table provided with the method saved at compile time, the method called at runtime, and overall output for lines 8–19 if applicable. If there is an error, write whether it is a runtime error or compile time error, and then proceed through the rest of the code as if the erroneous line were not there.

> **中文翻译：** 执行 `TestAnimal` 类中的 `main` 方法时，Java 会怎么做？请在所给表格中填写第 8–19 行在编译期保存的方法、运行时实际调用的方法，以及适用时的总体输出。如果发生错误，请注明它是运行时错误还是编译时错误；之后假设出错的那一行不存在，继续分析其余代码。

```java
public class Animal {
    public String name, noise;

    public Animal(String name) {
        this.name = name;
        this.noise = "Huh?";
    }

    public void greet(Animal a) {
        System.out.println("Hi " + a.name + ", I'm " + name);
    }

    public void play() {
        System.out.println("I love to play! " + noise);
    }

    public static void sleep() {
        System.out.println("Naptime!");
    }
}

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
        this.noise = "Meow!";
    }

    public void greet(Animal a) {
        System.out.println("Cat " + name + " says: " + noise);
    }

    public void play() {
        System.out.println("Woo it is so much fun being a cat! " + noise);
    }
}

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
        noise = "Woof!";
    }

    public void greet(Animal a) {
        System.out.println("Dog " + name + " says: " + noise);
    }

    public void play() {
        System.out.println("Woo it is so much fun being a dog! " + noise);
    }

    public static void sleep() {
        System.out.println("I love napping!");
    }
}
```

```java
public class TestAnimal {
    public static void main(String[] args) {
        Animal a = new Dog("Pluto");
        Animal b = new Animal("Bear");
        Cat c = new Cat("Garfield");
        Dog d = new Dog("Lucky");

        Cat e = new Animal("Kitty");                 // line 8
        a.greet(c);                                  // line 9
        a.sleep();                                   // line 10
        c.play();                                    // line 11
        c.greet(d);                                  // line 12
        ((Animal) c).greet(d);                       // line 13
        d.sleep();                                   // line 14
        a = c;                                       // line 15
        a.play(14);                                  // line 16
        ((Cat) b).play();                            // line 17
        d = (Dog) a;                                 // line 18
        c = a;                                       // line 19
    }
}
```

| Line | Compile time (static) | Runtime (dynamic) | Output |
|---:|---|---|---|
| 8 |  |  |  |
| 9 |  |  |  |
| 10 |  |  |  |
| 11 |  |  |  |
| 12 |  |  |  |
| 13 |  |  |  |
| 14 |  |  |  |
| 15 |  |  |  |
| 16 |  |  |  |
| 17 |  |  |  |
| 18 |  |  |  |
| 19 |  |  |  |

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

| Line | Compile time (static) | Runtime (dynamic) | Output |
|---:|---|---|---|
| 8 | Error: an `Animal` is not necessarily a `Cat` | N/A | Compiler error |
| 9 | `Animal`’s `greet(Animal)` | `Dog`’s `greet(Animal)` | `Dog Pluto says: Woof!` |
| 10 | `Animal`’s `sleep()` | N/A - `sleep()` is static! | `Naptime!` |
| 11 | `Cat`’s `play()` | `Cat`’s `play()` | `Woo it is so much fun being a cat! Meow!` |
| 12 | `Cat`’s `greet(Animal)` | `Cat`’s `greet(Animal)` | `Cat Garfield says: Meow!` |
| 13 | `Animal`’s `greet(Animal)` | `Cat`’s `greet(Animal)` | `Cat Garfield says: Meow!` |
| 14 | `Dog`’s `sleep()` | N/A - `sleep()` is static! | `I love napping!` |
| 15 | Works because a `Cat` is-an `Animal` | Works because a `Cat` is-an `Animal` | OK - nothing is printed |
| 16 | Error: `Animal` does not define `play(int)` | N/A | Compiler error |
| 17 | `Cat`’s `play()` (cast works here because `Animal` could be a `Cat`) | Error: an `Animal` is not necessarily a `Cat` | Runtime error |
| 18 | Works because `Animal` could be a `Dog` | Error: a `Cat` is not a `Dog` (can’t cast between siblings) | Runtime error |
| 19 | Error: `c` is static type `Cat` but `a` is static type `Animal` | N/A | Compiler error |

#### 解析

先区分两个概念：变量的**静态类型**由声明决定并供编译器检查；对象的**动态类型**由 `new` 出来的实际对象决定。普通实例方法先在编译期根据静态类型检查，再在运行期根据动态类型进行动态方法选择；`static` 方法则不参与动态方法选择。

- **Line 8**：左侧需要 `Cat`，右侧却是一个实际的 `Animal`，不存在安全的向下赋值，因此编译错误。
- **Line 9**：`a` 的静态类型是 `Animal`，所以编译期找到 `Animal.greet(Animal)`；它实际指向 `Dog("Pluto")`，运行时调用覆盖后的 `Dog.greet`。
- **Line 10**：`sleep` 是静态方法，绑定到表达式的静态类型 `Animal`，所以输出 `Naptime!`。
- **Line 11–12**：`c` 的静态和动态类型都是 `Cat`，因此都调用 `Cat` 的实现。
- **Line 13**：强制转换只临时改变表达式的静态类型，并不会把实际的 `Cat` 对象变成 `Animal` 对象。编译期从 `Animal` 找到方法，运行期仍动态分派到 `Cat.greet`。
- **Line 14**：`d` 的静态类型是 `Dog`，静态方法选择 `Dog.sleep`。
- **Line 15**：把 `Cat` 赋给 `Animal` 变量是安全的向上转型。此后 `a` 的静态类型仍是 `Animal`，动态类型变成 `Cat`。
- **Line 16**：编译器只看 `a` 的静态类型；`Animal` 没有 `play(int)`，所以在运行前就失败。
- **Line 17**：从 `Animal` 到 `Cat` 的转换在类型关系上可能成立，因此能编译；但 `b` 实际指向纯 `Animal`，运行时转换失败。
- **Line 18**：在 Line 15 后 `a` 实际指向 `Cat`。编译器允许尝试把 `Animal` 转为 `Dog`，但运行时发现对象是 `Cat`，抛出类型转换异常。
- **Line 19**：即使 `a` 此时动态类型是 `Cat`，编译器只知道它的静态类型是 `Animal`，不能直接赋给 `Cat` 变量。

容易犯的错误包括：认为强制转换会改变对象本身、认为所有方法都动态绑定、以及用动态类型解释编译错误。

#### 考点

- 静态类型与动态类型
- 编译期方法检查
- 动态方法选择
- 静态方法隐藏
- 向上转型、向下转型与运行时错误
- 编译错误和运行时错误

</details>

#### 2b

Spoiler alert! There is an error on the last line, line 19. How could we fix this error?

> **中文翻译：** 剧透警告！最后一行，也就是第 19 行存在错误。我们应该怎样修复这个错误？

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

The compilation error on line 19 is because we are trying set `c`, which is of static type `Cat`, to be equal to `a`, when the static type of `a` is `Animal`. Even though at runtime, `a` really does have dynamic type `Cat`, the compiler only sees static types so it doesn’t believe that this assignment is valid. The compiler only sees that we are trying to set a `Cat` variable to point to an `Animal`, and an `Animal` isn’t a `Cat`!

We could fix this error by casting `a` to be a `Cat`, such that the line reads:

```java
c = (Cat) a;
```

This would be a valid cast, as the compiler agrees that a variable of static type `Animal` could potentially hold a `Cat`, and so our request is feasible. Because the cast works, then the assignment is also now valid because a variable of static type `Cat` can be told to point to the same thing as another variable of (temporary) static type `Cat`. At runtime, this line will be fine because we were telling the truth: `a` really is a `Cat` dynamically!

#### 解析

Line 15 执行 `a = c` 后，`a` 的动态类型确实是 `Cat`，但变量声明没有改变，它的静态类型仍是 `Animal`。赋值给静态类型为 `Cat` 的变量前，需要用 `(Cat)` 向编译器说明要进行向下转型。

这个转换在本题中运行安全，因为 `a` 实际指向的就是 `Cat`。如果 `a` 实际指向纯 `Animal` 或其他非 `Cat` 子类，同样的代码会在运行时抛出 `ClassCastException`。

#### 考点

- 显式向下转型
- 静态类型不会因赋值而改变
- `ClassCastException`

</details>

# Exam Prep

### Question 1 — Forget It, We Ball / 别管了，我们打球吧

The 61Ballers are organizing the best IM team at Cal, but they first need your help with some inheritance issues...

> **中文翻译：** 61Ballers 正在组建加州大学伯克利分校最强的校内联赛（IM）队伍，但他们首先需要你帮助解决一些继承问题……

Suppose we have the `Person` interface and the `Athlete`, and `SoccerPlayer` classes defined below.

> **中文翻译：** 假设我们有如下定义的 `Person` 接口，以及 `Athlete` 和 `SoccerPlayer` 类。

```java
interface Person {
    void speakTo(Person other);
    default void watch(Athlete other) {
        System.out.println("wow");
    }
}

public class Athlete implements Person {
    @Override
    public void speakTo(Person other) {
        System.out.println("i love sports");
    }

    @Override
    public void watch(Athlete other) {
        System.out.println("ball is life");
    }
}

public class SoccerPlayer extends Athlete {
    @Override
    void speakTo(Person other) {
        System.out.println("join 61ballers");
    }
}
```

> [!NOTE]
> 原 PDF 的 `SoccerPlayer.speakTo` 确实未写 `public`。严格按 Java 规则，这会因降低访问权限而无法编译；下方官方答案显然按它是一个有效 override 的出题意图继续分析。原题在这里保持不变。

Read the code below and fill in the table.

> **中文翻译：** 阅读下面的代码并填写表格。

For lines 1–11, write down the static type of the object being created in the “Compile Time (Static)” column, the dynamic type in the “Runtime (Dynamic)” column. For the output, write nothing if there are no errors, write CE if there’s a compiler error, and write RE if there’s a runtime error.

> **中文翻译：** 对于第 1–11 行，在“Compile Time (Static)”栏中写出所创建对象对应变量的静态类型，在“Runtime (Dynamic)”栏中写出动态类型。若没有错误，输出栏留空；若有编译错误，填写 CE；若有运行时错误，填写 RE。

For lines 13–25, identify the method that’s been saved during compile time, and write down its name and the class it belongs to in the “Compile Time (Static)” column. Identify the method executed at runtime, and write down its information in the “Runtime (Dynamic)” column. Write output in the “Output” column, if anything. Write CE if there is a compiler error and RE if there is a runtime error. If a line errors, continue executing the rest of the lines.

> **中文翻译：** 对于第 13–25 行，确定编译期选中的方法，并在“Compile Time (Static)”栏中写出方法名及其所属的类；然后确定运行时实际执行的方法，并在“Runtime (Dynamic)”栏中填写相应信息。如果产生输出，就写在“Output”栏中。编译错误记为 CE，运行时错误记为 RE。即使某一行出错，也继续分析后面的代码。

```java
Person ayati = new Person();                       // line 1

Athlete aniruth = new SoccerPlayer();             // line 3

SoccerPlayer vanessa = aniruth;                   // line 5

Person eric = new Athlete();                       // line 7

Athlete shreyas = new Athlete();                  // line 9

SoccerPlayer yaofu = new SoccerPlayer();          // line 11

eric.watch(aniruth);                              // line 13

shreyas.speakTo(yaofu);                           // line 15

yaofu.speakTo(eric);                              // line 17

((Athlete) yaofu).speakTo(eric);                  // line 19

((Person) yaofu).speakTo(eric);                   // line 21

((Athlete) eric).speakTo(shreyas);                // line 23

((SoccerPlayer) eric).watch(yaofu);               // line 25
```

| Line | Compile Time (Static) | Runtime (Dynamic) | Output |
|---:|---|---|---|
| 1 |  |  |  |
| 3 |  |  |  |
| 5 |  |  |  |
| 7 |  |  |  |
| 9 |  |  |  |
| 11 |  |  |  |
| 13 |  |  |  |
| 15 |  |  |  |
| 17 |  |  |  |
| 19 |  |  |  |
| 21 |  |  |  |
| 23 |  |  |  |
| 25 |  |  |  |

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

| Line | Compile Time (Static) | Runtime (Dynamic) | Output |
|---:|---|---|---|
| 1 | CE | n/a | CE |
| 3 | `Athlete` | `SoccerPlayer` | n/a |
| 5 | CE | n/a | CE |
| 7 | `Person` | `Athlete` | n/a |
| 9 | `Athlete` | `Athlete` | n/a |
| 11 | `SoccerPlayer` | `SoccerPlayer` | n/a |
| 13 | `Person.watch` | `Athlete.watch` | `ball is life` |
| 15 | `Athlete.speakTo` | `Athlete.speakTo` | `i love sports` |
| 17 | `SoccerPlayer.speakTo` | `SoccerPlayer.speakTo` | `join 61ballers` |
| 19 | `Athlete.speakTo` | `SoccerPlayer.speakTo` | `join 61ballers` |
| 21 | `Person.speakTo` | `SoccerPlayer.speakTo` | `join 61ballers` |
| 23 | `Athlete.speakTo` | `Athlete.speakTo` | `i love sports` |
| 25 | `SoccerPlayer.watch` | RE | RE |

#### 解析

这道题同时考察接口、继承、动态方法选择和强制转换。以下解释遵循官方答案的出题意图，即把 `SoccerPlayer.speakTo` 当作有效的重写方法。

- **Line 1**：`Person` 是接口，不能 `new Person()`，所以是 CE。
- **Line 3**：`SoccerPlayer` 是 `Athlete` 的子类，可以赋给 `Athlete` 变量；静态类型为 `Athlete`，动态类型为 `SoccerPlayer`。
- **Line 5**：`aniruth` 的静态类型是 `Athlete`，不能在没有显式转换的情况下赋给 `SoccerPlayer` 变量。编译器不会仅凭它当前的动态类型批准赋值。
- **Lines 7、9、11**：左右类型相容，分别得到表中的静态与动态类型。
- **Line 13**：`eric` 的静态类型是 `Person`，编译期找到接口默认方法 `Person.watch(Athlete)`；动态对象是 `Athlete`，所以运行时调用 `Athlete.watch`。
- **Line 15**：`shreyas` 实际就是 `Athlete`，因此编译期和运行期都选择 `Athlete.speakTo`。
- **Line 17**：`yaofu` 的静态和动态类型都是 `SoccerPlayer`，调用其覆盖版本。
- **Line 19**：转换使表达式的静态类型暂时成为 `Athlete`，但对象仍是 `SoccerPlayer`；运行时仍调用 `SoccerPlayer.speakTo`。
- **Line 21**：静态类型暂时是 `Person`，编译器从接口中确认方法存在；动态分派仍到 `SoccerPlayer`。
- **Line 23**：`eric` 实际指向 `Athlete`，转为 `Athlete` 成功，并调用 `Athlete.speakTo`。
- **Line 25**：编译器允许尝试把 `Person` 引用转换为 `SoccerPlayer`，但 `eric` 实际是 `Athlete` 而不是 `SoccerPlayer`，转换在运行时失败，方法不会被调用。

容易犯的错误是认为显式转换会改变对象的动态类型。转换只改变编译器看待该表达式的静态类型，并在运行时增加一次类型检查。

#### 考点

- 接口与默认方法
- 静态类型、动态类型
- 动态方法选择
- 向上转型与向下转型
- 编译错误（CE）与运行时错误（RE）

</details>

### Question 2 — List Inheritance / 链表继承

Modify the code below so that the `max` method of `DMSList` works properly. Assume all numbers inserted into `DMSList` are positive, and we only insert using `insertFront`. You may not change anything in the given code. You may only fill in blanks. You may not need all blanks. (Spring ’16, MT1)

> **中文翻译：** 修改下面的代码，使 `DMSList` 的 `max` 方法能够正确工作。假设插入 `DMSList` 的所有数字均为正数，而且只通过 `insertFront` 插入。不得修改任何给定代码，只能填写空白处，并且不一定需要使用所有空白。（Spring ’16，期中考试 1）

```java
public class DMSList {
    private IntNode sentinel;

    public DMSList() {
        sentinel = new IntNode(-1000, _____________________);
    }

    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }

        public int max() {
            return Math.max(item, next.max());
        }
    }

    class LastIntNode extends IntNode {
        public LastIntNode() {
            ___________________________________________________________________;
        }

        @Override
        public int max() {
            ___________________________________________________________________;
        }
    }

    /* Returns 0 if list is empty. Otherwise, returns the max element. */
    public int max() {
        return sentinel.next.max();
    }

    public void insertFront(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
    }
}
```

> **代码注释翻译：** `Returns 0 if list is empty. Otherwise, returns the max element.` 表示“如果链表为空则返回 0，否则返回最大元素”。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

[Here is a video walkthrough of the solution.](https://youtu.be/0htcCcYbTws)

```java
public class DMSList {
    private IntNode sentinel;

    public DMSList() {
        sentinel = new IntNode(-1000, new LastIntNode());
    }

    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }

        public int max() {
            return Math.max(item, next.max());
        }
    }

    public class LastIntNode extends IntNode {
        public LastIntNode() {
            super(0, null);
        }

        @Override
        public int max() {
            return 0;
        }
    }

    /* Returns 0 if list is empty. Otherwise, returns the max element. */
    public int max() {
        return sentinel.next.max();
    }

    public void insertFront(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
    }
}
```

#### 解析

普通 `IntNode.max()` 使用递归：当前节点的最大值等于 `item` 与后继链表最大值中的较大者。递归必须有终止点，否则最后一个普通节点会在 `next` 为 `null` 时继续调用 `next.max()`，产生 `NullPointerException`。

这里使用一个特殊的末尾节点 `LastIntNode` 作为递归基例：

1. 构造列表时，哨兵的 `next` 指向 `new LastIntNode()`，所以即使列表为空，`sentinel.next.max()` 仍然安全。
2. `LastIntNode` 继承 `IntNode`，构造器必须先调用父类构造器，因此填写 `super(0, null)`。
3. `LastIntNode.max()` 覆盖递归版本并直接返回 `0`，递归到此结束。

题目保证插入的数都是正数，所以 `0` 是合适的基准值：空表返回 `0`，非空表中任意实际元素都不会被这个基准错误地压过。若允许负数，固定返回 `0` 就可能给出错误结果，需要更换设计，例如使用负无穷或单独处理空表。

**补充分析（非官方答案）：** 原题明确要求只能填写空白、不得修改给定代码；原题声明是 `class LastIntNode extends IntNode`，但 Solutions PDF 将其写成了 `public class LastIntNode extends IntNode`。上方“官方答案”忠实保留 Solutions 的 `public`，不过这个访问修饰符并不是完成题目所必需的，而且严格来说超出了“只填空”的限制。

#### 考点

- 递归基例
- 哨兵节点
- 继承与方法重写
- `super` 构造器调用
- 动态方法选择
- 特殊节点（sentinel/sentinel-like node）设计

</details>

---

## 完整性检查

- Regular：原题与 Solutions 均包含 Question 1（1a、1b、1c）和 Question 2（2a、2b），题号一一对应。
- Exam Prep：原题与 Solutions 均包含 Question 1 和 Question 2，题号一一对应。
- 本 Discussion 无需从 PDF 提取图示；`assets/` 目录保留给后续可能需要的原图。

## 官方资料

- [Regular PDF](https://sp24.datastructur.es/assets/discussions/regular03.pdf)
- [Regular Solutions PDF](https://sp24.datastructur.es/assets/discussions/regular03sol.pdf)
- [Exam Prep PDF](https://sp24.datastructur.es/assets/discussions/examlevel03.pdf)
- [Exam Prep Solutions PDF](https://sp24.datastructur.es/assets/discussions/examlevel03sol.pdf)
