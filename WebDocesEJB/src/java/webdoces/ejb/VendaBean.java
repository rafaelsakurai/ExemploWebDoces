package webdoces.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import webdoces.dao.VendaDAO;
import webdoces.modelo.Venda;

/**
 *
 * @author Rafael Guimarães Sakurai
 */
@Stateless
public class VendaBean implements VendaRemote {
  @PersistenceContext(unitName="WebDocesPU")
  private EntityManager em;

  /**
   * Salva ou atualiza os dados da venda.
   *
   * @param venda - Objeto venda que será salvo ou atualizado.
   * @return O objeto venda após ser salvo.
   * @throws Exception - Lançado caso ocorra algum erro.
   */
  @Override
  public Venda salvar(Venda venda) throws Exception {
    VendaDAO dao = new VendaDAO(em);
    return dao.salvar(venda);
  }

  /**
   * Remove uma venda a partir de seu id.
   *
   * @param id
   */
  @Override
  public void remover(Long id) {
    VendaDAO dao = new VendaDAO(em);
    dao.remover(id);
  }

  /**
   * Consultar todas as vendas em determinado período.
   *
   * @param inicio - Data de inicio.
   * @param fim - Data de fim.
   * @return Uma lista de vendas.
   */
  @Override
  public List<Venda> consultarPorPeriodo(Date inicio, Date fim) {
    VendaDAO dao = new VendaDAO(em);
    return dao.consultarTodos(inicio, fim);
  }

}
