package org.kimbs.ims.repository;

import org.kimbs.ims.domain.KakaoTemplateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoTemplateRequestRepository extends JpaRepository<KakaoTemplateRequest, Long> {
}
