public class Operator {
    private int id;
    private Client client;


    public Operator(int id){
        this.id = id;
        this.client = null;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void talk() throws InterruptedException {
        client.sleep((long) (Math.random()*3000 + 6000));

    }
}
