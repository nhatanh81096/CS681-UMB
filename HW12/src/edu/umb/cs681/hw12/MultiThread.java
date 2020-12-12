package edu.umb.cs681.hw12;

public class MultiThread implements Runnable {

    public void run() {
        Customer customer = new Customer(new Address("Martin Luther King", "Raleigh", "NC", 27717));
        customer.getAddress().change("Ocher St", "Graham", "TX", 89703);
        customer.setAddress(new Address("Butler St", "New York", "NY", 10929));
        System.out.println(customer.getAddress());
    }

    public static void main(String[] args) {
        MultiThread m1 = new MultiThread();
        MultiThread m2 = new MultiThread();
        MultiThread m3 = new MultiThread();
        MultiThread m4 = new MultiThread();
        new Thread(m1).start();
        new Thread(m2).start();
        new Thread(m3).start();
        new Thread(m4).start();
    }
}
