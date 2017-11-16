package webdoces.managedbean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import webdoces.ejb.DoceRemote;
import webdoces.modelo.Doce;

/**
 *
 * @author Rafael Guimarães Sakurai
 */
@ManagedBean
public class DoceMB {
  private Doce doce;
  private List<Doce> docesList;
  private DataModel<Doce> doces;
  
  @EJB
  private DoceRemote doceEJB;

  public DoceMB() {
      doce = new Doce();
  }

  @PostConstruct
  public void inicializarDados() {
    try {
      docesList = doceEJB.consultarTodos();
      doces = new ListDataModel<Doce>(docesList);
    } catch (Exception ex) {
      // Caso ocorra algum erro manda para a tela uma mensagem.
      FacesMessage fm = new FacesMessage(ex.getMessage());
      FacesContext.getCurrentInstance().addMessage("erro", fm);
    }
  }
  
  public String cadastrar() {
    try {
      if(isValido(doce)) {
        doce = doceEJB.salvar(doce);
        docesList.add(doce);
        // Se a doce for salvo, então manda uma mensagem de informação.
        FacesMessage fm = new FacesMessage("Doce salvo");
        FacesContext.getCurrentInstance().addMessage("informacao", fm);

        doce = new Doce();
      }
    } catch (Exception ex) {
      // Caso ocorra algum erro manda para a tela uma mensagem.
      FacesMessage fm = new FacesMessage(ex.getMessage());
      FacesContext.getCurrentInstance().addMessage("erro", fm);
    }
    return null;
  }

  public String remover() {
    try {
      Doce d = doces.getRowData();
      doceEJB.remover(d.getId());
      FacesMessage fm = new FacesMessage("Doce removido");
      FacesContext.getCurrentInstance().addMessage("informacao", fm);
      docesList.remove(d);
    } catch (Exception ex) {
      // Caso ocorra algum erro manda para a tela uma mensagem.
      FacesMessage fm = new FacesMessage(ex.getMessage());
      FacesContext.getCurrentInstance().addMessage("erro", fm);
    }
    return "cadastro";
  }

  /**
   * Verifica se as informações preechidas na tela são válidas.
   * @param doce
   * @return 
   */
  public boolean isValido(Doce doce) {
    boolean valido = true;
    if(doce.getDescricao() == null || doce.getDescricao().trim().length() == 0) {
      FacesMessage fm = new FacesMessage("Campo descrição obrigatorio!");
      FacesContext.getCurrentInstance().addMessage("erro", fm);
      valido = false;
    }

    if(doce.getValor() == null) {
      FacesMessage fm = new FacesMessage("Campo preço obrigatorio!");
      FacesContext.getCurrentInstance().addMessage("erro", fm);
      valido = false;
    } else if(doce.getValor() < 0) {
      FacesMessage fm = new FacesMessage("O preço não pode ser negativo!");
      FacesContext.getCurrentInstance().addMessage("erro", fm);
      valido = false;
    }

    return valido;
  }

  public Doce getDoce() {
    return doce;
  }

  public void setDoce(Doce doce) {
    this.doce = doce;
  }

  public DataModel<Doce> getDoces() {
    return doces;
  }

  public void setDoces(DataModel<Doce> doces) {
    this.doces = doces;
  }
}
