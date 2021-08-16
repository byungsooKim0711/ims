package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.ApiRouting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRoutingRepository extends JpaRepository<ApiRouting, Long> {
}
