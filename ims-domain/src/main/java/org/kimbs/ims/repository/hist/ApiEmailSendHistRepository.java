package org.kimbs.ims.repository.hist;

import org.kimbs.ims.domain.hist.ApiEmailSendHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiEmailSendHistRepository extends JpaRepository<ApiEmailSendHist, Long> {
}
