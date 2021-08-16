package org.kimbs.ims.store.repository;

import org.kimbs.ims.store.domain.KakaoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoTemplateRepository extends JpaRepository<KakaoTemplate, Long> {
}
