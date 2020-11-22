package br.com.gregory.apirest;

import br.com.gregory.apirest.models.AddressModel;
import br.com.gregory.apirest.repositories.AddressRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApirestApplicationTests {

	@Autowired
	private	AddressRepository repository;

	//CREATE
	@Test
	void save() {
		AddressModel address = new AddressModel();
		address.setStreetName("Teste");
		address.setNumber(1234);
		address.setComplement("Teste");
		address.setNeighbourhood("Teste");
		address.setCity("Teste");
		address.setState("Teste");
		address.setCountry("Teste");
		address.setZipcode(1234567);
		address.setLatitude(null);
		address.setLongitude(null);
		this.repository.save(address);

		Assertions.assertThat(address.getId()).isNotNull();
	}

	//READ
	@Test
	void findAll() {
		AddressModel a1 = new AddressModel();
		a1.setStreetName("Teste");
		a1.setNumber(1234);
		a1.setComplement("Teste");
		a1.setNeighbourhood("Teste");
		a1.setCity("Teste");
		a1.setState("Teste");
		a1.setCountry("Teste");
		a1.setZipcode(1234567);
		a1.setLatitude(null);
		a1.setLongitude(null);

		AddressModel a2 = new AddressModel();
		a2.setStreetName("Teste 2");
		a2.setNumber(1234);
		a2.setComplement("Teste 2");
		a2.setNeighbourhood("Teste 2");
		a2.setCity("Teste 2");
		a2.setState("Teste 2");
		a2.setCountry("Teste 2");
		a2.setZipcode(1234567);
		a2.setLatitude(null);
		a2.setLongitude(null);

		repository.save(a1);
		repository.save(a2);

		Assertions.assertThat(repository.findAll()).isNotNull();
	}

	//READ
	@Test
	void findById() {
		AddressModel a1 = new AddressModel();
		a1.setStreetName("Teste");
		a1.setNumber(1234);
		a1.setComplement("Teste");
		a1.setNeighbourhood("Teste");
		a1.setCity("Teste");
		a1.setState("Teste");
		a1.setCountry("Teste");
		a1.setZipcode(1234567);
		a1.setLatitude(null);
		a1.setLongitude(null);

		AddressModel a2 = new AddressModel();
		a2.setStreetName("Teste 2");
		a2.setNumber(1234);
		a2.setComplement("Teste 2");
		a2.setNeighbourhood("Teste 2");
		a2.setCity("Teste 2");
		a2.setState("Teste 2");
		a2.setCountry("Teste 2");
		a2.setZipcode(1234567);
		a2.setLatitude(null);
		a2.setLongitude(null);

		repository.save(a1);
		repository.save(a2);

		Assertions.assertThat(repository.getOne(a1.getId())).isNotNull();
	}

	//UPDATE
	@Test
	void update() {
		AddressModel address = new AddressModel();
		address.setStreetName("Teste");
		address.setNumber(1234);
		address.setComplement("Teste");
		address.setNeighbourhood("Teste");
		address.setCity("Teste");
		address.setState("Teste");
		address.setCountry("Teste");
		address.setZipcode(1234567);
		address.setLatitude(null);
		address.setLongitude(null);

		this.repository.save(address);
		address.setNeighbourhood("Novo bairro teste");
		repository.save(address);

		Assertions.assertThat(address.getNeighbourhood()).isEqualTo("Novo bairro teste");
	}

	//DELETE
	@Test
	void delete() {
		AddressModel address = new AddressModel();
		address.setStreetName("Teste");
		address.setNumber(1234);
		address.setComplement("Teste");
		address.setNeighbourhood("Teste");
		address.setCity("Teste");
		address.setState("Teste");
		address.setCountry("Teste");
		address.setZipcode(1234567);
		address.setLatitude(null);
		address.setLongitude(null);

		repository.save(address);
		repository.delete(address);

		Assertions.assertThat(repository.getOne(address.getId())).isNull();
	}

}
