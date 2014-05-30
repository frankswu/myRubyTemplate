//
//  test.c
//  Enc
//
//  Created by jimzhao on 13-12-2.
//  Copyright (c) 2013骞�jimzhao. All rights reserved.
//

#include <stdio.h>
#include <string.h>
#include <sys/file.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <math.h>
#include <time.h>
#include <unistd.h>
#include <jni.h>
#include <android/log.h>
#include "enc.h"

#ifndef Ctrip_Enc_Jni
#define Ctrip_Enc_Jni

void checkTimeMethod(JNIEnv *env)
{
   printf("this is a print, ok\n");
   //__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","this is a print, ok\n");

   jclass myClass = (*env)->FindClass(env,"<%= value['check_package_name'] %>");
   if ((*env)->ExceptionCheck(env))
   {
   	return ;
   }
   if (myClass == 0 || myClass == NULL) {
	   //__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","MainActivity is null\n");
  	  return ;
   }
   jmethodID timeMethod = (*env)->GetStaticMethodID(env,myClass,"<%= value['check_method_name'] %>","()J");
   if ((*env)->ExceptionCheck(env))
   {
   	return ;
   }
   if(timeMethod == 0|| timeMethod == NULL){
	   //__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","timeMethod is null\n");
	   return ;
   } 

	//__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","CallStaticLongMethodl\n");
   jlong time = (*env)->CallStaticLongMethod(env,myClass,timeMethod );
   //__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","the system time for java [%lld]",time);
    return ;
 }

/**
 * �ctrip���
 */

JNIEXPORT jbyteArray JNICALL Java_<%= value['encode_util_package'] %>_<%= value['encode_util_name'] %>_ctripEnc(JNIEnv *env, jobject jobj, jbyteArray in_byte, jint byte_len) {
    checkTimeMethod(env);
	unsigned char *re_out_bytes = 0;
	jboolean *bl = 0;
	jbyte* bytes = (*env)->GetByteArrayElements(env, in_byte, bl);
	int encLen = ctrip_enc(bytes, byte_len, &re_out_bytes);
	if (encLen) {
		jbyteArray tempArray = (*env)->NewByteArray(env, encLen);
		(*env)->SetByteArrayRegion(env, tempArray, 0, encLen,
				(jbyte*) re_out_bytes);
		if (re_out_bytes) {
			free(re_out_bytes);
			re_out_bytes = 0;
		}
		if (bytes) {
			(*env)->ReleaseByteArrayElements(env, in_byte, bytes, JNI_FALSE);
		}
		return tempArray;
	}

//	printf("Excute error");
	return NULL;
}

/**
 * �ctrip瑙ｅ�
 */

JNIEXPORT jbyteArray JNICALL Java_<%= value['encode_util_package'] %>_<%= value['encode_util_name'] %>_ctripDec(JNIEnv *env, jobject jobj, jbyteArray in_byte, jint byte_len) {
    checkTimeMethod(env);
	unsigned char *re_out_bytes = 0;
	jboolean *bl = 0;
	jbyte* bytes = (*env)->GetByteArrayElements(env, in_byte, bl);
	int encLen = ctrip_dec(bytes, byte_len, &re_out_bytes);
	if (encLen) {
		jbyteArray tempArray = (*env)->NewByteArray(env, encLen);
		(*env)->SetByteArrayRegion(env, tempArray, 0, encLen,
				(jbyte*) re_out_bytes);
		if (re_out_bytes) {
			free(re_out_bytes);
			re_out_bytes = 0;
		}
		if (bytes) {
			(*env)->ReleaseByteArrayElements(env, in_byte, bytes, JNI_FALSE);
		}
		return tempArray;
	}
//	LOGE("Excute error");
	return NULL;
}

 JNIEXPORT jchar JNICALL Java_<%= value['encode_util_package'] %>_<%= value['encode_util_name'] %>_print(JNIEnv *env, jobject obj)
 {
   printf("this is a print, ok\n");
   //__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","this is a print, ok\n");
   // jclass null_exception = (*env)->FindClass(env,"java/lang/NullPointerException");

   jclass myClass = (*env)->FindClass(env,"com/example/increamentdatapull/MainActivity1");
   if ((*env)->ExceptionCheck(env))
   {
   	return 1;
   }
   if (myClass == 0 || myClass == NULL) {
	   //__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","MainActivity is null\n");
   	  // (*env)->ThrowNew(env,null_exception,"");
  	  return -1;
   }
   jmethodID timeMethod = (*env)->GetStaticMethodID(env,myClass,"<%= value['check_method_name'] %>","()J");
   if ((*env)->ExceptionCheck(env))
   {
   	return 1;
   }
   if(timeMethod == 0|| timeMethod == NULL){
	   //__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","timeMethod is null\n");
   	  // (*env)->ThrowNew(env,null_exception,"");
	   return 1;
   } 

	//__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","CallStaticLongMethodl\n");
   jlong time = (*env)->CallStaticLongMethod(env,myClass,timeMethod );
   //__android_log_print(ANDROID_LOG_VERBOSE,"MyTag","the system time for java [%lld]",time);

   return 118;
 }



#endif
