package sample;

public class Client {
    private int id=-1;
    private String surname;
    private String name;
    private String middleName;
    private int phoneNumber;
    private String address;
    private String login;
    private String password;

    public Client(int id, String surname, String name, String middleName, int phoneNumber, String address, String login, String password) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.login = login;
        this.password = password;
    }

    public Client(String surname, String name, String middleName, int phoneNumber, String address, String login, String password) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.login = login;
        this.password = password;
    }
    public Client(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(this.id==-1){
            this.id=id;
        }else{
            System.out.println("Error with modification primary key !!!");
        }

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}'+'\n';
    }
}
