package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.RoutingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutingGroupRepository extends JpaRepository<RoutingGroup, Long> {
}
