package rxkotlin.grace.permission.date;

import android.content.Context;

import rxkotlin.grace.permission.ui.Template;
import rxkotlin.grace.permission.ui.DefaultTemplate;

/**
 * Created by hongyang on 17-5-26.
 */

public class KtRequest {

    private Context context;

    private KtRequest(Builder builder) {
        this.context = builder.context;
        DatePersistence.INSTANCE.build(builder.context, builder.title, builder.message, builder.rxDialog);
    }

    public String getTitle() {
        return DatePersistence.INSTANCE.getTitle(context);
    }

    public String getMessage() {
        return DatePersistence.INSTANCE.getMessage(context);
    }

    public String getRxDialog() {
        return DatePersistence.INSTANCE.getDialog(context);
    }

    public static class Builder {
        private static final String DEFULT = "defult";
        private String title = DEFULT;
        private String message = DEFULT;
        private String rxDialog = DefaultTemplate.class.getCanonicalName();
        private Context context;

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

        public Builder rxDialog(Template rxDialog) {
            if (rxDialog == null) throw new NullPointerException("rxDialog == rxDialog");
            this.rxDialog = Template.class.getCanonicalName();
            return this;
        }

        public KtRequest build(Context context) {
            this.context = context;
            return new KtRequest(this);
        }
    }

}
