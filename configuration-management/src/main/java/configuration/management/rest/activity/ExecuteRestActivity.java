package configuration.management.rest.activity;

public class ExecuteRestActivity<T1, T2> implements Runnable {

    private Activity<T1, T2> activity;

    private T2 item;

    public ExecuteRestActivity(Activity<T1, T2> activity, T2 item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public void run() {
        activity.doStep(item);
    }

}
