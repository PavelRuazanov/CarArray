public interface CarArrays {

  Car get (int index);
  void add(Car car);
  boolean remove(Car car);
  boolean removeAt (int index);
  int size();
  void clear();
  void add (Car car,int index);


}