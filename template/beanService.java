package org.springside.examples.quickstart.service.<%= key %>;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.quickstart.entity.<%= key %>;
import org.springside.examples.quickstart.repository.<%= key %>Dao;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

//Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional
public class <%= key %>Service {

	private <%= key %>Dao <%= key %>Dao;

	public <%= key %> get<%= key %>(Long id) {
		return <%= key %>Dao.findOne(id);
	}

	public void save<%= key %>(<%= key %> entity) {
		<%= key %>Dao.save(entity);
	}

	public void delete<%= key %>(Long id) {
		<%= key %>Dao.delete(id);
	}

	public List<<%= key %>> getAll<%= key %>() {
		return (List<<%= key %>>) <%= key %>Dao.findAll();
	}

	public Page<<%= key %>> getXXXX<%= key %>(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<<%= key %>> spec = buildSpecification(userId, searchParams);

		return <%= key %>Dao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<<%= key %>> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<<%= key %>> spec = DynamicSpecifications.bySearchFilter(filters.values(), <%= key %>.class);
		return spec;
	}

	@Autowired
	public void set<%= key %>Dao(<%= key %>Dao <%= key %>Dao) {
		this.<%= key %>Dao = <%= key %>Dao;
	}
}
