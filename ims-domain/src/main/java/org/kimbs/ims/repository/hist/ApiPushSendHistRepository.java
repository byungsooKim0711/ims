package org.kimbs.ims.repository.hist;

import org.kimbs.ims.domain.hist.ApiPushSendHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiPushSendHistRepository extends JpaRepository<ApiPushSendHist, Long> {
}
