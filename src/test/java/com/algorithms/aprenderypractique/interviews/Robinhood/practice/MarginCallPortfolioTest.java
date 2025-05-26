package com.algorithms.aprenderypractique.interviews.Robinhood.practice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarginCallPortfolioTest {

    @ParameterizedTest
    @MethodSource("provideTradesAndExpectedPortfolios")
    void testProcessTrades(List<List<String>> inputTrades, List<List<String>> expectedPortfolio) {
        MarginCallPortfolio obj = new MarginCallPortfolio();
        List<List<String>> actual = obj.parseTrade(inputTrades);
        assertEquals(expectedPortfolio, actual);
    }

    static Stream<Arguments> provideTradesAndExpectedPortfolios() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                List.of("1", "AAPL", "B", "5", "75"),
                                List.of("2", "GOOG", "B", "5", "100"),
                                List.of("3", "AAPLO", "B", "5", "50")
                        ),
                        List.of(
                                List.of("CASH", "25"),
                                List.of("AAPLO", "5"),
                                List.of("AAPL", "5"),
                                List.of("GOOG", "3")
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of("1", "AAPL", "B", "10", "100"),
                                List.of("2", "AAPL", "S", "2", "80"),
                                List.of("3", "GOOG", "B", "15", "20")
                        ),
                        List.of(
                                List.of("CASH", "20"),
                                List.of("AAPL", "6"),
                                List.of("GOOG", "15")
                        )
                )
        );
    }
}
