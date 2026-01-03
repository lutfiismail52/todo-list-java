package todo.model;

public class TodoItem {
    private String text;
    private boolean done;

    public TodoItem(String text) {
        this.text = text;
        this.done = false;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return text;
    }
}
