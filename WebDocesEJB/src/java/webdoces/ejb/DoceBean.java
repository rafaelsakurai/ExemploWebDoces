package webdoces.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import webdoces.dao.DoceDAO;
import webdoces.modelo.Doce;

/**
 * Implementação do EJB de Doce.
 *
 * @author Rafael Guimarães Sakurai
 */
@Stateless
public class DoceBean implements DoceRemote {
  @PersistenceContext(unitName="WebDocesPU")
  private EntityManager em;

  /**
   * Salva ou atualiza os dados do doce.
   *
   * @param doce - Objeto doce que será salvo ou atualizado.
   * @return O objeto doce após ser salvo.
   * @throws Exception - Lançado caso ocorra algum erro.
   */
  @Override
  public Doce salvar(Doce doce) throws Exception {
    DoceDAO dao = new DoceDAO(em);
    return dao.salvar(doce);
  }

  /**
   * Remove um doce a partir de seu id.
   *
   * @param id
   */
  @Override
  public void remover(Long id) {
    DoceDAO dao = new DoceDAO(em);
    dao.remover(id);
  }

  /**
   * Consulta todos os doces.
   *
   * @return uma lista com os doces cadastrados.
   */
  @Override
  public List<Doce> consultarTodos() {
    DoceDAO dao = new DoceDAO(em);
    return dao.consultarTodos();
  }

}
