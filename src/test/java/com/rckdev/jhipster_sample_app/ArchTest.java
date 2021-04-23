package com.rckdev.jhipster_sample_app;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.rckdev.jhipster_sample_app");

        noClasses()
            .that()
            .resideInAnyPackage("com.rckdev.jhipster_sample_app.service..")
            .or()
            .resideInAnyPackage("com.rckdev.jhipster_sample_app.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.rckdev.jhipster_sample_app.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
