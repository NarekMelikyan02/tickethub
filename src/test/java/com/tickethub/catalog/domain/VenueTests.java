package com.tickethub.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.tickethub.exception.IllegalOperationException;
import org.junit.jupiter.api.Test;

public class VenueTests
{

    private Venue venue;

    private final Venue.Id venueId = Venue.Id.generate();

    @Test
    void shouldCorrectlyConstructVenueObject()
    {
        //given
        var section = new GeneralAdmission(
            Section.Id.generate(),
            "Standing Bar Zone",
            25
        );

        venue = new Venue(
            venueId,
            "Yerevan",
            "Karen Demirchyan Hall",
            ZonedDateTime.of(
                LocalDateTime.of(
                    2026,
                    8,
                    20,
                    14,
                    0
                ),
                ZoneId.of("Asia/Yerevan")
            ),
            List.of(section)
        );

        //then
        assertThat(venue.id()).isEqualTo(venueId);
        assertThat(venue.city()).isEqualTo("Yerevan");
        assertThat(venue.name()).isEqualTo("Karen Demirchyan Hall");
        assertThat(venue.timeZone()).isEqualTo(ZonedDateTime.of(
            LocalDateTime.of(
                2026,
                8,
                20,
                14,
                0
            ),
            ZoneId.of("Asia/Yerevan")
        ));
        assertThat(venue.sections()).containsExactly(section);
    }

    @Test
    void shouldThrowWhenSectionHasDuplicateName()
    {
        //given
        var seatedSection = new SeatedSection(
            Section.Id.generate(),
            "Hall 1",
            List.of(
                new SeatedRow("A", 14),
                new SeatedRow("B", 38)
            )
        );

        var dupeSeatedSection = new SeatedSection(
            Section.Id.generate(),
            "Hall 1",
            List.of(
                new SeatedRow("A", 14)
            )
        );

        //then
        assertThatThrownBy(() ->
            new Venue(
                venueId,
                "Yerevan",
                "Karen Demirchyan Hall",
                ZonedDateTime.of(
                    LocalDateTime.of(
                        2026,
                        8,
                        20,
                        14,
                        0
                    ),
                    ZoneId.of("Asia/Yerevan")
                ),
                List.of(dupeSeatedSection, seatedSection)
            )).isExactlyInstanceOf(IllegalOperationException.class);
    }

    @Test
    void shouldThrowWhenNullValuesDuringVenueConstruction()
    {
        //then
        assertThatThrownBy(() -> venue = new Venue(
                null,
                null,
                null,
                null,
                null
            )
        ).isExactlyInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldCorrectlyCalculateTotalCapacity()
    {

        //given
        var genSection = new GeneralAdmission(
            Section.Id.generate(),
            "Standing Bar Zone",
            25
        );

        var seatedSection = new SeatedSection(
            Section.Id.generate(),
            "Hall 1",
            List.of(
                new SeatedRow("A", 14),
                new SeatedRow("B", 38)
            )
        );

        venue = new Venue(
            venueId,
            "Yerevan",
            "Karen Demirchyan Hall",
            ZonedDateTime.of(
                LocalDateTime.of(
                    2026,
                    8,
                    20,
                    14,
                    0
                ),
                ZoneId.of("Asia/Yerevan")
            ),
            List.of(genSection, seatedSection)
        );

        //when
        var totalCapacity = venue.totalCapacity();

        //then
        assertThat(totalCapacity).isEqualTo(77);
    }

    @Test
    void shouldThrowWhenSectionsIsEmpty()
    {
        //then
        assertThatThrownBy(() -> venue = new Venue(
                venueId,
                "Yerevan",
                "Karen Demirchyan Hall",
                ZonedDateTime.of(
                    LocalDateTime.of(
                        2026,
                        8,
                        20,
                        14,
                        0
                    ),
                    ZoneId.of("Asia/Yerevan")
                ),
                List.of()
            )
        ).isExactlyInstanceOf(IllegalOperationException.class);
    }
}
