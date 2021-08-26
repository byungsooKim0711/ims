package org.kimbs.ims.repository.stat;

import org.kimbs.ims.domain.stat.AgentEmailSendStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentEmailSendStatRepository extends JpaRepository<AgentEmailSendStat, Long> {
}
