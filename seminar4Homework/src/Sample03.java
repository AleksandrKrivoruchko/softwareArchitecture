import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Sample03 {

    /**TODO: ДОМАШНЯЯ РАБОТА
     * Разработать контракты и компоненты системы "Покупка онлайн билетов на автобус в час пик".
     *
     * 4, 5,6,7,8 - необязательные, опциональные задания.
     *
     * 1.  Предусловия.
     * 2.  Постусловия.
     * 3.  Инвариант.
     * 4.  Определить абстрактные и конкретные классы.
     * 5.  Определить интерфейсы.
     * 6.  Реализовать наследование.
     * 7.  Выявить компоненты.
     * 8.  Разработать Диаграмму компонент использую нотацию UML 2.0. Общая без деталей.
     * */
    public static void main(String[] args) {

        Core core = new Core();

        MobileApp mobileApp = new MobileApp(core.getCustomerProvider(), core.getTicketProvider());
        Date date = new Date();
        try {
            mobileApp.searchTicket(date);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

        mobileApp.buyTicket("1000000000000033", date);
        mobileApp.buyTicket("1000000000000033", date);
        mobileApp.buyTicket("1000000000000033", date);
        try {
            System.out.print(mobileApp.searchTicket(date));
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

        BusStation busStation = new BusStation(mobileApp.getTicketProvider());
        String answer = busStation.checkTicket(mobileApp.getCustomer()
                .getTickets().stream().findFirst().get());
        System.out.println(answer);

        answer = busStation.checkTicket(mobileApp.getCustomer()
                .getTickets().stream().findFirst().get());
        System.out.println(answer);
    }

}

class Customer{

    private static int counter;
    private final int id;

    private Collection<Ticket> tickets;

    {
        id = ++counter;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

}

class Ticket{
    private static int count;
    private int id;
    private int customerId;
    private Date date;
    private String qrcode;

    private boolean enable = true;

    {
        id = ++count;
    }

    public int getId() {
        return id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode() {
        qrcode = String.format("%d %d %s", id, customerId, date);
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Билет %d, id клиента %d, дата %s, %s",
                id, customerId, date, isEnable() ? "действителен" : "использован");
    }
}

class Core{

    private final CustomerProvider customerProvider;
    private final TicketProvider ticketProvider;
    private final PaymentProvider paymentProvider;
    private final Database database;

    public Core(){
        database = new Database();
        customerProvider = new CustomerProvider(database);
        paymentProvider = new PaymentProvider();
        ticketProvider = new TicketProvider(database, paymentProvider);
    }

    public TicketProvider getTicketProvider() {
        return ticketProvider;
    }

    public CustomerProvider getCustomerProvider() {
        return customerProvider;
    }

}


class Database{

    private static int counter;
    private Collection<Ticket> tickets = new ArrayList<>();

    private Collection<Customer> customers = new ArrayList<>();

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    /**
     * Получить актуальную стоимость билета
     * @return
     */
    public double getTicketAmount(){
        return 60;
    }

    /**
     * Получить идентификатор заявки на покупку билета
     * @return
     */
    public int createTicketOrder(int clientId){
        return ++counter;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

}

/**
 * Мобильное приложение
 */
class MobileApp{

    private  Customer customer;
    private final TicketProvider ticketProvider;

    public Customer getCustomer() {
        return customer;
    }

    public TicketProvider getTicketProvider() {
        return ticketProvider;
    }

    public MobileApp(CustomerProvider customerProvider, TicketProvider ticketProvider){
        this.ticketProvider = ticketProvider;
        customer = customerProvider.getCustomer("login", "password");
    }


    public String searchTicket(Date date){
        customer.setTickets(ticketProvider.searchTicket(customer.getId(), date));
        Collection<Ticket> tickets = customer.getTickets();
        StringBuilder sb = new StringBuilder();
        for (Ticket ticket : tickets) {
            sb.append(ticket).append("\n");
        }
        return sb.toString();
    }

    public boolean buyTicket(String cardNo, Date date){
        return ticketProvider.buyTicket(customer.getId(), cardNo, date);
    }

}

/**
 * Автобусная станция
 */
class BusStation{
    // TODO: 1. Доработать модуль BusStation

    private  TicketProvider ticketProvider;

    public BusStation(TicketProvider ticketProvider) {
        this.ticketProvider = ticketProvider;
    }

    /**
     * Метод проверяет билет
     * @param ticket
     * @return String
     */
    public String checkTicket(Ticket ticket) {
        if (!ticket.isEnable()) {
            return "Ваш билет уже был использован!!!";
        }
        if(ticketProvider.checkTicket(ticket.getQrcode())) {
            return "Проходите в автобус!";
        }
        return "У вас фальшивый билет!!!";
    }
}

class CustomerProvider{

    private Database database;

    public CustomerProvider(Database database){
        this.database = database;
    }

    public Customer getCustomer(String login, String password){
        // ... проверка логина и пароля
        if(database.getCustomers().isEmpty()) {
            database.addCustomer(new Customer());
        }
        return database.getCustomers().stream().findFirst().get();
    }


}

class TicketProvider{

    private Database database;
    private PaymentProvider paymentProvider;

    public TicketProvider(Database database, PaymentProvider paymentProvider){
        this.database = database;
        this.paymentProvider = paymentProvider;
    }

    // 2. Доработать модуль (например TicketProvider) с точки зрения контрактного программирования.
    /**
     * Поиск билетов
     * @param clientId > 0
     * @param date != null
     * @throws RuntimeException
     * @return Collection<Ticket>
     */
    public Collection<Ticket> searchTicket(int clientId, Date date){
        // Предусловие
        if(clientId <= 0) {
            throw new RuntimeException("clientID не может быть отрицательным или равным нулю!!!");
        }
        if(date == null) {
            throw  new RuntimeException("Дата не может быть null!!!");
        }

        // Инвариант
        if(!isCustomerInDatabase(clientId)) {
            throw new RuntimeException(String.format("Клиента с id = %d нет в базе!", clientId));
        }

        Collection<Ticket> tickets = new ArrayList<>();
        for (Ticket ticket: database.getTickets()) {
            if (ticket.getCustomerId() == clientId && ticket.getDate().equals(date))
                tickets.add(ticket);
        }

        // Постусловие
        if(tickets.isEmpty()) {
            throw new RuntimeException("У клиента нет билетов с этой датой!");
        }

        return tickets;

    }

    private boolean isCustomerInDatabase(int clientId) {
        Collection<Customer> customers = database.getCustomers();
        for (Customer client : customers) {
            if(client.getId() == clientId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Покупка билета
     * @param clientId
     * @param cardNo
     * @return
     */
    public boolean buyTicket(int clientId, String cardNo, Date date){
        double amount = database.getTicketAmount();
        int orderId =  database.createTicketOrder(clientId);
        if (paymentProvider.buy(orderId, cardNo, amount)) {
            Ticket ticket = new Ticket();
            ticket.setDate(date);
            ticket.setCustomerId(clientId);
            ticket.setQrcode();
            database.addTicket(ticket);
            return true;
        }
        return false;
    }

    /**
     * Проверка билетика
     * @param qrcode
     * @return
     */
    public boolean checkTicket(String qrcode){
        for (Ticket ticket: database.getTickets()) {
            if (ticket.getQrcode().equals(qrcode)){
                ticket.setEnable(false);
                // Save database ...
                return true;
            }
        }
        return false;
    }

}

/**
 * Платежный шлюз
 */
class PaymentProvider{

    public boolean buy(int orderId, String cardNo, double amount){
        //...
        return true;
    }

}


class ProcessingCompany{

    private Collection<Bank> banks = new ArrayList<>();

}

class Bank{

}

