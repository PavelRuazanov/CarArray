public class CarHashSet implements CarSet {

  private int size;
  private static final int INITIAL_CAPACITY = 16;
  private static final double LOAD_FACTOR = 0.75;
  private Entry[] array = new Entry[INITIAL_CAPACITY];

  @Override
  public boolean add(Car car) {
    if (size >= (array.length * LOAD_FACTOR)){
      increaseArray();
    }
    boolean result = add(car, array);
    if (result) {
      size++;
    }
    return result;
  }

  private boolean add(Car car, Entry[] dst) {
    int position = getElementPosition(car, dst.length);
    if (dst[position] == null) {
      Entry entry = new Entry(car, null);
      dst[position] = entry;
      return true;
    } else {
      Entry existedElement = dst[position];
      while (true) {
        if (existedElement.value.equals(car)) {
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
    int position = getElementPosition(car, array.length);
    if (array[position] == null){
      return false;
    }
    Entry secondPosition = array[position];
    Entry lastPosition = array[position].next;
    if (secondPosition.value.equals(car)) {
      array[position] = lastPosition;
      size--;
      return true;
    }
    while (lastPosition != null) {
      if (lastPosition.value.equals(car)) {
        secondPosition.next = lastPosition.next;
        size--;
        return true;
      } else {
        secondPosition = lastPosition;
        lastPosition = lastPosition.next;
      }
    }
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    array = new Entry[INITIAL_CAPACITY];
    size = 0;

  }

  private static class Entry {

    private Car value;
    private Entry next;

    public Entry(Car value, Entry next) {
      this.value = value;
      this.next = next;
    }
  }

  private void increaseArray() {
    Entry []newArray = new Entry[array.length * 2];
    for (Entry entry : array) {
      Entry existedElement = entry;
      while (existedElement != null) {
        add(existedElement.value,newArray);
        existedElement = existedElement.next;
      }
    }
    array = newArray;
  }

  private int getElementPosition(Car car, int arrayLength) {
    return Math.abs(car.hashCode() % arrayLength);
  }

}
