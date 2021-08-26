package org.kimbs.ims.repository.hist;

import org.kimbs.ims.domain.hist.AgentMtSendHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentMtSendHistRepository extends JpaRepository<AgentMtSendHist, Long> {
}
