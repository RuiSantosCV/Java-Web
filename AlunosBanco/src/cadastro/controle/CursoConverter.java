package cadastro.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import cadastro.modelo.CursoBean;
import cadastro.persistencia.Curso;

@FacesConverter("CursoConverter")
public class CursoConverter implements Converter{


	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Integer idCurso = Integer.parseInt(value);
		CursoBean cursoBean = new CursoBean();
		Curso curso = cursoBean.getCurso(idCurso);
		return curso;

	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Curso curso = (Curso) value;
		CursoBean cursoBean = new CursoBean();
		Integer idCurso = cursoBean.getIdCurso(curso.getNome());
		return idCurso.toString();
	}
}
