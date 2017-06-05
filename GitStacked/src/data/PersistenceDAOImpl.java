package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import entities.Exercise;
import entities.LoginUserType;
import entities.User;

@Transactional
@Component
public class PersistenceDAOImpl implements PersistenceDAO {
	
	@PersistenceContext
	private EntityManager em;
	
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("GitStacked");
//	EntityManager em = emf.createEntityManager();
	
	@Override
	public User createNewUser(User user) {
		if(em == null){
			System.out.println("em is null");
			return user;
		}
		user.setLoginUsertype(LoginUserType.USER);
		em.persist(user);
		System.out.println(user);
		System.out.println("user created");
		return user;
	}
	
	@Override
	public User login(User user) {
		String qry = "select u from User u where u.username = :username";
		User u = em.createQuery(qry, User.class).setParameter("username", user.getUsername()).getSingleResult();
		if (u != null) {
			if (u.getPassword().equals(user.getPassword())) {
				return u;
			}
		}
		
		return null;
	}

	@Override
	public User updateUserWeight(User user, int weight) {
		return user;
		
	}

	@Override
	public void changePassword(User user, String newPass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserHeight(User user, int newHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createWorkout(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createWorkoutExercise(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Exercise getExerciseByName(String exerciseName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exercise> getListOfExercises() {
		String query = "select e from Exercise e";
		List<Exercise> exercises = em.createQuery(query, Exercise.class).getResultList();
		return exercises;
	}

	@Override
	public User persistUser(User user) {
		User tempUser = em.find(user.getClass(), user.getId());
		
		tempUser.setFName(user.getFName());
		tempUser.setLName(user.getLName());
		tempUser.setHeightFeet(user.getHeightFeet());
		tempUser.setHeightInch(user.getHeightInch());
		tempUser.setPassword(user.getPassword());
		tempUser.setWeight(user.getWeight());
		tempUser.setWorkouts(user.getWorkouts());
		tempUser.setLoginUsertype(user.getLoginUsertype());
		
		em.persist(tempUser);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		String query = "select u from User u";
		List<User> users = em.createQuery(query, User.class).getResultList();
		return users;
	}

	@Override
	public int getUserIdByUsername(String username) {
		String query = "select u from User u where u.username = :username";
		User tempUser = em.createQuery(query, User.class).setParameter("username", username).getSingleResult();
		return tempUser.getId();
	}

	@Override
	public Exercise createExercise() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
