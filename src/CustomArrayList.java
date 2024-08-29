import java.util.ArrayList;
import java.util.Collection;

public class CustomArrayList<T> {
    private T[] customArray;
    private int arrayLength;
    private static final int STANDARD_ADDED_SIZE = 5;

    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        customArray = (T[]) new Object[10];  // Начальная длина массива
        arrayLength = 0;  // Начальная длина списка
    }

    @SuppressWarnings("unchecked")
    public CustomArrayList(Collection<? extends T> collection) {
        customArray = (T[]) new Object[collection.size() + 10];  // Инициализируем массив с запасом места
        arrayLength = 0;
        addAll(collection);
    }

    public void add(T object) { // добавляем элемент в список
        if (arrayLength >= customArray.length)
            arrayExpansion(STANDARD_ADDED_SIZE);

        customArray[arrayLength++] = object;
    }

    public T get(int index) {   // вернет порядковый элемент
        if (index < 0 || index >= arrayLength)
            throw new IndexOutOfBoundsException();

        return customArray[index];
    }

    public T get(T object) {  // Найти и вернуть первый найденный элемент
        for (int i = 0; i < arrayLength; i++) {
            if (customArray[i].equals(object)) {
                return customArray[i];
            }
        }
        return null;
    }

    public int length() {   // вернет длину списка
        return arrayLength;
    }

    public void remove(int index) {  // удаляет элемент по индексу
        if (index < 0 || index >= arrayLength)
            throw new IndexOutOfBoundsException();

        for (int i = index; i < arrayLength - 1; i++)
            customArray[i] = customArray[i + 1];

        customArray[--arrayLength] = null;
    }

    public void remove(T object) {  // Удаляет первый найденный объект
        for (int i = 0; i < arrayLength; i++) {
            if (customArray[i].equals(object)) {
                remove(i);
                return;
            }
        }
    }

    public void addAll(Collection<? extends T> collection) {  // переписать переданную коллекцию
        arrayExpansion(collection.size());

        for (T object : collection)
            add(object);
    }

    public static <T extends Comparable<? super T>> void bubbleSort(Collection<T> collection) {  // Сортировка пузырьком
        Object[] array = collection.toArray();

        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;  // Флаг для отслеживания обменов

            for (int j = 0; j < array.length - i - 1; j++) {
                T a = (T) array[j];
                T b = (T) array[j + 1];
                if (a.compareTo(b) > 0) {
                    array[j] = b;
                    array[j + 1] = a;
                    swapped = true;
                }
            }

            if (!swapped) break;    // Если не было ни одного обмена, массив уже отсортирован
        }

        // Обновляем коллекцию
        collection.clear();
        for (Object e : array) {
            collection.add((T) e);
        }
    }

    private void arrayExpansion(int addedSize) { // расширение списка при необходимости
        T[] doubleArray = (T[]) new Object[customArray.length + addedSize];
        System.arraycopy(customArray, 0, doubleArray, 0, customArray.length);
        customArray = doubleArray;
    }
}