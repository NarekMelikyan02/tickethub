package com.tickethub.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SectionTests
{

    SeatedSection seatedSection;

    GeneralAdmission generalAdmission;

    Section.Id generalAdmissionId = Section.Id.generate();

    Section.Id seatedSectionId = Section.Id.generate();

    @BeforeEach
    void setUp()
    {

        seatedSection = new SeatedSection(
            seatedSectionId,
            "Hall 1",
            List.of(
                new SeatedRow("A", 14),
                new SeatedRow("B", 14)
            )
        );

        generalAdmission = new GeneralAdmission(
            generalAdmissionId,
            "Bar area",
            50
        );
    }

    @Test
    void shouldCorrectlyConstructSection()
    {
        //given
        Section.Id generate = Section.Id.generate();

        //then
        assertThat(generalAdmission).hasFieldOrProperty("id");
    }
}
