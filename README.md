**Description**

 Android Kotlin Request permission library,Internal implementation request permission, successful ||  failure interface interaction

 Support internationalization(English Chinese)
 
**Getting started**

**The first step is to include RxKotlinPermission into your project, for example, as a Gradle compile dependency:**

      maven { url 'https://jitpack.io' }
      compile 'com.github.hongyangJia:RxKotlinPermission:1.1.0'

**write the request permission program(Consumer impl):**

  kotlin style:
    
       var rxKotlinPermission: RxKotlinPermission = RxKotlinPermission(this)
                 rxKotlinPermission.requestCamera().subscribe { rxInteractive ->
                       /**
                         * success
                         */
                }

  java style:
                
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
 
 **write the request permission program(Observer impl):**

 java style and kotlin style:
     
      rxKotlinPermission.requestCamera().subscribe(new Observer<RxInteractive>() {
                 @Override
                 public void onSubscribe(Disposable d) {
     
                 }
     
                 @Override
                 public void onNext(RxInteractive rxLimit) {
                         /**
                           * success
                          */
                 }
     
                 @Override
                 public void onError(Throwable e) {
     
                 }
     
                 @Override
                 public void onComplete() {
     
                 }
             });
             
 **Hide the interaction  Default Display:**

kotlin style:
       
         rxKotlinPermission.setCancelInteractive();
         rxKotlinPermission.requestCamera().subscribe(Consumer<RxInteractive> { rxInteractive ->
                   when (rxInteractive.rxMode) {
                       RxGracePermission.RxMode.GRACE_RECRY -> Log.e(TAG, "GRACE_RECRY")
                       RxGracePermission.RxMode.GRACE_ALLOW -> Log.e(TAG, "GRACE_ALLOW")
                       RxGracePermission.RxMode.GRACE_REFUSE -> Log.e(TAG, "GRACE_REFUSE")
                       RxGracePermission.RxMode.GRACE_HIDE -> Log.e(TAG, "GRACE_HIDE")
                   }
               })
     
java style:

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

**Custom request permission:**
 
 java style:
 
       rxKotlinPermission.request(Manifest.permission.WRITE_SETTINGS,
                       Manifest.permission.WRITE_SETTINGS).subscribe(new Consumer<RxInteractive>() {
                   @Override
                   public void accept(@NonNull RxInteractive rxInteractive) throws Exception {
                    
               });
 

      
 