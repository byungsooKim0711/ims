package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.SendProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendProfileRepository extends JpaRepository<SendProfile, Long> {
}
