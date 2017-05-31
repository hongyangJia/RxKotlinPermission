**Description**

 Android Kotlin Request permission library,Internal implementation request permission, successful ||  failure interface interaction
                
**Getting started**

**The first step is to include xxxxx into your project, for example, as a Gradle compile dependency:**

    maven { url 'https://jitpack.io' }
    compile 'com.github.hongyangJia:AndroidPermissions:1.0.2'

 
 write the request permission program(Consumer impl):

          RxKotlinPermission rxKotlinPermission;
          rxKotlinPermission = new RxKotlinPermission(this);
          rxKotlinPermission.requestCamera().subscribe(new Consumer<RxInteractive>() {
                    @Override
                    public void accept(@NonNull RxInteractive rxInteractive) throws Exception {
                        /**
                         * success
                         */
                    }
                });

 
 write the request permission program(Observer impl):

      rxKotlinPermission.requestCamera().subscribe(new Observer<RxInteractive>() {
                 @Override
                 public void onSubscribe(Disposable d) {
     
                 }
     
                 @Override
                 public void onNext(RxInteractive rxLimit) {
                    
                 }
     
                 @Override
                 public void onError(Throwable e) {
     
                 }
     
                 @Override
                 public void onComplete() {
     
                 }
             });
             
  
Hide the interaction  Default Display:

       rxKotlinPermission.setCancelInteractive();
 