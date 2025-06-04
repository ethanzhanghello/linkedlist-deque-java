import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

//     Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    public void testIsEmpty() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();
         assertThat(lld1.isEmpty());
    }

    @Test
    public void testSizeTwo() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("first");
        lld1.addLast("second");
        assertThat(lld1.toList()).containsExactly("first", "second").inOrder();
    }

    @Test
    public void testSizeFourAndIsEmpty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("second");
        lld1.addLast("third");
        lld1.addFirst("first");
        lld1.addLast("fourth");
        assertThat(lld1.isEmpty());
        assertThat(lld1.toList()).containsExactly("first", "second", "third", "fourth").inOrder();
    }

    @Test
    public void testGet2() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        l.addLast(0);
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        assertThat(l.get(2)).isEqualTo(2);
    }

    @Test
    public void testGetAll() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        for (int i = 0; i < 1000; i ++) {
            l.addLast(i);
        }
        for (int i = 0; i < l.size(); i++) {
            assertThat(l.get(i)).isEqualTo(i);
        }
    }

    @Test
    public void testGetBig() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        assertThat(l.get(1000)).isEqualTo(null);
    }

    @Test
    public void testGetNegIndex() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        l.addFirst(1);
        assertThat(l.get(-1)).isEqualTo(null);
    }

    @Test
    public void testGetRecursive2() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        l.addLast(0);
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        assertThat(l.getRecursive(2)).isEqualTo(2);
    }

    @Test
    public void testGetRecursiveAll() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        for (int i = 0; i < 1000; i ++) {
            l.addLast(i);
        }
        for (int i = 0; i < l.size(); i++) {
            assertThat(l.getRecursive(i)).isEqualTo(i);
        }
    }

    @Test
    public void testGetRecursiveBig() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        assertThat(l.getRecursive(1000)).isEqualTo(null);
    }

    @Test
    public void testGetRecursiveNegIndex() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        l.addFirst(1);
        assertThat(l.getRecursive(-1)).isEqualTo(null);
    }

    @Test
    public void testRemoveFirstAndReturnLastValue() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        int x = l.removeFirst();
        assertThat(l.toList()).containsExactly(2, 3);
        assertThat(x).isEqualTo(1);
    }

    @Test
    public void testRemoveLastAndReturnLastValue() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        int x = l.removeLast();
        assertThat(l.toList()).containsExactly(1, 2);
        assertThat(x).isEqualTo(3);
    }

    @Test
    public void testRemoveFirstAndRemoveLast() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        l.removeFirst();
        l.removeLast();
        assertThat(l.toList()).containsExactly(2);
    }

    @Test
    public void testAddThenRemoveThenAddFirst() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(0);
        lld1.addFirst(1);
        lld1.removeLast();
        lld1.removeLast();
        lld1.addFirst(1);
        assertThat(lld1.toList()).containsExactly(1);
    }

    @Test
    public void testAddThenRemoveThenAddLast() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.removeFirst();
        lld1.removeLast();
        lld1.addLast(1);
        assertThat(lld1.toList()).containsExactly(1);
    }

    @Test
    public void testRemoveFirstWhenEmpty() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        l.removeFirst();
        l.removeFirst();
        int x = l.removeFirst();
        assertThat(l.toList()).containsExactly();
        assertThat(x).isEqualTo(3);
    }

    @Test
    public void testRemoveLastWhenEmpty() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        l.removeLast();
        l.removeLast();
        int x = l.removeLast();
        assertThat(l.toList()).containsExactly();
        assertThat(x).isEqualTo(1);
    }

    @Test
    public void voidTestToListEmpty() {
        Deque61B<Integer> l = new LinkedListDeque61B<>();
        assertThat(l.toList()).isEmpty();
    }
}