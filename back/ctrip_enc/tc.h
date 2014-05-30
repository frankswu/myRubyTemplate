#include <jni.h>

#ifndef _Included_testdll
#define _Included_testdll

JNIEXPORT jbyteArray JNICALL Java_<%= value['encode_util_package'] %>_<%= value['encode_util_name'] %>_ctripEnc(JNIEnv *env, jobject jobj, jbyteArray in_byte, jint byte_len);

JNIEXPORT jbyteArray JNICALL Java_<%= value['encode_util_package'] %>_<%= value['encode_util_name'] %>_ctripDec(JNIEnv *env, jobject jobj, jbyteArray in_byte, jint byte_len);

JNIEXPORT jchar JNICALL Java_<%= value['encode_util_package'] %>_<%= value['encode_util_name'] %>_print(JNIEnv *env, jobject obj);

#endif
