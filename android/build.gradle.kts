plugins {                                                                                                                                                                          
    id("com.android.library")                                                                                                                                                      
    id("org.jetbrains.kotlin.android")                                                                                                                                             
}                                                                                                                                                                                  
                                                                                                                                                                                     
android {                                                                                                                                                                          
    namespace = "org.lichess.chessground.assets"                                                                                                                                   
    compileSdk = 36                                                                                                                                                                
                                                                                                                                                                                     
    defaultConfig {
        minSdk = 21                                                                                                                                                                
    }                                                                                                                                                                              
                                                                                                                                                                                     
    compileOptions {                                                                                                                                                               
        sourceCompatibility = JavaVersion.VERSION_11                                                                                                                               
        targetCompatibility = JavaVersion.VERSION_11                                                                                                                               
    }                                                                                                                                                                              
}                                                                                                                                                                                  
                                                                                                                                                                                     
kotlin {                                                                                                                                                                           
    jvmToolchain(11)                                                                                                                                                               
}                                                                                                                                                                                               