package com.project.wordGenerator;

import com.project.wordGenerator.application.FilterFileService;
import com.project.wordGenerator.application.IFilterFileService;
import com.project.wordGenerator.application.IWordService;
import com.project.wordGenerator.application.WordService;
import com.project.wordGenerator.domain.FilterFileOnStartup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class validateWordTest {
    private static FilterFileOnStartup filterFileOnStartup;

    @BeforeAll
    static void beforeAll(){
        filterFileOnStartup = mock(FilterFileOnStartup.class);
    }

    @Test
    @DisplayName("Validate legal user input words")
    void acceptWordsBetween5And7Letters() throws NoSuchAlgorithmException {
        //Arrange
        IFilterFileService filterFileService = new FilterFileService();
        IWordService wordService = new WordService(filterFileService);
        String word5 = "woord";
        String word6 = "zweven";
        String word7 = "bloemen";

        //Act
        boolean accepts5Letter = wordService.validate(word5);
        boolean accepts6Letter = wordService.validate(word6);
        boolean accepts7Letter = wordService.validate(word7);

        //Assert
        assertTrue(accepts5Letter);
        assertTrue(accepts6Letter);
        assertTrue(accepts7Letter);
    }

    @Test
    @DisplayName("Validate illegal user input word")
    void failWordsBetween5And7Letters() throws NoSuchAlgorithmException {
        //Arrange
        IFilterFileService filterFileService = new FilterFileService();
        IWordService wordService = new WordService(filterFileService);
        String word5 = "W:ADS";
        String word6 = "?&*@#";
        String word7 = "flowers";

        //Act
        boolean accepts5Letter = wordService.validate(word5);
        boolean accepts6Letter = wordService.validate(word6);
        boolean accepts7Letter = wordService.validate(word7);

        //Assert
        assertFalse(accepts5Letter);
        assertFalse(accepts6Letter);
        assertFalse(accepts7Letter);
    }

    @Test
    void successfulOnConstruct() throws IOException {
        FilterFileOnStartup filterFileOnStartup = new FilterFileOnStartup();
        //verify(filterFileOnStartup, atMost(1).filter);
        filterFileOnStartup.filterFileOnStartup();
    }


}
