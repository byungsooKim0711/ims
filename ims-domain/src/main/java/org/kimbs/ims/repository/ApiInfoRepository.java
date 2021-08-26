package org.kimbs.ims.repository;

import org.kimbs.ims.domain.ApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiInfoRepository extends JpaRepository<ApiInfo, Long> {
}
