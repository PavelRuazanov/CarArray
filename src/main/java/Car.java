import java.util.Objects;

public class Car {
  private String name;
  private int number;

  public Car(String name, int number) {
    this.name = name;
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public int getNumber() {
    return number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car car = (Car) o;
    return number == car.number && Objects.equals(name, car.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, number);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNumber(int number) {
    this.number = number;
  }
}
