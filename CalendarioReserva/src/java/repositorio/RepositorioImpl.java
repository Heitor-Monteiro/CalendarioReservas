package repositorio;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class RepositorioImpl<T> implements Repositorio<T> {

	@Inject
        @PersistenceContext(unitName = "calendario_teste_2PU")
	EntityManager entityManager;

	protected HashMap<String, Object> parametros(Object... valores) {
		List<Object> par = Arrays.asList(valores);

		HashMap<String, Object> parametros = new HashMap<>();

		for (int i = 0; i < par.size() - 1; i = i + 2) {
			parametros.put(par.get(i).toString(), par.get(i + 1));
		}
		return parametros;
	}

    /**
     *
     * @param entidade
     */
    @Override
	public void inserir(T entidade) {
            System.out.println("repositorio.RepositorioImpl.inserir()  entidade = "+entidade);
            System.out.println("repositorio.RepositorioImpl.inserir()  eManager = "+entityManager);
	    entityManager.persist(entidade);
	}

	@Override
	public T atualizar(T entidade) {
		return entityManager.merge(entidade);
	}

	@Override
	public void remover(T entidade) {
		entityManager.remove(this.atualizar(entidade));
	}

	@Override
	public T getPorId(Object id) throws EntityNotFoundException {
		if (id == null) {
			throw new EntityNotFoundException("O id deve ser informado");
		}
                System.out.println("dfsfsdf" + id);   
		return entityManager.find(this.getClasseGenerica(), id);
	}

	@SuppressWarnings("unchecked")
	private Class<T> getClasseGenerica() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
			return (Class<T>) paramType.getActualTypeArguments()[0];
		} else {
			return null;
		}
	}

	@Override
	public List<T> listar() {
		TypedQuery<T> consulta = entityManager.createQuery("select t from " + getClasseGenerica().getSimpleName() + " t",
				getClasseGenerica());
		return consulta.getResultList();
	}

	@Override
	public List<T> listar(Class<T> tipo, String campoOrdem) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> consulta = cb.createQuery(tipo);
		Root<T> root = consulta.from(tipo);
		consulta.select(root);
		consulta.orderBy(cb.asc(root.get(campoOrdem)));
		return entityManager.createQuery(consulta).getResultList();
	}

	@Override
	public Long count(Class<T> tipo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> consulta = cb.createQuery(Long.class);
		consulta.select(cb.count(consulta.from(tipo)));
		return entityManager.createQuery(consulta).getSingleResult();
	}

        @Override
	public List<T> consultaUsuario (Class<T> tipo, String campo, String sql) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> consulta = cb.createQuery(tipo);
            Root<T> root = consulta.from(tipo);
            consulta.select(root);
            consulta.where(root.get(campo).in(sql));
            return entityManager.createQuery(consulta).getResultList();
	}

	@Override
	public TypedQuery<T> criarConsulta(String sql, int maxResults) {
		return this.criarConsulta(sql).setMaxResults(maxResults);
	}

	@Override
	public TypedQuery<T> criarConsultaAPartir(String sql, int firstResults) {
		return this.criarConsulta(sql).setFirstResult(firstResults);
	}

	@Override
	public TypedQuery<T> criarConsulta(String sql, int firstResults, int maxResults) {
		return this.criarConsulta(sql).setMaxResults(maxResults).setFirstResult(firstResults);
	}

	@Override
	public TypedQuery<T> criarConsulta(String sql, Object... valores) {
		TypedQuery<T> consulta = this.criarConsulta(sql);
		for (int posicao = 1; posicao <= valores.length; posicao++) {
			consulta.setParameter(posicao, valores[posicao - 1]);
		}
		return consulta;
	}

	@Override
	public TypedQuery<T> criarConsulta(String sql, Map<String, Object> parametros) {
		TypedQuery<T> consulta = this.criarConsulta(sql);
		for (Map.Entry<String, Object> parametro : parametros.entrySet()) {
			consulta.setParameter(parametro.getKey(), parametro.getValue());
		}
		return consulta;
	}

	@Override
	public TypedQuery<T> criarConsulta(String sql, int maxResults, Object... valores) {
		return this.criarConsulta(sql, valores).setMaxResults(maxResults);
	}

	@Override
	public TypedQuery<T> criarConsulta(String sql, int firstResults, int maxResults, Object... valores) {
		return this.criarConsulta(sql, valores).setMaxResults(maxResults).setFirstResult(firstResults);
	}

	@Override
	public int executa(String sql) {
		return this.criarConsulta(sql).executeUpdate();
	}

	@Override
	public int executa(String sql, Object... valores) {
		return this.criarConsulta(sql, valores).executeUpdate();
	}

	@Override
	public T get(String sql) {
		return this.criarConsulta(sql).getSingleResult();
	}

	/**
	 * M�todo para retornar uma entidade a partir de uma consulta namedquery e
	 * uma lista de par�metros definidos por ordem
	 * 
	 * @since 01/04/2015
	 * @author gustavo
	 * @version 1
	 * @param namedQuery
	 *            Nome da Named Query
	 * @param valores
	 *            sequ�ncia de valores que ir�o preencher os par�metros da
	 *            consulta, na ordem declarada
	 * @throws NoResultException
	 * @return T entidade JPA
	 * 
	 */
	@Override
	public T get(String namedQuery, Object... valores) throws NoResultException {
		return this.criarConsulta(namedQuery, valores).getSingleResult();
	}

	/**
	 * M�todo para retornar uma entidade a partir de uma consulta namedquery e
	 * uma lista de par�metros definidos por ordem
	 * 
	 * @since 01/04/2015
	 * @author gustavo
	 * @version 1
	 * @param namedQuery
	 *            Nome da Named Query
	 * @param valores
	 *            sequ�ncia de valores que ir�o preencher os par�metros da
	 *            consulta, na ordem declarada
	 * @throws NoResultException
	 * @return T entidade JPA
	 * 
	 */
	@Override
	public T get(String namedQuery, Map<String, Object> parametros) throws NoResultException {
		return this.criarConsulta(namedQuery, parametros).getSingleResult();
	}

	@Override
	public List<T> listar(String sql) {
		return this.criarConsulta(sql).getResultList();
	}

	@Override
	public List<T> listarNoMaximo(String sql, int maxResults) {
		return this.criarConsulta(sql, maxResults).getResultList();
	}

	@Override
	public List<T> listarAPartir(String sql, int firstResults) {
		return this.criarConsultaAPartir(sql, firstResults).getResultList();
	}

	@Override
	public List<T> subListar(String sql, int firstResults, int maxResults) {
		return this.criarConsulta(sql, firstResults, maxResults).getResultList();
	}

	@Override
	public List<T> listar(String sql, Object... valores) {
		return this.criarConsulta(sql, valores).getResultList();
	}

	@Override
	public List<T> listar(String sql, Map<String, Object> parametros) {
		return this.criarConsulta(sql, parametros).getResultList();
	}

	@Override
	public List<T> listarNoMaximo(String sql, int maxResults, Object... valores) {
		return this.criarConsulta(sql, maxResults, valores).getResultList();
	}

	@Override
	public List<T> subListar(String sql, int firstResults, int maxResults, Object... valores) {
		return this.criarConsulta(sql, firstResults, maxResults, valores).getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
