package org.kimbs.ims.repository;

import org.kimbs.ims.domain.RoutingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutingGroupRepository extends JpaRepository<RoutingGroup, Long> {
}
