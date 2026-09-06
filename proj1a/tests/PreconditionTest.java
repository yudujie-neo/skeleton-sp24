import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.fail;

/** Tests that the LinkedListDeque61B class is structured correctly.
 *  中文：测试 LinkedListDeque61B 类的结构是否正确。
 *  @author Noah Adhikari */
public class PreconditionTest {

    /** Returns the inner class of lld. Asserts there is exactly one inner class.
     * 中文：返回 lld 的内部类，并断言它恰好只有一个内部类。 */
    private static Class<?> getLldInnerClass() {
        Class<?>[] innerClasses = LinkedListDeque61B.class.getDeclaredClasses();
        assertWithMessage("LinkedListDeque61B should have exactly one inner class / "
                + "LinkedListDeque61B 应恰好包含一个内部类").that(innerClasses).hasLength(1);
        return innerClasses[0];
    }

    /** Returns a stream of all fields in c that are not primitives, synthetic, generic (Object),
     *  or of type nodeClass.
     * 中文：返回 c 中所有不符合要求的字段流；允许基本类型、合成字段、泛型值
     * （Object）以及 nodeClass 类型字段。 */
    private static Stream<Field> getBadFields(Class<?> c, Class<?> nodeClass) {
        return Reflection.getFields(c)
                .filter(f -> !(f.getType().isPrimitive()
                        || f.getType().equals(nodeClass)
                        || f.isSynthetic()
                        || f.getType().equals(Object.class)));
    }

    @Test
    @Order(0)
    @DisplayName("LinkedListDeque61B is structured and generified properly / 结构与泛型正确")
    public void genericTest() {
        Class<?> lldClass = LinkedListDeque61B.class;
        int lldNumParams = lldClass.getTypeParameters().length;
        assertWithMessage("LinkedListDeque61B should be generified with one type parameter / "
                + "LinkedListDeque61B 应使用一个泛型参数")
                .that(lldNumParams).isEqualTo(1);
        Class<?>[] innerClasses = lldClass.getDeclaredClasses();
        assertWithMessage("LinkedListDeque61B should have exactly one inner class / "
                + "LinkedListDeque61B 应恰好包含一个内部类").that(innerClasses).hasLength(1);
        Class<?> nodeClass = innerClasses[0];
        assertWithMessage("Inner class of LinkedListDeque61B should not be generic. " +
                "(Use the generic type from the outer class?) / 内部类不应另行声明泛型，"
                + "请使用外部类的泛型参数")
                .that(nodeClass.getTypeParameters()).isEmpty();

        // Convoluted check that value field of node is actually generic instead of Object
        // 中文：进一步检查节点的值字段确实使用泛型，而不是直接声明为 Object。
        LinkedListDeque61B<Integer> lld = new LinkedListDeque61B<>();
        Field[] fields = lld.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.getType().equals(nodeClass)) {
                try {
                    f.setAccessible(true);
                    Object node = f.get(lld);
                    for (Field innerField : node.getClass().getDeclaredFields())
                        if (innerField.getType().equals(Object.class)) { // value field / 中文：值字段
                            innerField.setAccessible(true);
                            assertWithMessage("Value field of node should be generic / 节点的值字段应使用泛型")
                                    .that(innerField.getGenericType())
                                    .isNotEqualTo(Object.class);
                        }
                } catch (IllegalAccessException e) {
                    fail(e.getMessage());
                }
            }
        }
    }

    @Test
    @Order(1)
    @DisplayName("LinkedListDeque61B follows a strict doubly-linked topology / 严格双向链接结构")
    public void doublyLinkedTest() {
        Class<?> nodeClass = getLldInnerClass();
        Map<Class<?>, Integer> typeCounts = new TreeMap<>(Comparator.comparing(Class::getSimpleName));
        for (Field f : nodeClass.getDeclaredFields()) {
            if (!f.isSynthetic()) {
                typeCounts.merge(f.getType(), 1, Integer::sum);
            }
        }
        assertWithMessage("Node class does not contain exactly two fields of type Node / "
                + "节点类没有恰好两个 Node 类型字段")
                .that(typeCounts.get(nodeClass)).isEqualTo(2);
        assertWithMessage("Node class does not contain exactly one generic value field / "
                + "节点类没有恰好一个泛型值字段")
                .that(typeCounts.get(Object.class)).isEqualTo(1);
    }

    @Test
    @Order(2)
    @DisplayName("LinkedListDeque61B has no fields besides nodes and primitives / 仅含节点和基本类型字段")
    public void noNonTrivialFieldsTest() {
        Class<?> nodeClass = getLldInnerClass();
        Stream<Field> badLldFields = getBadFields(LinkedListDeque61B.class, nodeClass);
        Stream<Field> badNodeFields = getBadFields(nodeClass, nodeClass);
        List<Field> badFields = Stream.concat(badLldFields, badNodeFields).toList();


        String msg = badFields.stream()
                .map(f -> f.getType().getSimpleName() + " " + f.getName())
                .reduce("", (a, b) -> a + "\n\t" + b);

        assertWithMessage("Found fields that are not nodes or primitives, or contain fields that are not nodes or " +
                "primitives / 发现不是节点或基本类型的字段：" + msg).that(badFields).isEmpty();
    }

    @Test
    @Order(3)
    @DisplayName("LinkedListDeque61B has only an empty constructor / 仅有无参数构造方法")
    public void noNonTrivialConstructorsTest() {
        Constructor<?>[] ctors = LinkedListDeque61B.class.getConstructors();
        assertWithMessage("Found more than one constructor in LinkedListDeque61B / 发现多个构造方法")
                .that(ctors).hasLength(1);
        assertWithMessage("LinkedListDeque61B constructor has more than zero arguments / 构造方法不应有参数")
                .that(ctors[0].getParameterCount()).isEqualTo(0);
    }
}
