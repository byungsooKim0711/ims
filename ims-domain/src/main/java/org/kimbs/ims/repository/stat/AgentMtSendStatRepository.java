package org.kimbs.ims.repository.stat;

import org.kimbs.ims.domain.stat.AgentMtSendStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentMtSendStatRepository extends JpaRepository<AgentMtSendStat, Long> {
}
