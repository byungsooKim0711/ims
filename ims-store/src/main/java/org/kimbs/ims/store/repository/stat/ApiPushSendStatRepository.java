package org.kimbs.ims.store.repository.stat;

import org.kimbs.ims.store.domain.stat.ApiPushSendStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiPushSendStatRepository extends JpaRepository<ApiPushSendStat, Long> {
}
