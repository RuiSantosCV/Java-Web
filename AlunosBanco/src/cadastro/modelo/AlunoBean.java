package cadastro.modelo;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import cadastro.persistencia.Aluno;
import cadastro.persistencia.AlunoDAO;
import cadastro.persistencia.Curso;

@ManagedBean
@SessionScoped
public class AlunoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	private String mensagem;
	private Curso selectedCurso;
	private List<Aluno> listaConsulta;
	private String nome;
	private AlunoDAO alunoDao;

	public AlunoDAO getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDAO alunoDao) {
		this.alunoDao = alunoDao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getListaConsulta() {
		return listaConsulta;
	}

	public void setListaConsulta(List<Aluno> listaConsulta) {
		this.listaConsulta = listaConsulta;
	}

	public Curso getSelectedCurso() {
		return selectedCurso;
	}

	public void setSelectedCurso(Curso selectedCurso) {
		this.selectedCurso = selectedCurso;
		this.aluno.setCurso(selectedCurso.getNome());
	}

	public AlunoBean() {
		alunoDao = new AlunoDAO();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String cadastrar() {
		selectedCurso = new Curso();
		aluno = new Aluno();
		return "cadastrar";
	}
	
	public String pre_salvo(){
		return "confirmar-cadastro";
	}

	public String salvar() {
				
		if (aluno.getId() == null) {
			alunoDao.inserir(aluno);
			mensagem = alunoDao.getMensagem();
		} else {
			alunoDao.atualizar(aluno);		
			mensagem=alunoDao.getMensagem();
		}

		return "exibir-mensagem";
	}

	public String listar() {
		listaConsulta = alunoDao.consultar();
		return "exibir-consulta";
	}

	public String pesquisar() {
		listaConsulta = alunoDao.pesquisar(nome);
		return "exibir-consulta";
	}

	public String editar() {		
		return "cadastrar";
	}

	public String remover() {

		alunoDao.remover(aluno);
		mensagem = alunoDao.getMensagem();

		return listar();
	}

	public void validarEmail(FacesContext context, UIComponent toValidate, Object value) {
		String email = (String) value;
		if (email.indexOf('@') == -1) {
			UIInput componente = (UIInput) toValidate;
			componente.setValid(false);
			FacesMessage message = new FacesMessage("E-mail inválido");
			context.addMessage(toValidate.getClientId(context), message);
		}
	}

	public void validarNome(FacesContext context, UIComponent toValidate, Object value) {
		String nome = (String) value;

		if ((nome.replaceAll("\\s","").matches("[A-Z]+") == false) || (nome.length() < 5)) {
			
			UIInput componente = (UIInput) toValidate;
			componente.setValid(false);
			FacesMessage message = new FacesMessage("Nome inválido");
			context.addMessage(toValidate.getClientId(context), message);
		}

	}
	
	public void validarCPF(FacesContext context, UIComponent toValidate, Object value) {
		String cpf = (String) value;

		if ((cpf.matches("[0-9]+") == false) || (cpf.length() < 11)) {

			UIInput componente = (UIInput) toValidate;
			componente.setValid(false);
			FacesMessage message = new FacesMessage("CPF inválido");
			context.addMessage(toValidate.getClientId(context), message);
		}

	}

}
