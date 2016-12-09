/**
 * Created by ScorpionOrange on 2016/12/07.
 */
interface Queue{
    // 返回队列的大小
    public int getSize();

    // 判断队列是否为空
    public boolean isEmpty();

    // 数据元素 x 入队
    public boolean enqueue(Object x);

    // 队首元素出队
    public Object dequeue();

    // 取对首元素
    public Object getFront();

    // 置队列为空操作
    public void setEmpty();
}

class CircularQueue implements Queue{

    private Object[] elements; // 数据元素数组
    private int capacity; // 数组的大小 elements.length
    private int front; // 队首指针，指向队首
    private int rear; // 队尾指针，指向队尾后一个位置
    private int s; // 标志位，为1时队列有元素，为0时队列为空

    CircularQueue(int capacity){
        this.capacity = capacity;
        front = -1;
        rear = -1;
        s = 0;
        elements = new Object[capacity];
    }

    // 返回队列的大小
    public int getSize(){
        if((s == 1) && (rear == front)){
            return capacity;
        }
        else if((s == 1) && (front == -1)){
            return rear - front;
        }
        else if(s == 1){
            return (rear - front + capacity) % capacity;
        }
        else {
            return 0;
        }
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        if(s == 0){
            return true;
        }
        else {
            return false;
        }
    }

    // 判断队列是否已满
    public boolean isFull(){
        if(rear == front && s == 1){
            return true;
        }
        else {
            return false;
        }
    }

    // 数据元素 x 入队
    public boolean enqueue(Object x){
        if(this.isFull()){
            return false;
        }
        rear = (rear + 1) % capacity;
        elements[rear] = x;
        s = 1;
        return true;
    }

    // 队首元素出队
    public Object dequeue(){
        Object object = null;

        if(s == 1){
            front = (front + 1) % capacity;
            object = elements[front];

            if(front == rear){
                s = 0;
            }
        }
        return object;
    }

    // 取对首元素
    public Object getFront(){
        Object object = null;
        if(isEmpty()){
            return null;
        }
        else {
            object = elements[(front + 1 + capacity) % capacity];
        }
        return object;
    }

    // 置队列为空操作
    public void setEmpty(){
        front = -1;
        rear = -1;
        s = 0;
    }
}

public class CircularQueueDemo {
    public static void main(String[] args){
        CircularQueue circularQueue = new CircularQueue(5);
        circularQueue.enqueue(100);
        System.out.println("元素100入队列。");
        circularQueue.enqueue(200);
        System.out.println("元素200入队列。");
        circularQueue.enqueue(300);
        System.out.println("元素300入队列。");
        circularQueue.enqueue(400);
        System.out.println("元素400入队列。");
        circularQueue.enqueue(500);
        System.out.println("元素500入队列。");
        System.out.println();

        if(circularQueue.isEmpty()){
            System.out.println("队列当前为空。");
        }
        else {
            System.out.println("队列当前不为空。");
        }
        System.out.println("队列内有" + circularQueue.getSize() + "个元素。");
        System.out.println("对首元素为：" + circularQueue.getFront());

        circularQueue.dequeue();
        System.out.println("一个元素出队列后，新队首元素为：" + circularQueue.getFront());

        circularQueue.setEmpty();
        if(circularQueue.isEmpty()){
            System.out.println("置队列为空操作后，队列内为空。");
        }
    }
}
