**Description**

 Android Kotlin Request permission library,Internal implementation request permission, successful ||  failure interface interaction

 Support internationalization(English Chinese) 

**Support Kotlin**

For more information please see the https://github.com/hongyangJia/RxPermission.

**Getting started**

**The first step is to include RxKotlinPermission into your project, for example, as a Gradle compile dependency:**


      maven { url 'https://jitpack.io' }
      compile 'com.github.hongyangJia:RxKotlinPermission:1.1.5'
      
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

**write the request rx permission program**
    
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

**Custom title or message or dialog:**
     
           KtPermissionSetting.INSTANCE.Setting(
                   new KtRequest.Builder().title("title").message("message")
                   .rxDialog(new DefaultTemplate(this)).build(this));

 
 