import java.util.Arrays;

public class CarList implements CarArrays {

  private int size = 0;
  private Car[] cars = new Car[10];

  @Override
  public Car get(int index) {
    indexCheck(index);
    return cars[index];
  }

  @Override
  public void add(Car car) {
    if (size >= cars.length) {
      cars = Arrays.copyOf(cars, cars.length * 2);
    }
    cars[size] = car;
    size++;

  }

  @Override
  public boolean remove(Car car) {
    for (int i = 0; i < size; i++) {
      if (cars[i].equals(car)) {
        return removeAt(i);
      }
    }
    return false;
  }

  @Override
  public boolean removeAt(int index) {
    indexCheck(index);
    for (int i = index; i < size - 1; i++) {
      cars[i] = cars[i + 1];
    }
    size--;

    return true;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    size = 0;
    cars = new Car[10];

  }

  private void indexCheck(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public void add(Car car, int index) {
    if (size >= cars.length) {
      cars = Arrays.copyOf(cars, cars.length * 2);
    }
    for (int i = size; i >index ; i--) {
      cars[i] = cars[i-1];
    }
    cars[index] = car;
    size++;
  }
}
