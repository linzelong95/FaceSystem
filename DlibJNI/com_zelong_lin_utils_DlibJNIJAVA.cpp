#include "stdafx.h"
#include "com_zelong_lin_utils_DlibJNIJAVA.h"
#include <iostream>
#include <stdio.h>
#include <dlib/image_processing/frontal_face_detector.h>
#include <dlib/image_processing/render_face_detections.h>
#include <dlib/image_processing.h>
#include <dlib/gui_widgets.h>
#include <dlib/image_io.h>
#include "jni.h"

using namespace dlib;
using namespace std;

JNIEXPORT jdoubleArray JNICALL Java_com_zelong_lin_utils_DlibJNIJAVA_getLandMarks
(JNIEnv *env, jobject obj, jstring dat, jstring img) {
	//FILE* log = NULL;
	//log = fopen("error.txt", "a");
	double num[136];
	const char* str1;
	const char* str2;
	//fprintf(log, "%s ", "begin function");
	str1 = env->GetStringUTFChars(dat, false);
	str2 = env->GetStringUTFChars(img, false);
	frontal_face_detector detector = get_frontal_face_detector();
	shape_predictor sp;
	deserialize(str1) >> sp;
	array2d<rgb_pixel> image;
	load_image(image, str2);
	env->ReleaseStringUTFChars(dat, str1);
	env->ReleaseStringUTFChars(img, str2);
	pyramid_up(image);
	std::vector<dlib::rectangle> dets = detector(image);
	if (dets.size() == 0) {
		for (unsigned long i = 0; i < 136; i++) {
			num[i] = 0;
		}
	}
	else {

		full_object_detection shape = sp(image, dets[0]);

		for (unsigned long i = 0; i < shape.num_parts(); i++)
		{
			num[i] = shape.part(i).x();
			num[135 - i] = shape.part(i).y();
		}
	}
	//fflush(log);
	//fclose(log);
	jdoubleArray arr = env->NewDoubleArray(136);
	env->SetDoubleArrayRegion(arr, 0, 136, num);
	return arr;
}
