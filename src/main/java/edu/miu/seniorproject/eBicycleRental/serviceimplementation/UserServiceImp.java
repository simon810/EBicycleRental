package edu.miu.seniorproject.eBicycleRental.serviceimplementation;

import edu.miu.seniorproject.eBicycleRental.model.Credential;
import edu.miu.seniorproject.eBicycleRental.model.User;
import edu.miu.seniorproject.eBicycleRental.repository.IUserRepository;
import edu.miu.seniorproject.eBicycleRental.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {
	@Autowired
	private IUserRepository userRepository;
	

	public UserServiceImp(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long uId) {
		return userRepository.findById(uId).orElse(null);
	}

	@Override
	public void delete(Long uId) {
		userRepository.deleteById(uId);
	}

	@Override
	public User findByCredential(Credential credential) {
		return userRepository.findByCredential(credential);
	}

}
