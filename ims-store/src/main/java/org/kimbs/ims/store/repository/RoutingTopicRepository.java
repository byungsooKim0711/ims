package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.RoutingTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutingTopicRepository extends JpaRepository<RoutingTopic, Long> {
}
