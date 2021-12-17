import java.util.*;

public class JavaProgramm {
    private final static int NUMBER_OF_OPERATORS = 2;
    private final static int NUMBER_OF_CLIENTS = 6;


    public static void main(String[] args) {
        //создаем список операторов
        List<Operator> operators = new LinkedList<>();
        for (int i = 0; i < NUMBER_OF_OPERATORS; i++) {
            Operator operator = new Operator(i+1);
            operators.add(operator);
        }

        //создаем Колл-центр
        CallCenter callCenter = new CallCenter(operators);

        //создаем Клиентов
        List<Client> clients = new LinkedList<>();
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            Client client = new Client(callCenter, i+1);
            clients.add(client);
        }


        //запускаем поток клиента
        for (Client client:clients) {
            client.start();
        }
    }
}

