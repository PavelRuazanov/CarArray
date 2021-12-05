import java.util.Iterator;

public class CarLinkedList implements CarArrays {

  private Node first;
  private Node last;
  private int size = 0;

  @Override
  public Car get(int index) {
    return getNode(index).value;
  }

  @Override
  public boolean add(Car car) {
    int size = 0;
    if (size == 0) {
      Node node = new Node(null, car, null);
      first = node;
      last = node;
    } else {
      Node secondLast = last;
      last = new Node(secondLast, car, null);
      secondLast.next = last;
    }
    size++;
    return true;

  }

  @Override
  public boolean remove(Car car) {
    Node node = first;
    for (int i = 0; i < size; i++) {
      if (node.value.equals(car)) {
        return removeAt(i);
      }
      node = node.next;
    }
    return false;
  }

  @Override
  public boolean removeAt(int index) {

    Node node = getNode(index);
    Node previousNode = node.previous;
    Node nextNode = node.next;
    if (nextNode != null) {
      nextNode.previous = previousNode;
    } else {
      last = previousNode;
    }
    if (previousNode != null) {
      previousNode.next = nextNode;
    } else {
      first = nextNode;
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
    first = null;
    last = null;
    size = 0;

  }

  @Override
  public void add(Car car, int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    } else if (index == size) {
      add(car);
    }
    else {
      Node nodeNext = getNode(index);
      Node nodePrevious = nodeNext.previous;
      Node newNode = new Node(nodePrevious, car, nodeNext);
      nodeNext.previous = newNode;
      if (nodePrevious != null) {
        nodePrevious.next = newNode;
      } else {
        first = newNode;
      }
      size++;
    }
  }

  private Node getNode(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    } else {
      Node node = first;
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
      return node;
    }
  }

  public static class Node {

    private Node previous;
    private Car value;
    private Node next;

    public Node(Node previous, Car value, Node next) {
      this.previous = previous;
      this.value = value;
      this.next = next;
    }
  }

  @Override
  public boolean contains(Car car) {
    for (int i = 0; i < size; i++) {
      if (getNode(i).value.equals(car)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Iterator<Car> iterator() {
    return new Iterator<Car>() {

      private int index = 0;

      @Override
      public boolean hasNext() {
        return index < size;
      }

      @Override
      public Car next() {
        Node node = getNode(index);
        index++;
        return node.value ;
      }
    };
  }
}
