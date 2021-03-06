package pt.amane.dstrabalhofinal.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

	@Transactional
	public ClientDTO create(ClientDTO dto) {
		Client client = new Client();
		client = copyClientEntity(client, dto);
		client = repository.save(client);
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client client = repository.getOne(id);
			client = copyClientEntity(client, dto);
			client = repository.save(client);
			return new ClientDTO(client);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("Id not found! Id: " + id + ", Type: " + ClientDTO.class.getName());
		}

	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Id not found! Id: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new pt.amane.dstrabalhofinal.services.exceptions.DataBaseIntegrityViolationException(
					"Data base Integraty");
		}

	}

	private Client copyClientEntity(Client client, ClientDTO dto) {
		client.setName(dto.getName());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		return client;
	}

}
