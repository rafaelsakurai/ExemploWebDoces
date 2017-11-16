package webdoces.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import webdoces.ejb.DoceRemote;
import webdoces.ejb.VendaRemote;
import webdoces.modelo.Doce;
import webdoces.modelo.Venda;

/**
 *
 * @author Rafael Guimarães Sakurai
 */
@ManagedBean
@SessionScoped
public class VendaMB {
  private DataModel<Doce> listaDoces = null;
  private List<Doce> lista = new ArrayList<Doce>();
  private List<Doce> selecionados = new ArrayList<Doce>();
  private double valorTotal = 0;
  private Date inicioPeriodo;
  private Date fimPeriodo;
  private List<Venda> relatorio;
  
  @EJB
  private DoceRemote doceEJB;
  @EJB
  private VendaRemote vendaEJB;

  /**
   * Constroi o ManagedBean para controlar as ações da tela de venda.
   */
  public VendaMB() {
      relatorio = new ArrayList<Venda>();
  }
  
  @PostConstruct
  public void inicializarDados() {
      try {
      // Consulta todos os doces.
      List<Doce> doces = doceEJB.consultarTodos();

      listaDoces = new ListDataModel<Doce>(doces);
    } catch (Exception ex) {
      // Caso ocorra algum erro manda para a tela uma mensagem.
      FacesMessage fm = new FacesMessage(ex.getMessage());
      FacesContext.getCurrentInstance().addMessage("erro", fm);
    }
  }

  /**
   * Ação para selecionar um doce.
   * @return
   */
  public String selecionar() {
    Doce doce = listaDoces.getRowData();

    selecionados.add(doce);
    valorTotal += doce.getValor();

    return "venda";
  }

  /**
   * Ação para finalizar uma venda.
   *
   * @return
   */
  public String finalizarVenda() {
    try {
      Venda venda = new Venda();
      venda.setData(new Date());
      venda.setDoces(selecionados);
      venda.setValorTotal(valorTotal);

      // Salvar a venda.
      vendaEJB.salvar(venda);

      // Se a venda for salva, então manda uma mensagem de informação.
      FacesMessage fm = new FacesMessage("Venda finalizada");
      FacesContext.getCurrentInstance().addMessage("informacao", fm);

      selecionados = new ArrayList<Doce>();
      valorTotal = 0;
    } catch (Exception ex) {
      // Caso ocorra algum erro manda para a tela uma mensagem.
      FacesMessage fm = new FacesMessage(ex.getMessage());
      FacesContext.getCurrentInstance().addMessage("erro", fm);
    }
    return "venda";
  }

  public String pesquisar() {
    try {
      relatorio = vendaEJB.consultarPorPeriodo(inicioPeriodo, fimPeriodo);
    } catch (Exception ex) {
      // Caso ocorra algum erro manda para a tela uma mensagem.
      FacesMessage fm = new FacesMessage(ex.getMessage());
      FacesContext.getCurrentInstance().addMessage("erro", fm);
    }

    return null;
  }

  public double getRelatorioValorTotal() {
    double total = 0;

    if(relatorio != null) {
      for(Venda v : relatorio) {
        total += v.getValorTotal();
      }
    }

    return total;
  }

  public boolean isPodeFinalizar() {
    return (selecionados != null && !selecionados.isEmpty());
  }

  public List<Doce> getLista() {
    return lista;
  }

  public void setLista(List<Doce> lista) {
    this.lista = lista;
  }

  public DataModel<Doce> getListaDoces() {
    return listaDoces;
  }

  public void setListaDoces(DataModel<Doce> listaDoces) {
    this.listaDoces = listaDoces;
  }

  public List<Doce> getSelecionados() {
    return selecionados;
  }

  public void setSelecionados(List<Doce> selecionados) {
    this.selecionados = selecionados;
  }

  public double getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(double valorTotal) {
    this.valorTotal = valorTotal;
  }

  public Date getFimPeriodo() {
    return fimPeriodo;
  }

  public void setFimPeriodo(Date fimPeriodo) {
    this.fimPeriodo = fimPeriodo;
  }

  public Date getInicioPeriodo() {
    return inicioPeriodo;
  }

  public void setInicioPeriodo(Date inicioPeriodo) {
    this.inicioPeriodo = inicioPeriodo;
  }

  public List<Venda> getRelatorio() {
    return relatorio;
  }

  public void setRelatorio(List<Venda> relatorio) {
    this.relatorio = relatorio;
  }
}
