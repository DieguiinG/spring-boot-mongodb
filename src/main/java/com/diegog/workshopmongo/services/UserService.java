package com.diegog.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegog.workshopmongo.domain.User;
import com.diegog.workshopmongo.dto.UserDTO;
import com.diegog.workshopmongo.repositories.UserRepository;
import com.diegog.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User Insert(User obj) {
		return repo.insert(obj);

	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updatedData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setName(obj.getEmail());

	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
