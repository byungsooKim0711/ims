package org.kimbs.ims.repository.stat;

import org.kimbs.ims.domain.stat.ApiBizSendStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiBizSendStatRepository extends JpaRepository<ApiBizSendStat, Long> {
}
