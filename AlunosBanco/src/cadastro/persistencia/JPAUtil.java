package cadastro.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("AlunosBanco");
	private static EntityManager manager;
	
	public static EntityManager getEntityManager() {
	
		if (manager == null) {
			manager = factory.createEntityManager();
		}
		
		return manager;
	}
	
	public static void close() {
		manager.close();
	}
}