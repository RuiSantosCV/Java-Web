package cadastro.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CursoDAO {

	private String mensagem;

	public static final String FALHA_CONEXAO = "Falha na conexão com o banco de dados";
	public static final String FALHA_OPERACAO = "Falha na operação do banco de dados";
	public static final String CADASTRO_SUCESSO = "Cadastro realizado com sucesso";
	public static final String CADASTRO_INSUCESSO = "O cadastro não foi realizado";
	public static final String CONSULTA_VAZIA = "A consulta não retornou resultados";
	public static final String CONSULTA_SUCESSO = "Consulta realizada com sucesso";
	public static final String ATUALIZACAO_SUCESSO = "Atualização realizada com sucesso";
	public static final String ATUALIZACAO_INSUCESSO = "A atualização não foi realizada";
	public static final String EXCLUSAO_SUCESSO = "Exclusão realizada com sucesso";
	public static final String EXCLUSAO_INSUCESSO = "A exclusão não foi realizada";

	public String getMensagem() {
		return mensagem;
	}

	public boolean inserir(Curso curso) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(curso);
			manager.getTransaction().commit();

			mensagem = CADASTRO_SUCESSO;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = FALHA_OPERACAO;
			return false;
		}

	}

	public List<Curso> consultar() {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			TypedQuery<Curso> query = manager.createQuery("SELECT c FROM Curso c", Curso.class);
			List<Curso> lista = (List<Curso>) query.getResultList();
			manager.getTransaction().commit();

			if (lista.size() < 1) {
				mensagem = CONSULTA_VAZIA;
			}

			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = FALHA_OPERACAO;
			return null;
		}
	}

	public List<Curso> pesquisar(String nome) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			TypedQuery<Curso> query = manager.createQuery("select c FROM Curso c where c.nome LIKE :paramNome",
					Curso.class);
			query.setParameter("paramNome", "%" + nome + "%");
			List<Curso> lista = (List<Curso>) query.getResultList();
			manager.getTransaction().commit();

			if (lista.size() < 1) {
				mensagem = CONSULTA_VAZIA;
			}

			return lista;
			
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = FALHA_OPERACAO;
			return null;
		}
	}

	public Curso pesquisar(int id) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			Curso curso = manager.find(Curso.class, id);
			manager.getTransaction().commit();

			if ( curso == null) {
				mensagem = CONSULTA_VAZIA;
			}

			return curso;

		} catch (Exception e) {
			e.printStackTrace();
			mensagem = FALHA_OPERACAO;
			return null;
		}
	}

	public boolean atualizar(Curso curso) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(curso);
			manager.getTransaction().commit();

			mensagem = ATUALIZACAO_SUCESSO;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = FALHA_OPERACAO;
			return false;
		}
	}

	public boolean remover(Curso curso) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			manager.remove(curso);
			manager.getTransaction().commit();

			mensagem = EXCLUSAO_SUCESSO;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = FALHA_OPERACAO;
			return false;
		}
	}

}