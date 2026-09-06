import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     中文：此结构测试要求 ArrayDeque61B 除底层数组、基本类型字段和合成字段外，
//     不能包含其他字段。
//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives / 仅含底层数组和基本类型字段")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives / 发现非数组或基本类型字段")
//                 .that(badFields).isEmpty();
//     }

}
