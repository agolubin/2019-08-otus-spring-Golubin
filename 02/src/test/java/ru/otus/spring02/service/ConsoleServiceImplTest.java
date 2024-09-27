package ru.otus.spring02.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import ru.otus.spring02.domain.Student;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DisplayName("Класс ConsoleServiceImpl")
class ConsoleServiceImplTest {

    @Test
    @DisplayName("askStudentInfo")
    void askStudentInfoTest() {
        IOService ioMock = mock(IOService.class);
        MessageSource messageMock = mock(MessageSource.class);
        ConsoleService console = new ConsoleServiceImpl(ioMock, messageMock);
        Mockito.when(ioMock.readString()).thenReturn("Петров");
        Mockito.doNothing().when(ioMock).printOut(Mockito.anyString());

        Mockito.when(messageMock.getMessage(Mockito.anyString(), Mockito.any(), Mockito.any(Locale.class))).thenReturn("Ваша ФИО");

        Student student = console.askStudentInfo();
        assertThat(student)
                .hasFieldOrPropertyWithValue("name", "Петров");

    }

    @Test
    @DisplayName("askQuestion")
    void askQuestionTest() {
    }

    @Test
    @DisplayName("printResult")
    void printResultTest() {
    }
}