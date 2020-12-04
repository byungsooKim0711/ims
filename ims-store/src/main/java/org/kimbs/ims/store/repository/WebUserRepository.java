package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebUserRepository extends JpaRepository<WebUser, Long> {
}
