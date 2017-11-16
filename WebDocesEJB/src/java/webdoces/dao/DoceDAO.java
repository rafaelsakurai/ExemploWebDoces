package webdoces.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import webdoces.modelo.Doce;

/**
 * Classe para executar as operações no banco de dados relacionados a Doce.
 *
 * @author Rafael Guimarães Sakurai
 */
public class DoceDAO {
  private EntityManager em = null;

  /**
   * Construtor que recebe como parametro a EntityManager.
   *
   * @param em
   */
  public DoceDAO(EntityManager em) {
    this.em = em;
  }

  /**
   * Salva ou atualiza os dados do doce.
   *
   * @param doce - Objeto doce que será salvo ou atualizado.
   * @return O objeto doce após ser salvo.
   * @throws Exception - Lançado caso ocorra algum erro.
   */
  public Doce salvar(Doce doce) throws Exception {
    if(doce.getId() == null) {
      em.persist(doce);
    } else {
      if(!em.contains(doce)) {
        if(em.find(Doce.class, doce.getId()) != null) {
          throw new Exception("Erro ao salvar o doce.");
        }
      }

      doce = em.merge(doce);
    }

    return doce;
  }

  /**
   * Remove um doce a partir de seu id.
   *
   * @param id
   */
  public void remover(Long id) {
    Doce doce = em.find(Doce.class, id);
    em.remove(doce);
  }

  /**
   * Consulta todos os doces.
   *
   * @return uma lista com os doces cadastrados.
   */
  public List<Doce> consultarTodos() {
    Query q = em.createNamedQuery("Doce.consultarTodos");
    return q.getResultList();
  }
}
