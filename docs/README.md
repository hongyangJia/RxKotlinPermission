**Getting started**

**The first step is to include RxKotlinPermission into your project, for example, as a Gradle compile dependency:**

      maven { url 'https://jitpack.io' }
      
      compile 'com.github.hongyangJia:RxKotlinPermission:1.1.8'
      
      ext {
          appcompat = 'com.android.support:appcompat-v7:xx.x.x'
      }

**write the request normal permission program**
    
       KtPermission permission = new KtPermission(this);
           permission.requestCamera().launcher(new LaunchTask() {
               @Override
               public void launch(boolean b) {
                       if (b){
                         /**
                           * success
                           */
                     }
               }
           });
 
**Custom title or message or dialog(reference DefaultTemplate class):**
     
           KtPermissionSetting.INSTANCE.Setting(
                   new KtRequest.Builder().title("title").messag e("message")
                   .rxDialog(new DefaultTemplate(this)).build(this));
