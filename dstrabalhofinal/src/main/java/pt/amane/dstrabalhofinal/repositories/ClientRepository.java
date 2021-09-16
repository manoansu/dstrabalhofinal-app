package pt.amane.dstrabalhofinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.amane.dstrabalhofinal.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
