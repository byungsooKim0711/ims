package org.kimbs.ims.repository;

import org.kimbs.ims.domain.SendProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendProfileRepository extends JpaRepository<SendProfile, Long> {
}
