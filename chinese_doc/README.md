**使用说明**

**添加请求库到gradle,appcompat包必须在 project gradle里面添加:**

      maven { url 'https://jitpack.io' }
      
      compile 'com.github.hongyangJia:RxKotlinPermission:1.1.6'
      
      ext {
          appcompat = 'com.android.support:appcompat-v7:xx.x.x'
      }

**发起请求权限**
    
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
 
**自定义对话框title message提示语言,自定义对话框可参考DefaultTemplate实现类 :**
     
           KtPermissionSetting.INSTANCE.Setting(
                   new KtRequest.Builder().title("title").messag e("message")
                   .rxDialog(new DefaultTemplate(this)).build(this));
