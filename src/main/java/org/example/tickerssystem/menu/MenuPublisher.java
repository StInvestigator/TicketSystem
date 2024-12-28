package org.example.tickerssystem.menu;

import java.util.List;

public class MenuPublisher {

    private static final String ACTION_STRING = "To do action press the number";
    private static final String CREATE_CUSTOMER = "Create a customer";
    private static final String SHOW_FREE_TICKETS_ON_EVENT = "Show free tickets on event";
    private static final String SHOW_EVENTS_IN_NEXT_2_WEEKS = "Show events in next two weeks";
    private static final String ASSIGN_TICKET_TO_CUSTOMER = "Assign ticket to customer";
    private static final String SHOW_ASSIGNED_TICKETS = "Show customer`s tickets";
    private static final String TEST_FOR_BUGS = "Test program for bugs";
    private static final String INVATION_STRING = "Please enter the number";
    private static final String EXIT_STRING = "Close the program";
    private static final String SEPARATOR = "-";
    private static final String DOT_SPACE = ".  ";
    private static final String END_LINE = "\n";

    public static void showMenu() {
        String resultString = ACTION_STRING +
                END_LINE +
                showStringList(List.of(CREATE_CUSTOMER, SHOW_FREE_TICKETS_ON_EVENT,
                        SHOW_EVENTS_IN_NEXT_2_WEEKS, ASSIGN_TICKET_TO_CUSTOMER,
                        SHOW_ASSIGNED_TICKETS, TEST_FOR_BUGS)) +
                -1 +
                DOT_SPACE +
                EXIT_STRING +
                END_LINE +
                SEPARATOR.repeat(60) +
                END_LINE +
                INVATION_STRING;
        System.out.println(resultString);
    }

    public static String showStringList(List<String> sourceStringList) {
        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var currentString : sourceStringList) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append(currentString)
                    .append(END_LINE);
        }
        resultString.append(END_LINE);

        return resultString.toString();
    }

    private MenuPublisher() {
    }
}
