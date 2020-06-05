package cadastro.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class AlunoDAO {

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

	public boolean inserir(Aluno aluno) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(aluno);
			manager.getTransaction().commit();

			mensagem = CADASTRO_SUCESSO;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = FALHA_OPERACAO;
			return false;
		}

	}

	public List<Aluno> consultar() {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			TypedQuery<Aluno> query = manager.createQuery("SELECT a FROM Aluno a", Aluno.class);
			List<Aluno> lista = (List<Aluno>) query.getResultList();
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

	public List<Aluno> pesquisar(String nome) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			TypedQuery<Aluno> query = manager.createQuery("select a FROM Aluno a where a.nome LIKE :paramNome",
					Aluno.class);
			query.setParameter("paramNome", "%" + nome + "%");
			List<Aluno> lista = (List<Aluno>) query.getResultList();
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

	public Aluno pesquisar(int id) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			Aluno aluno = manager.find(Aluno.class, id);
			manager.getTransaction().commit();

			if (aluno == null) {
				mensagem = CONSULTA_VAZIA;
			}

			return aluno;

		} catch (Exception e) {
			e.printStackTrace();
			mensagem = FALHA_OPERACAO;
			return null;
		}
	}

	public boolean atualizar(Aluno aluno) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(aluno);
			manager.getTransaction().commit();

			mensagem = ATUALIZACAO_SUCESSO;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = FALHA_OPERACAO;
			return false;
		}
	}

	public boolean remover(Aluno aluno) {

		EntityManager manager = JPAUtil.getEntityManager();

		try {
			manager.getTransaction().begin();
			manager.remove(aluno);
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