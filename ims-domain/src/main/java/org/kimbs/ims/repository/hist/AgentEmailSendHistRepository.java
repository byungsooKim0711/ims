package org.kimbs.ims.repository.hist;

import org.kimbs.ims.domain.hist.AgentEmailSendHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentEmailSendHistRepository extends JpaRepository<AgentEmailSendHist, Long> {
}
