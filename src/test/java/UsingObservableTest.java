import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


@RunWith(MockitoJUnitRunner.class)
public class UsingObservableTest {

    @Mock
    private Console console;

    private UsingObservable usingObservable;

    @Before
    public void initialize() {
        this.usingObservable = new UsingObservable(this.console);
    }

    @Test
    public void singleEvent() throws Exception {
        String message = "testing";

        this.usingObservable.singleEvent(message);

        Mockito.verify(this.console).println(message);
    }

    @Test
    public void iterableEvents() {
        List<String> messageList = Arrays.asList("learning", "reactive", "programming", "with", "rxjava");

        this.usingObservable.iterableEvents(messageList);

        messageList.stream()
                .forEach(message -> Mockito.verify(console).println(message));
    }

    @Test
    public void range_events() {
        int start = 5;
        int count = 20;

        this.usingObservable.rangeEvents(start, count);

        IntStream.range(start, start + count)
                .forEach(current -> Mockito.verify(console).println(current));
    }

    @Test
    public void zipEventsWithCounter() {
        List<String> messageList = Arrays.asList("learning", "reactive", "programming", "with", "rxjava");

        this.usingObservable.zipEventsWithCounter(messageList);

        AtomicInteger atomicInteger = new AtomicInteger(1);
        messageList.stream()
                .forEach(message -> Mockito.verify(console).println(String.format("%2d. %s", atomicInteger.getAndIncrement(), message)));
    }

    @Test
    public void mergeEvents() {
        List<String> messageList1 = Arrays.asList("learning", "reactive", "programming", "with", "rxjava");
        List<String> messageList2 = Arrays.asList("trying", "merge", "function");

        this.usingObservable.mergeEvents(messageList1, messageList2);

        messageList1.stream()
                .forEach(message -> Mockito.verify(console).println(message));
        messageList2.stream()
                .forEach(message -> Mockito.verify(console).println(message));
    }

}