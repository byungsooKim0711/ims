package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.CustomerRouting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRoutingRepository extends JpaRepository<CustomerRouting, Long> {
}
