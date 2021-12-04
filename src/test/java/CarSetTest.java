import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarSetTest {
private CarSet carSet;
  @Before
  public void setUp() throws Exception {
    carSet = new CarHashSet();
    for (int i = 0; i < 100; i++) {
      carSet.add(new Car("Model" + i, i));
    }
  }

  @Test
  public void add() {
      Car car = new Car("Name",1);
      Car car2 = new Car("Name",1);
      Car car3 = new Car("Name",1);
      assertTrue(carSet.add(car));
      assertFalse(carSet.add(car2));
      assertFalse(carSet.add(car3));
      assertEquals(101,carSet.size());
    }

    @Test
  public void remove() {
    assertTrue(carSet.remove(new Car("Model30",30)));
    assertEquals(99,carSet.size());
    assertFalse(carSet.remove(new Car("Model30",30)));
    assertEquals(99,carSet.size());
  }

  @Test
  public void size() {
    assertEquals(100,carSet.size());
  }

  @Test
  public void clear() {
    carSet.clear();
    assertEquals(0,carSet.size());
  }

  @Test
  public void contains() {
    assertTrue(carSet.contains(new Car("Model30",30)));
    assertFalse(carSet.contains(new Car("Modeljjj",30)));
  }
}
