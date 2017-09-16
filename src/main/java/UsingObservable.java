import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class UsingObservable {

    private Console console;

    public UsingObservable(Console console) {
        this.console = console;
    }

    public void singleEvent(String message) {
        Observable.just(message)
            .subscribe(console::println);
    }

    public void iterableEvents(List<String> messages) {
        Observable.fromIterable(messages)
            .subscribe(console::println);
    }

    public void rangeEvents(int start, int count) {
        Observable.range(start, count)
            .subscribe(console::println);
    }

    public void zipEventsWithCounter(List<String> messages) {
        Observable.fromIterable(messages)
            .zipWith(Observable.range(1, Integer.MAX_VALUE), (string, count) -> String.format("%2d. %s", count, string))
            .subscribe(console::println);
    }

    public void mergeEvents(List<String> firstMessageGroup, List<String> secondMessageGroup) {
        Observable.merge(
            Observable.fromIterable(firstMessageGroup),
            Observable.fromIterable(secondMessageGroup)
        ).subscribe(console::println);
    }

}
