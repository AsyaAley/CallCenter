public class Client extends Thread {
    private final static int WAITING_TIME = 2000;
    private int id;
    private CallCenter callCenter;

    public Client (CallCenter callCenter, int id){
        this.id = id;
        this.callCenter = callCenter;
    }


    public int getid() {
        return id;
    }


    //thread method
    @Override
    public void run() {
        Operator operator = null;
        try {
            while (operator == null) {
                operator = callCenter.tryCall(this, WAITING_TIME);
            }
            callCenter.endCall(operator);
        } catch (InterruptedException e) {
            System.out.println("Клиент " + this.id + " положил трубку.");
        }

    }
}
