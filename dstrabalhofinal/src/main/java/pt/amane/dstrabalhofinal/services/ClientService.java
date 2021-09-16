package pt.amane.dstrabalhofinal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.dstrabalhofinal.dtos.ClientDTO;
import pt.amane.dstrabalhofinal.entities.Client;
import pt.amane.dstrabalhofinal.repositories.ClientRepository;
import pt.amane.dstrabalhofinal.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> clientId = repository.findById(id);
		Client client = clientId.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente not found! Id: " + id + ", Type: " + Client.class.getName()));
		return new ClientDTO(client);
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPerPaged(PageRequest pageRequest) {
		Page<Client> clients = repository.findAll(pageRequest);
		return clients.map(dto -> new ClientDTO(dto));
	}

	public ClientDTO create(ClientDTO dto) {
		Client client = new Client();
		client = copyClientEntity(client, dto);
		client = repository.save(client);
		return new ClientDTO(client);
	}

	@Transactional
	private Client copyClientEntity(Client client,ClientDTO dto) {
		client.setName(dto.getName());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		return client;
	}

}
