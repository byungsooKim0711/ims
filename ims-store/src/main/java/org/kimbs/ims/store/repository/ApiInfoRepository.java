package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.ApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiInfoRepository extends JpaRepository<ApiInfo, Long> {
}
