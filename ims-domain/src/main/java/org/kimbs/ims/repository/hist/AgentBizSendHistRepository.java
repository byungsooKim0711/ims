package org.kimbs.ims.repository.hist;

import org.kimbs.ims.domain.hist.AgentBizSendHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentBizSendHistRepository extends JpaRepository<AgentBizSendHist, Long> {
}
