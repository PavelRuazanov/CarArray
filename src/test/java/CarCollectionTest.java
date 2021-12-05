import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarCollectionTest {
private CarCollection carCollection;

  @Before
  public void setUp() throws Exception {
    carCollection = new CarHashSet();
    for (int i = 0; i < 100; i++) {
      carCollection.add(new Car("Model" + i, i));
    }
  }

  @Test
  public void testForEach() {
    int index = 0;
    for (Car car: carCollection){
      index++;
    }
    assertEquals(100,index);
  }
}