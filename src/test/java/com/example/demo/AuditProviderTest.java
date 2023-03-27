package com.example.demo;

import com.example.demo.base.BaseRepositoryTest;
import com.example.demo.config.TestAuditingConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

class AuditProviderTest extends BaseRepositoryTest {

    @Autowired
    private AuditorAware<String> auditorAware;

    @Test
    @DisplayName("Validates the current test auditor.")
    void validateAuditor() {
        String currentAuditor = auditorAware.getCurrentAuditor()
                .orElse(null);

        Assertions.assertEquals(TestAuditingConfig.TEST_AUDITOR, currentAuditor);
    }
}
