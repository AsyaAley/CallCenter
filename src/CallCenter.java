import java.util.List;

public class CallCenter {
    private final static int NUMBER_TRYING = 5;
    List<Operator> operators;

    public CallCenter(List<Operator> operators){
        this.operators = operators;
    }

    public synchronized Operator tryCall(Client client, int waitingTime) throws InterruptedException {
        Operator busyOperator = null;
            for (Operator operator : operators) {
                if (this.searchFreeOperator(operator, client)){
                    operator.talk();
                    busyOperator = operator;
                    operators.remove(busyOperator);
                    System.out.println("Клиент " + client.getid() + " разговаривает с оператором " + operator.getId());
                    break;
                }else {client.sleep(waitingTime); }
            }
        return busyOperator;
    }

    public synchronized void endCall(Operator operator){
        operator.setClient(null);
        operators.add(operator);
        System.out.println("Оператор " + operator.getId() + " освободился.");
    }

    private boolean searchFreeOperator(Operator operator, Client client){
        if (operator.getClient() == null){
            operator.setClient(client);
            return true;
        }else return false;
    }
}
