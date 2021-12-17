import java.util.List;

public class CallCenter {
    private final static int NUMBER_TRYING = 5;
    List<Operator> operators;

    public CallCenter(List<Operator> operators) {
        this.operators = operators;
    }

    public synchronized Operator tryCall(Client client, int waitingTime) throws InterruptedException {
        Operator busyOperator = null;
        int numberTrying = 0;
        for (Operator operator : operators) {
            System.out.println("Клиент "+ client.getid() + " звонит");
            if (this.searchFreeOperator(operator, client)) {
                System.out.println("Клиент " + client.getid() + " разговаривает с оператором " + operator.getId());
                operator.talk();
                busyOperator = operator;
                operators.remove(busyOperator);
                break;
            } else{
                numberTrying++;
                checkCountTrying(numberTrying, waitingTime, client);
            }
        }
        return busyOperator;
    }

    public synchronized void endCall(Operator operator) {
        operator.setClient(null);
        operators.add(operator);
        System.out.println("Оператор " + operator.getId() + " освободился.");
    }

    private synchronized boolean searchFreeOperator(Operator operator, Client client) {
        if (operator.getClient() == null) {
            operator.setClient(client);
            return true;
        } else return false;
    }
    private synchronized boolean checkCountTrying(int numberTrying, int waitingTime, Client client) throws InterruptedException{
        if (numberTrying<= NUMBER_TRYING){
            System.out.println("Клиент " + client.getId() + " ждёт в очереди.");
            client.sleep(waitingTime);
            return true;
        }else {
            client.interrupt();
            return false;
        }
    }
}
