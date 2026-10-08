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
        var sectionId = Section.Id.generate();
        var section = new GeneralAdmission(
            sectionId,
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
