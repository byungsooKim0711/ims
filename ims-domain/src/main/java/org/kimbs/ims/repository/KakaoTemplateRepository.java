package org.kimbs.ims.repository;

import org.kimbs.ims.domain.KakaoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoTemplateRepository extends JpaRepository<KakaoTemplate, Long> {
}
