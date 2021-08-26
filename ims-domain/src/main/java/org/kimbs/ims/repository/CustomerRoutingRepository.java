package org.kimbs.ims.repository;

import org.kimbs.ims.domain.CustomerRouting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRoutingRepository extends JpaRepository<CustomerRouting, Long> {
}
