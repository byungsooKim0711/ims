package org.kimbs.ims.repository.stat;

import org.kimbs.ims.domain.stat.AgentBizSendStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentBizSendStatRepository extends JpaRepository<AgentBizSendStat, Long> {
}
