package ru.otus.spring03;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring03.domain.Student;
import ru.otus.spring03.service.ConsoleService;
import ru.otus.spring03.service.ConsoleServiceImpl;
import ru.otus.spring03.service.IOService;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("Класс ConsoleServiceImpl")
class ConsoleServiceImplTest {

    private final String ASK_STUDENT_NAME = "Введите ФИО";
    private final String WRONG_ASK_STUDENT_NAME = "Введите ФИО2";
    private final String STUDENT_NAME = "Петров";
    private final String WRONG_STUDENT_NAME = "Петров1";

    @MockBean
    private MessageSource messageMock;

    @MockBean
    private IOService ioMock;

    @Autowired
    private ConsoleServiceImpl console;


    @Test
    @DisplayName("askStudentInfo")
    void askStudentInfoTest() {
        Mockito.when(ioMock.readString()).thenReturn(STUDENT_NAME);

        Student student = console.askStudentInfo();

        verify(ioMock, Mockito.times(1)).printOut(ASK_STUDENT_NAME);
        //verify(messageMock, Mockito.times(1)).getMessage(Mockito.anyString(), Mockito.any(), Mockito.any());

        assertThat(student)
                .hasFieldOrPropertyWithValue("name", STUDENT_NAME);
    }
}