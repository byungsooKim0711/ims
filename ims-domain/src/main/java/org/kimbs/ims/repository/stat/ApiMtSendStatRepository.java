package org.kimbs.ims.repository.stat;

import org.kimbs.ims.domain.stat.ApiMtSendStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiMtSendStatRepository extends JpaRepository<ApiMtSendStat, Long> {
}
