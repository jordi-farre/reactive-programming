import io.reactivex.Observable;

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

}
