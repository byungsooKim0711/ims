package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.ServiceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceKeyRepository extends JpaRepository<ServiceKey, Long> {
}
