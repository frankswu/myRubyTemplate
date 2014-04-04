package org.springside.examples.quickstart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.TM<%= key %>;


/**
 * @auther frankswu
 */
public interface <%= key %>Dao extends PagingAndSortingRepository<TM<%= key %>, Long>, JpaSpecificationExecutor<TM<%= key %>> {

    //    Page<TM<%= key %>> findByXXXXId(Long id, Pageable pageRequest);

    //    TM<%= key %> findByXXXXX(String loginName);

    //    @Modifying
    //	@Query("delete from TM<%= key %> <%= key.downcase %> where <%= key.downcase %>.user.id=?1")
    //	void deleteByXXXXXId(Long id);
}
