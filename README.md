**Description**

 Android Kotlin Request permission library,Internal implementation request permission, successful ||  failure interface interaction
                
**Getting started**

**The first step is to include RxKotlinPermission into your project, for example, as a Gradle compile dependency:**

    maven { url 'https://jitpack.io' }
      compile 'com.github.hongyangJia:RxKotlinPermission:1.0'

 
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
       rxKotlinPermission.requestCamera().subscribe(new Consumer<RxInteractive>() {
                   @Override
                   public void accept(@NonNull RxInteractive rxInteractive) throws Exception {
                       switch (rxInteractive.getRxMode()) {
                           case GRACE_RECRY:
                               Log.e(TAG, "GRACE_RECRY");
                               break;
                           case GRACE_ALLOW:
                               Log.e(TAG, "GRACE_ALLOW");
                               break;
                           case GRACE_REFUSE:
                               Log.e(TAG, "GRACE_REFUSE");
                               break;
                           case GRACE_HIDE:
                               Log.e(TAG, "GRACE_HIDE");
                               break;
                       }
                   }
               });
       
 
   
Custom request permission:


       rxKotlinPermission.request(Manifest.permission.WRITE_SETTINGS,
                       Manifest.permission.WRITE_SETTINGS).subscribe(new Consumer<RxInteractive>() {
                   @Override
                   public void accept(@NonNull RxInteractive rxInteractive) throws Exception {
                    
               });
 

      
 