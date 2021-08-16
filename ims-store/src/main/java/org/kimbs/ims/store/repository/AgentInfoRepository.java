package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.AgentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentInfoRepository extends JpaRepository<AgentInfo, Long> {
}
