package org.kimbs.ims.repository.hist;

import org.kimbs.ims.domain.hist.ApiMtSendHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiMtSendHistRepository extends JpaRepository<ApiMtSendHist, Long> {
}
