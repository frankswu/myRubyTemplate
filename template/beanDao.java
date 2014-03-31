package org.springside.examples.quickstart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.<%= key %>;


/**
 * @auther frankswu
 */
public interface <%= key %>Dao extends PagingAndSortingRepository<<%= key %>, Long>, JpaSpecificationExecutor<<%= key %>> {

    Page<<%= key %>> findByXXXXId(Long id, Pageable pageRequest);

    <%= key %> findByXXXXX(String loginName);

    @Modifying
	@Query("delete from <%= key %> <%= key %> where <%= key %>.user.id=?1")
	void deleteByXXXXXId(Long id);
}
