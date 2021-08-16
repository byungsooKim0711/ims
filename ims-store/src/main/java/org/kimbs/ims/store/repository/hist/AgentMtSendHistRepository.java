package org.kimbs.ims.store.repository.hist;

import org.kimbs.ims.store.domain.hist.AgentMtSendHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentMtSendHistRepository extends JpaRepository<AgentMtSendHist, Long> {
}
