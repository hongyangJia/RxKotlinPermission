package rxkotlin.grace.permission;

import rxkotlin.grace.permission.ui.IRxDialog;
import rxkotlin.grace.permission.ui.RxDialog;

/**
 * Created by hongyang on 17-5-26.
 */

public class RxRequest {

    private String title;
    private String message;
    private String rxDialog;

    public Builder newBuilder() {
        return new Builder();
    }

    private RxRequest(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.rxDialog = builder.rxDialog;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getRxDialog() {
        return rxDialog;
    }

    public static class Builder {

        private String title = RxTool.INSTANCE.getTITLE();
        private String message = RxTool.INSTANCE.getMESSAGE();
        private String rxDialog = RxDialog.class.getCanonicalName();

        public Builder title(String title) {
            if (title == null) throw new NullPointerException("title == title");
            this.title = title;
            return this;
        }

        public Builder message(String message) {
            if (message == null) throw new NullPointerException("message == message");
            this.message = message;
            return this;
        }

        public Builder rxDialog(IRxDialog rxDialog) {
            if (rxDialog == null) throw new NullPointerException("rxDialog == rxDialog");
            this.rxDialog = IRxDialog.class.getCanonicalName();
            return this;
        }

        public RxRequest build() {
            return new RxRequest(this);
        }
    }
}
