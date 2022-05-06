package hello.itemservice.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test()
    @DisplayName("메시지 기본 출력")
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    @DisplayName("해당하는 메시지가 없을 경우")
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @DisplayName("해당하는 메시지가 없을 경우, 기본 메시지 할당")
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    @DisplayName("메시지 매개변수 사용")
    void argumentMessage() {
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    @DisplayName("국제화 기본")
    void defaultLang() {
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    @DisplayName("국제화 영어")
    void enLang() {
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
        assertThat(ms.getMessage("hello.name", new Object[]{"Spring!"}, Locale.ENGLISH)).isEqualTo("hello Spring!");
    }
}
