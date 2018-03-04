package cadastro.modelo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import cadastro.persistencia.Aluno;
import cadastro.persistencia.Curso;
import cadastro.persistencia.CursoDAO;

@ManagedBean
public class CursoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Curso> lista;
	private Aluno aluno;
	private CursoDAO cursoDAO;

	public List<Curso> getLista() {
		return lista;
	}

	public void setLista(List<Curso> lista) {
		this.lista = lista;
	}

	public CursoBean() {
		cursoDAO=new CursoDAO();
		init();
	}

	private void init() {
		lista= cursoDAO.consultar();
	}

	public String getNomeCurso(Integer idCurso) {
		for (Curso curso : lista) {
			if (curso.getId().compareTo(idCurso) == 0)
				aluno.setCurso(curso.getNome());
		}
		return null;
	}

	public Integer getIdCurso(String nomeCurso) {
		for (Curso curso : lista) {
			if (curso.getNome().compareTo(nomeCurso) == 0)
				return curso.getId();
		}
		return null;
	}

	public Curso getCurso(Integer idCurso) {
		for (Curso curso : lista) {
			if (curso.getId().compareTo(idCurso) == 0)
				return curso;
		}
		return null;
	}

}

