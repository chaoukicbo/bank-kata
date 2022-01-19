package hexa.bank.account.bank.mapper;


import hexa.bank.account.bank.entity.ClientEntity;
import hexa.bank.account.bank.model.Client;

public class ClientMapper {

    private ClientMapper() {
        super();
    }

    public static Client mapToClient(ClientEntity clientEntity) {
        return new Client(clientEntity.getNumber(), clientEntity.getFirstName(), clientEntity.getLastName());
    }
}
