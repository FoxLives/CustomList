import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CustomArrayListTest {
    CustomArrayList<Integer> customList;
    ArrayList<Integer> arrayList;

    @Before
    public void setUp() throws Exception {
        arrayList = new ArrayList<>();
        arrayList.add(7);
        arrayList.add(2);
        arrayList.add(4);
        arrayList.add(1);
        arrayList.add(9);

        customList = new CustomArrayList<>(arrayList);
    }

    @Test
    public void add() {
        customList.add(55);
        arrayList.add(55);

        for (int i = 0; i < arrayList.size(); i++)
            assertEquals(customList.get(i), arrayList.get(i));
    }

    @Test
    public void getByIndex() {
        assertEquals(Integer.valueOf(7), customList.get(0));
        assertEquals(Integer.valueOf(4), customList.get(2));
        assertEquals(Integer.valueOf(9), customList.get(4));
    }

    @Test
    public void getByObject() {
        assertEquals(Integer.valueOf(7), customList.get((Integer) 7));
        assertEquals(Integer.valueOf(2), customList.get((Integer) 2));
        assertNull(customList.get((Integer) 100)); // Объекта 100 нет в списке, поэтому ожидаем null
    }

    @Test
    public void length() {
        assertEquals(5, customList.length());
        customList.add(100);
        assertEquals(6, customList.length());
    }

    @Test
    public void removeByIndex() {
        customList.remove(1); // Удаляем элемент с индексом 1 (значение 2)
        assertEquals(4, customList.length());
        assertEquals(Integer.valueOf(4), customList.get(1)); // Теперь на позиции 1 значение 4
    }

    @Test
    public void removeByObject() {
        customList.remove((Integer) 2); // Удаляем элемент со значением 2
        assertEquals(4, customList.length());
        assertNull(customList.get((Integer) 2)); // Значение 2 больше не существует в списке
    }

    @Test
    public void addAll() {
        List<Integer> additionalList = Arrays.asList(10, 20, 30);
        customList.addAll(additionalList);
        arrayList.addAll(additionalList);

        assertEquals(arrayList.size(), customList.length());

        for (int i = 0; i < arrayList.size(); i++) {
            assertEquals(customList.get(i), arrayList.get(i));
        }
    }

    @Test
    public void bubbleSort() {
        ArrayList listClone = arrayList;

        CustomArrayList.bubbleSort(arrayList);
        Collections.sort(listClone);

        for (int i = 0; i < arrayList.size(); i++) {
            assertEquals(listClone.get(i), arrayList.get(i));
        }
    }
}