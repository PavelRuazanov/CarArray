import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarArraysTest {
  private CarArrays carArrays;

  @Before
  public void setUp() throws Exception {
    carArrays = new Carlist();
    for (int i = 0; i < 100; i++) {
      carArrays.add(new Car("Model" + i, i));
    }
  }

  @Test
  public void get() {
    carArrays.get(5);
    Car car = carArrays.get(0);
    assertEquals("Model0",car.getName());
  }

  @Test
  public void WhenAddedNewCarSizeMustBe101() {
    Car car = new Car("Name",10);
    carArrays.add(car);
    assertEquals(101,carArrays.size());
    }

  @Test
  public void remove() {
    Car car = new Car("BMW",15);
    carArrays.add(car);
    assertEquals(101,carArrays.size());
    assertTrue(carArrays.remove(car));
    assertEquals(100, carArrays.size());
   }

  @Test
  public void removeAt() {

   assertTrue(carArrays.removeAt(5));
    assertEquals(99,carArrays.size());

  }

  @Test
  public void size() {
    assertEquals(100,carArrays.size());
  }

  @Test
  public void clear() {
    carArrays.clear();
    assertEquals(0, carArrays.size());
  }

  @Test
  public void whenNonExistentElementRemovedThenReturnFalse() {
    Car car = new Car("BMW",15);
    assertFalse(carArrays.remove(car));
    assertEquals(100, carArrays.size());
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void whenIndexOutOfBoundsException() {
carArrays.get(100);
  }

  @Test
  public void whenAddedCarinIndex() {
    Car car = new Car("Name",10);
    carArrays.add(car,15);
    Car car2 = carArrays.get(15);
    assertEquals(car.getName(),(car2.getName()));
    assertEquals(101,carArrays.size());
  }
  @Test
  public void whenAddedCarinIndexIntoLasPosition() {
    Car car = new Car("Name",10);
    carArrays.add(car,100);
    Car car2 = carArrays.get(100);
    assertEquals(car.getName(),(car2.getName()));
    assertEquals(101,carArrays.size());
  }
  @Test
  public void whenAddedCarinIndexIntoFirstPosition() {
    Car car = new Car("Name",10);
    carArrays.add(car,0);
    Car car2 = carArrays.get(0);
    assertEquals(car.getName(),(car2.getName()));
    assertEquals(101,carArrays.size());
  }

}