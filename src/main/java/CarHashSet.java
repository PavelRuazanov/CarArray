import java.util.Iterator;

public class CarHashSet implements CarSet {

  private int size = 0;
  private static final int INITIAL_CAPACITY = 16;
  private Entry[] array = new Entry[INITIAL_CAPACITY];
  private static final double LOAD_FACTOR = 0.75;

  @Override
  public boolean add(Car car) {
    if (size >= (array.length * LOAD_FACTOR)) {
      increaseArrays();
    }
    boolean result = add(car, array);
    if (result) {
      size++;
    }
    return result;
  }

  private boolean add(Car car, Entry[] dst) {
    int position = getArraysPosition(car, dst.length);
    if (dst[position] == null) {
      dst[position] = new Entry(car, null);
      return true;
    } else {
      Entry existedElement = dst[position];
      while (true) {
        if (dst[position].value.equals(car)) {
          return false;
        } else if (existedElement.next == null) {
          existedElement.next = new Entry(car, null);
          return true;
        } else {
          existedElement = existedElement.next;
        }
      }
    }
  }

  @Override
  public boolean remove(Car car) {
    int position = getArraysPosition(car, array.length);
    if (array[position] == null) {
      return false;
    }
    Entry secondLast = array[position];
    Entry last = array[position].next;
    if (secondLast.value.equals(car)) {
      array[position] = last;
      size--;
      return true;
    }
    while (last != null) {
      if (last.value.equals(car)) {
        secondLast.next = last.next;
        size--;
        return true;
      } else {
        secondLast = last;
        last = last.next;
      }
    }
    return false;
  }

  private void increaseArrays() {
    Entry[] newArrays = new Entry[array.length * 2];
    for (Entry entry : array) {
      Entry existedElement = entry;
      while (existedElement != null) {
        add(existedElement.value, newArrays);
        existedElement = existedElement.next;
      }
    }
    array = newArrays;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    size = 0;
    array = new Entry[INITIAL_CAPACITY];
  }

  private static class Entry {

    private Car value;
    private Entry next;

    public Entry(Car value, Entry next) {
      this.value = value;
      this.next = next;
    }
  }

  private int getArraysPosition(Car car, int arrayLength) {
    return Math.abs(car.hashCode() % arrayLength);
  }

  @Override
  public boolean contains(Car car) {
    int position = getArraysPosition(car, array.length);
    if (array[position] == null) {
      return false;
    }
    Entry secondLast = array[position];
    while (secondLast != null) {
      if (secondLast.value.equals(car)) {
        return true;
      } else {
        secondLast = secondLast.next;
      }
    }
    return false;
  }

  @Override
  public Iterator<Car> iterator() {
    return new Iterator<Car>() {
      int index = 0;
      Entry entry;
      int arrayIndex = 0;


      @Override
      public boolean hasNext() {
        return index < size;
      }

      @Override
      public Car next() {
        while (array[arrayIndex] == null){
          arrayIndex++;
        }
        if (entry == null){
          entry = array[arrayIndex];
        }
        Car result = entry.value;
        entry = entry.next;
        if (entry == null){
          arrayIndex++;
        }
        index++;
        return result;
      }
    };
  }
}
