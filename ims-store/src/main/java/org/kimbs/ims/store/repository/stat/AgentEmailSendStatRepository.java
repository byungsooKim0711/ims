package org.kimbs.ims.store.repository.stat;

import org.kimbs.ims.store.domain.stat.AgentEmailSendStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentEmailSendStatRepository extends JpaRepository<AgentEmailSendStat, Long> {
}
