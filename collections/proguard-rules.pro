# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# Common
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-keepattributes *Annotation*
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepattributes InnerClasses
-keepattributes EnclosingMethod

-dontwarn javax.annotation.**

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

